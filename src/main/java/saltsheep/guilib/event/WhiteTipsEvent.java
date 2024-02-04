package saltsheep.guilib.event;

import net.minecraftforge.fml.common.eventhandler.Event;
import saltsheep.guilib.client.gui.ContainerWhiteTips;

public class WhiteTipsEvent extends Event {

	public final ContainerWhiteTips container;
	public WhiteTipsEvent(ContainerWhiteTips container) {this.container=container;}
	
	public static class Create extends WhiteTipsEvent{
		public Create(ContainerWhiteTips container) {
			super(container);
		}
	}
	
	public static class Close extends WhiteTipsEvent{
		public Close(ContainerWhiteTips container) {
			super(container);
		}
	}
	
}
