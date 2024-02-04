package saltsheep.guilib.client;

import java.awt.Color;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.LogicThief;

public class ColorHelper {

	public static int getRGBA(int red,int green,int blue,int alpha) {
		return new Color(red,green,blue,alpha).getRGB();
	}
	
	public static int getRGBA(int red,int green,int blue,float alpha) {
		return new Color(red/255,green/255,blue/255,alpha).getRGB();
	}
	
	public static void drawFullBackground(GuiScreen openGui,int colorRBGA) {
		LogicThief.drawFullBackground(openGui, colorRBGA);
	}
	
}
