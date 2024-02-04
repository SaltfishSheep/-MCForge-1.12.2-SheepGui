package saltsheep.guilib.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import saltsheep.guilib.SheepGui;
import saltsheep.guilib.client.ColorHelper;
import saltsheep.guilib.network.DataWhiteTips;
import saltsheep.lib.common.BaseType;

public class GuiWhiteTips extends GuiContainer {

	private static ResourceLocation TEXTURE = new ResourceLocation("sheepgui:textures/gui/white_tips.png");
	
	ContainerWhiteTips information;
	
	public GuiWhiteTips(ContainerWhiteTips containerWhiteTips) {
		super(containerWhiteTips);
		this.information = containerWhiteTips;
		this.xSize = 80;
		this.ySize = 100;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.drawDefaultBackground();
		GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		try {
			String itemID = this.information.getValue(BaseType.STRING, "itemID");
			String title = this.information.getValue(BaseType.STRING, "title");
			String text = this.information.getValue(BaseType.STRING, "text");
			if(itemID==null||title==null||text==null)
				return;
			GuiHelper.drawItemTexture(this, 0, 0, 9, 9, itemID, false);
			int titleWidth = this.mc.fontRenderer.getStringWidth(title);
			GlStateManager.pushMatrix();
			this.mc.fontRenderer.drawString(title, 26+(xSize-26-4-titleWidth)/2, 8+4, ColorHelper.getRGBA(0, 0, 0, 1.0f));
			GlStateManager.scale(0.8f,0.8f,0.8f);
			this.mc.fontRenderer.drawSplitString(text, (int)(6/0.8), (int)((26+4)/0.8), 85, ColorHelper.getRGBA(0, 0, 0, 1.0f));
			GlStateManager.scale(1/0.8,1/0.8,1/0.8);
			this.mc.fontRenderer.drawString("press [esc/space]", (xSize-this.mc.fontRenderer.getStringWidth("Press [esc/space]"))/2, ySize-4-this.mc.fontRenderer.FONT_HEIGHT, ColorHelper.getRGBA(0, 0, 0, 1.0f));
			GlStateManager.popMatrix();
		} catch (Throwable e) {
			SheepGui.printError(e);
		}
		
	}
	
	@Override
	protected void mouseClicked(int x, int y, int mouseButton) {
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		if(keyCode==1||keyCode==57)
			DataWhiteTips.closeByClient();;
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
