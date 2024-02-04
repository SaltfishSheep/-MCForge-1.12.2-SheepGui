package saltsheep.guilib.network;

public class NetworkHandler {
	
	public static void register() {
		DataContainerAuto.CHANNEL.register(DataContainerAuto.class);
		DataBlackTips.CHANNEL.register(DataBlackTips.class);
		DataWhiteTips.CHANNEL.register(DataWhiteTips.class);
	}
	
}
