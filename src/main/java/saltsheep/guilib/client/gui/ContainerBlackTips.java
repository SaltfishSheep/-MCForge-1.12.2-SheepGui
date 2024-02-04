package saltsheep.guilib.client.gui;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import saltsheep.guilib.SheepGui;
import saltsheep.guilib.event.BlackTipsEvent;
import saltsheep.lib.common.BaseType;
import saltsheep.lib.exception.InputIllegalException;

public class ContainerBlackTips extends saltsheep.guilib.client.ContainerAuto {
	
	public final EntityPlayer playerIn;
	public final String[] allTexts;
	public int point;
	public double eachTickUpdateChar;
	public double nowLength;
	public final boolean isServer;
	public Object extraField = null;
	
	public ContainerBlackTips(EntityPlayer playerIn,boolean isTextShake,String[] allTexts,double eachTickUpdateChar,boolean isServer, Object extraField) throws InputIllegalException {
		this.playerIn = playerIn;
		this.putValue(BaseType.BOOLEAN, "isTextShake", isTextShake);
		this.allTexts = allTexts;
		for(int i=0;i<this.allTexts.length;i++)
			this.allTexts[i] = this.allTexts[i].replaceAll("\\\\n", "\n");
		this.point = 0;
		this.eachTickUpdateChar = eachTickUpdateChar;
		this.nowLength = 0;
		this.isServer = isServer;
		this.extraField = extraField;
		MinecraftForge.EVENT_BUS.post(new BlackTipsEvent.Create(this));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	public void turnNextText() {
		try {
			if(this.allTexts[this.point].length()<=this.getValue(BaseType.STRING, "showText").length()) {
				this.point++;
				this.nowLength=0;
				this.putValue(BaseType.STRING, "showText", "");
				if(this.point>=this.allTexts.length) {
					this.putValue(BaseType.BOOLEAN, "canClose", true);;
					this.playerIn.closeScreen();
					return;
				}
				MinecraftForge.EVENT_BUS.post(new BlackTipsEvent.TurnText(this));
			}
		} catch (InputIllegalException e) {
			SheepGui.printError(e);
		}
	}
	
	@Override
	public void writeUpdate(ByteBuf buf) {
		try {
			String nowText = this.getValue(BaseType.STRING, "showText");
			if(nowText.length()<allTexts[this.point].length()) {
				this.nowLength = Math.min(allTexts[this.point].length(),this.nowLength+this.eachTickUpdateChar);
				this.putValue(BaseType.STRING, "showText", allTexts[this.point].substring(0, (int)this.nowLength));
			}else if(nowText.length()==allTexts[this.point].length()) {
				this.putValue(BaseType.STRING, "showText", nowText+"§f§l◁");
			}/*else if(nowText.length()>allTexts[this.point].length())){
				this.point++;
				this.putValue(BaseType.STRING, "showText", "");
			}*/
		}catch(Throwable error) {
			SheepGui.printError(error);
		}
		super.writeUpdate(buf);
	}
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn){
		super.onContainerClosed(playerIn);
		MinecraftForge.EVENT_BUS.post(new BlackTipsEvent.Close(this));
	}

}
