package saltsheep.guilib.command;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import saltsheep.guilib.client.gui.GuiHandler;

public class CommandTestGui extends CommandBase {

	@Override
	public String getName() {
		return "testGui";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "§c/testGui";
	}
	
	public int getRequiredPermissionLevel()
    {
        return 4;
    }

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		GuiHandler.openBlackTips((EntityPlayer) sender, Boolean.parseBoolean(args[0]), new String[] {"难以置信...","在当前句所有文本播放完后，任意键继续下一句","You see?"}, 0.25, null);
	}
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos){
		return null;
	}

}
