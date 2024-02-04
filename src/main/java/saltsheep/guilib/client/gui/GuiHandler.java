package saltsheep.guilib.client.gui;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import saltsheep.guilib.SheepGui;
import saltsheep.lib.SheepLib;

public class GuiHandler implements IGuiHandler {
	
	public static final int BLACK_TIPS = 1;
	public static final int WHITE_TIPS = 2;
	
	private static boolean black_isTextShake = false;
	private static String[] black_tempShowTexts = null;
	private static double black_eachTickUpdateChar = 0.1;
	
	private static String white_itemID = null;
	private static String white_title = null;
	private static String white_text = null;
	
	private static Object extraField = null;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		try {
			if(ID==BLACK_TIPS) {
				return new ContainerBlackTips(player, black_isTextShake, black_tempShowTexts, black_eachTickUpdateChar, true, extraField);
			}else if(ID==WHITE_TIPS) {
				return new ContainerWhiteTips(player, white_itemID, white_title, white_text, true, extraField);
			}
		} catch (Throwable e) {
			SheepLib.printError(e);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		try {
			if(ID==BLACK_TIPS) {
				return new GuiBlackTips(new ContainerBlackTips(player, false, new String[0], 0.1, false, null));
			}else if(ID==WHITE_TIPS) {
				return new GuiWhiteTips(new ContainerWhiteTips(player, null, null, null, false, null));
			}
		} catch (Throwable e) {
			SheepLib.printError(e);
		}
		return null;
	}
	
	public static synchronized void openBlackTips(EntityPlayer player,boolean isTextShakeIn,@Nonnull String[] tempShowTextsIn,double eachTickUpdateCharIn,Object extraField) {
		GuiHandler.black_isTextShake = isTextShakeIn;
		GuiHandler.black_tempShowTexts = tempShowTextsIn;
		GuiHandler.black_eachTickUpdateChar = eachTickUpdateCharIn;
		GuiHandler.extraField = extraField;
		player.openGui(SheepGui.instance, BLACK_TIPS, player.world, 0, 0, 0);
	}
	
	public static synchronized void openWhiteTips(EntityPlayer player,String itemID,String title,String text,Object extraField) {
		GuiHandler.white_itemID = itemID;
		GuiHandler.white_title = title;
		GuiHandler.white_text = text;
		GuiHandler.extraField = extraField;
		player.openGui(SheepGui.instance, WHITE_TIPS, player.world, 0, 0, 0);
	}

}
