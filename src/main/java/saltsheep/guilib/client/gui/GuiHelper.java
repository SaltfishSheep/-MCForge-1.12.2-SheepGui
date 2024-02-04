package saltsheep.guilib.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiHelper {

	private static final ResourceLocation TEXTURE_TOASTS = new ResourceLocation("minecraft:textures/gui/toasts.png");
	private static final ResourceLocation TEXTURE_STATS_ICONS = new ResourceLocation("minecraft:textures/gui/container/stats_icons.png");
	private static final ResourceLocation TEXTURE_WIDGETS = new ResourceLocation("minecraft:textures/gui/widgets.png");
	
	public static void drawWhiteScreen(GuiScreen gui, int offsetX, int offsetY, int xSize, int ySize) {
		if(!(xSize>=8&&ySize>=8))
			return;
		GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        gui.mc.getTextureManager().bindTexture(TEXTURE_TOASTS);
        //*左上角及横幅
        {
        	if(xSize<=160)
        		gui.drawTexturedModalRect(offsetX, offsetY, 0, 32, xSize-4, 4);
        	else {
        		gui.drawTexturedModalRect(offsetX, offsetY, 0, 32, 160-4, 4);
        		int restX = xSize-160;
        		for(int nowX=160-4;restX>0;restX-=152) {
        			if(restX-152>0)
        				gui.drawTexturedModalRect(offsetX+nowX, offsetY, 4, 32, 152, 4);
        			else
        				gui.drawTexturedModalRect(offsetX+nowX, offsetY, 4, 32, restX, 4);
        			nowX+=152;
        		}
        	}
		}
        //*右上角
        gui.drawTexturedModalRect(offsetX+xSize-4, offsetY, 160-4, 32, 4, 4);
        //*左下角及横幅
        {
        	if(xSize<=160)
        		gui.drawTexturedModalRect(offsetX, offsetY+ySize-4, 0, 64-4, xSize-4, 4);
        	else {
        		gui.drawTexturedModalRect(offsetX, offsetY+ySize-4, 0, 64-4, 160-4, 4);
        		int restX = xSize-160;
        		for(int nowX=160-4;restX>0;restX-=152) {
        			if(restX-152>0)
        				gui.drawTexturedModalRect(offsetX+nowX, offsetY+ySize-4, 4, 64-4, 152, 4);
        			else
        				gui.drawTexturedModalRect(offsetX+nowX, offsetY+ySize-4, 4, 64-4, restX, 4);
        			nowX+=152;
        		}
        	}
		}
        //*右下角
        gui.drawTexturedModalRect(offsetX+xSize-4, offsetY+ySize-4, 160-4, 64-4, 4, 4);
        //*左侧与中填
        {
        	int restY = ySize-8;
        	for(int nowY=4;restY>0;restY-=24) {
        		int drawY=restY;
        		if(restY>24) {
        			drawY=24;
        		}
        		if(xSize<=160)
            		gui.drawTexturedModalRect(offsetX, offsetY+nowY, 0, 32+4, xSize-4, drawY);
            	else {
            		gui.drawTexturedModalRect(offsetX, offsetY+nowY, 0, 32+4, 160-4, drawY);
            		int restX = xSize-160;
            		for(int nowX=160-4;restX>0;restX-=152) {
            			if(restX-152>0)
            				gui.drawTexturedModalRect(offsetX+nowX, offsetY+nowY, 4, 32+4, 152, drawY);
            			else
            				gui.drawTexturedModalRect(offsetX+nowX, offsetY+nowY, 4, 32+4, restX, drawY);
            			nowX+=152;
            		}
            	}
        		nowY+=24;
        	}
        }
        //*右侧
        {
        	int restY = ySize-8;
        	for(int nowY=4;restY>0;restY-=24) {
        		int drawY=restY;
        		if(restY>24) {
        			drawY=24;
        		}
        		gui.drawTexturedModalRect(offsetX+xSize-4, offsetY+nowY, 156, 32+4, 4, drawY);
        		nowY+=24;
        	}
        }
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
	}
	
	public static void drawLightSlotWithLeftShadow(GuiScreen gui, int offsetX, int offsetY, int guiX, int guiY, int slotCount) {
		if(slotCount<0)
			return;
		GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        gui.mc.getTextureManager().bindTexture(TEXTURE_STATS_ICONS);
        for(int i=0;i<slotCount;i++)
        	gui.drawTexturedModalRect(offsetX+guiX+i*18, offsetY+guiY, 0, 0, 18, 18);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
	}
	
	public static void drawLightSlotWithRightShadow(GuiScreen gui, int offsetX, int offsetY, int guiX, int guiY, int slotCount) {
		if(slotCount<0)
			return;
		GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        gui.mc.getTextureManager().bindTexture(TEXTURE_STATS_ICONS);
        for(int i=0;i<slotCount;i++)
        	gui.drawTexturedModalRect(offsetX+guiX+i*18, offsetY+guiY, 0, 18, 18, 18);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
	}
	
	public static void drawHeavySimpleSlot(GuiScreen gui, int offsetX, int offsetY, int guiX, int guiY) {
		GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        gui.mc.getTextureManager().bindTexture(TEXTURE_WIDGETS);
        gui.drawTexturedModalRect(offsetX+guiX, offsetY+guiY, 24, 23, 22, 22);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
	}
	
	public static void drawHeavyMoreSlot(GuiScreen gui, int offsetX, int offsetY, int guiX, int guiY, int slotCount) {
		if(slotCount<0||slotCount>9)
			return;
		GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        gui.mc.getTextureManager().bindTexture(TEXTURE_WIDGETS);
        gui.drawTexturedModalRect(offsetX+guiX, offsetY+guiY, 0, 0, 1+20*slotCount, 22);
        gui.drawTexturedModalRect(offsetX+guiX+1+20*slotCount, offsetY+guiY, 0, 0, 1, 22);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
	}
	
	public static void drawItemTexture(GuiScreen gui, int offsetX, int offsetY, int guiX, int guiY, String itemID, boolean isBulingBuling) {
		ItemStack item = new ItemStack(Item.getByNameOrId(itemID));
		if(isBulingBuling)
			item.addEnchantment(Enchantments.INFINITY, 1);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(item, offsetX+guiX, offsetY+guiY);
	}
	
}
