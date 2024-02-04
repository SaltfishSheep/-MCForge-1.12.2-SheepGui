package saltsheep.guilib.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import saltsheep.guilib.client.gui.ContainerWhiteTips;
import saltsheep.lib.network.NetworkHelper;

public class DataWhiteTips {

	private static final String NAME = "SHEEPWHITETIPSDATA";
	static final FMLEventChannel CHANNEL = NetworkRegistry.INSTANCE.newEventDrivenChannel(NAME);
	
	@SideOnly(Side.CLIENT)
	public static void closeByClient() {
		PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
		NetworkHelper.writeString(buf, Minecraft.getMinecraft().player.getName());
		CHANNEL.sendToServer(new FMLProxyPacket(buf,NAME));
	}
	
	@SubscribeEvent
	public static void closeByServer(FMLNetworkEvent.ServerCustomPacketEvent event) {
		if(event.getPacket().channel().equals(NAME)) {
			MinecraftServer MCServer = FMLCommonHandler.instance().getMinecraftServerInstance();
			ByteBuf buf = event.getPacket().payload();
			String playerName = NetworkHelper.readString(buf);
			MCServer.addScheduledTask(()->{
				EntityPlayerMP player = MCServer.getPlayerList().getPlayerByUsername(playerName);
				if(player != null&&player instanceof EntityPlayerMP&&player.openContainer instanceof ContainerWhiteTips) {
					player.closeScreen();
				}
			});
		}
	}
	
}
