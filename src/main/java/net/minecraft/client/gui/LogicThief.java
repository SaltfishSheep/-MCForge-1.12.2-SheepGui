package net.minecraft.client.gui;

public class LogicThief {

	public static void drawFullBackground(GuiScreen openGui,int colorRBGA) {
		openGui.drawGradientRect(0, 0, openGui.width, openGui.height, colorRBGA, colorRBGA);
	}
	
}
