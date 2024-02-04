package saltsheep.guilib.client;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import saltsheep.guilib.network.DataContainerAuto;

@EventBusSubscriber
public class EventHandler {

	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
		if(!event.player.world.isRemote&&event.player instanceof EntityPlayerMP) {
			DataContainerAuto.updateByServer((EntityPlayerMP) event.player);
		}
	}
	
	/*@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onGuiContainerAutoClose(GuiOpenEvent event) {
		if(Minecraft.getMinecraft().currentScreen!=null&&Minecraft.getMinecraft().currentScreen instanceof GuiBlackTips) {
			event.setCanceled(true);
			return;
		}
	}*/
	
}
