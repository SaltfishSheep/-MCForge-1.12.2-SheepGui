package saltsheep.guilib.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import saltsheep.guilib.client.ContainerAuto;

public class DataContainerAuto {

	private static final String NAME = "SHEEPCONTAINERAUTODATA";
	static final FMLEventChannel CHANNEL = NetworkRegistry.INSTANCE.newEventDrivenChannel(NAME);
	
	public static void updateByServer(EntityPlayerMP player) {
		if(player.openContainer instanceof ContainerAuto) {
			PacketBuffer bufSend = new PacketBuffer(Unpooled.buffer());
			((ContainerAuto)player.openContainer).writeUpdate(bufSend);
			CHANNEL.sendTo(new FMLProxyPacket(bufSend,NAME), player);
		}
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void updateByClient(FMLNetworkEvent.ClientCustomPacketEvent event) {
		if(event.getPacket().channel().equals(NAME)) {
			Minecraft MC = Minecraft.getMinecraft();
			ByteBuf buf = event.getPacket().payload();
			if(MC.player.openContainer instanceof ContainerAuto) {
				MC.addScheduledTask(()->{
					try {
						((ContainerAuto)MC.player.openContainer).readUpdate(buf);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
		}
	}
	
}
