package saltsheep.guilib;

import org.apache.logging.log4j.Logger;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import saltsheep.guilib.command.CommandManager;
import saltsheep.guilib.network.NetworkHandler;

//*The real server need only.
@Mod(modid = SheepGui.MODID, name = SheepGui.NAME, version = SheepGui.VERSION)
public class SheepGui
{
    public static final String MODID = "sheepgui";
    public static final String NAME = "SheepGui";
    public static final String VERSION = "1.0";
    public static SheepGui instance;

    private static Logger logger;

    public SheepGui() {
    	instance = this;
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        instance = this;
        NetworkHandler.register();
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
    	NetworkRegistry.INSTANCE.registerGuiHandler(instance, new saltsheep.guilib.client.gui.GuiHandler());
    }
    
    @EventHandler
    public static void onServerStarting(FMLServerStartingEvent event){
    	CommandManager.register(event);
	}
    
    public static Logger getLogger() {
    	return logger;
    }
    
    public static MinecraftServer getMCServer() {
    	return FMLCommonHandler.instance().getMinecraftServerInstance();
    }
    
    public static void printError(Throwable error) {
    	String messages = "";
    	for(StackTraceElement stackTrace : error.getStackTrace()) {
    		messages = messages+stackTrace.toString()+"\n";
		}
    	SheepGui.getLogger().error("警告！在咸羊我的mod里出现了一些错误，信息如下：\n"+messages+"出现错误类型:"+error.getClass());
    }
    
    public static void info(String str) {
    	logger.info(str);
    }
    
    public static void info(Object obj) {
    	if(obj == null)
    		logger.info("null has such obj.");
    	else
    		logger.info(obj.toString());
    }
    
    
}
