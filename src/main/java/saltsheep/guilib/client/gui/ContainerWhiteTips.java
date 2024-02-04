package saltsheep.guilib.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import saltsheep.guilib.client.ContainerAuto;
import saltsheep.guilib.event.WhiteTipsEvent;
import saltsheep.lib.common.BaseType;
import saltsheep.lib.exception.InputIllegalException;

public class ContainerWhiteTips extends ContainerAuto {

	public final EntityPlayer playerIn;
	public final boolean isServer;
	public Object extraField = null;
	
	public ContainerWhiteTips(EntityPlayer playerIn, String itemID, String title, String text, boolean isServer, Object extraField) throws InputIllegalException {
		this.playerIn = playerIn;
		this.isServer = isServer;
		this.extraField = extraField;
		this.putValue(BaseType.STRING, "itemID", itemID);
		this.putValue(BaseType.STRING, "title", title);
		if(isServer)
			text = text.replaceAll("\\\\n", "\n");
		this.putValue(BaseType.STRING, "text", text);
		if(!isServer)
			playerIn.playSound(SoundEvents.BLOCK_NOTE_BELL, 1, 1);
		MinecraftForge.EVENT_BUS.post(new WhiteTipsEvent.Create(this));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		if(!isServer)
			playerIn.playSound(SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, 0.5f, 1);
		MinecraftForge.EVENT_BUS.post(new WhiteTipsEvent.Close(this));
	}

}
