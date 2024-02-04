package saltsheep.guilib.event;

import net.minecraftforge.fml.common.eventhandler.Event;
import saltsheep.guilib.client.gui.ContainerBlackTips;

public class BlackTipsEvent extends Event {

	public final ContainerBlackTips container;
	public BlackTipsEvent(ContainerBlackTips container) {this.container=container;}
	
	public static class Create extends BlackTipsEvent{
		public Create(ContainerBlackTips container) {
			super(container);
		}
	}
	
	public static class Close extends BlackTipsEvent{
		public Close(ContainerBlackTips container) {
			super(container);
		}
	}
	
	public static class TurnText extends BlackTipsEvent{
		public TurnText(ContainerBlackTips container) {
			super(container);
		}
	}
	
}
