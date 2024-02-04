package saltsheep.guilib.client.gui;

import java.util.Date;

import net.minecraft.client.gui.inventory.GuiContainer;
import saltsheep.guilib.SheepGui;
import saltsheep.guilib.client.ColorHelper;
import saltsheep.guilib.network.DataBlackTips;
import saltsheep.lib.common.BaseType;
import saltsheep.lib.exception.InputIllegalException;

public class GuiBlackTips extends GuiContainer {

	ContainerBlackTips information;
	private long lastShakeTime = 0;
	private int shakeX,shakeY;
	
	public GuiBlackTips(ContainerBlackTips inventorySlotsIn) {
		super(inventorySlotsIn);
		this.information = inventorySlotsIn;
		//this.width = 0;
		//this.height = 0;
		this.xSize = 0;
		this.ySize = 0;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		ColorHelper.drawFullBackground(this, ColorHelper.getRGBA(0, 0, 0, 1.0f));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		String showText = null;
		try {
			showText = this.information.getValue(BaseType.STRING, "showText");
			int textWidth = this.mc.fontRenderer.getStringWidth(showText);
			boolean isTextShake = this.information.getValue(BaseType.BOOLEAN, "isTextShake");
			boolean turnShake = isTextShake&&new Date().getTime()>=this.lastShakeTime+100;
			if(turnShake) {
				this.lastShakeTime = new Date().getTime();
				this.shakeX = (int)(Math.random()*5);
				this.shakeY = (int)(Math.random()*5);
			}
			int x = isTextShake? -textWidth/2-2+this.shakeX:-textWidth/2;
			int y = isTextShake? -this.mc.fontRenderer.FONT_HEIGHT/2-2+this.shakeY:-this.mc.fontRenderer.FONT_HEIGHT/2;
			if(showText.contains("\n")) {
				String[] showTexts = showText.split("\n");
				int height = showTexts.length*(this.mc.fontRenderer.FONT_HEIGHT+1);
				y = isTextShake? -height/2-2+this.shakeY:-height/2;
				for(int i=0;i<showTexts.length;i++) {
					textWidth = this.mc.fontRenderer.getStringWidth(showTexts[i]);
					int newX = isTextShake? -textWidth/2-2+this.shakeX:-textWidth/2;
					int newY = y + i*(this.mc.fontRenderer.FONT_HEIGHT+1);
					this.mc.fontRenderer.drawString(showTexts[i], newX, newY, ColorHelper.getRGBA(255, 255, 255, 255));
				}
			}else
				this.mc.fontRenderer.drawString(showText, x, y, ColorHelper.getRGBA(255, 255, 255, 255));
		} catch (InputIllegalException e) {
			SheepGui.printError(e);
		}
		
	}
	
	@Override
	protected void mouseClicked(int x, int y, int mouseButton) {
		DataBlackTips.nextByClient();
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		DataBlackTips.nextByClient();
	}
	
	/*@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		try {
			if(!(this.information).getValue(BaseType.BOOLEAN, "canClose"))
				Minecraft.getMinecraft().addScheduledTask(()->Minecraft.getMinecraft().displayGuiScreen(this));
		} catch (InputIllegalException e) {
			SheepGui.printError(e);
		}
	}*/

}
