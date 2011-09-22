package me.repeat.ruFix;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.*;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ruFix extends JavaPlugin {

	private final ruFixPlayerListener PlayerListener = new ruFixPlayerListener(this);
	private final ruFixServerListener ServerListener = new ruFixServerListener(this);

	public static String prefix = null;

	@Override
	public void onEnable() { 

        PluginDescriptionFile pdfFile = this.getDescription();
		prefix = "[" + pdfFile.getName()+ "]";

		Logger.getLogger("Minecraft").info( prefix + " version " + pdfFile.getVersion() + " is enabled!" );
		
		try {
			ruFixHandler.handlerChange();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		Logger globalLogger = Logger.getLogger("Minecraft");
		Handler[] handlers = globalLogger.getHandlers();

		for (Handler handler : handlers)
        {
//			globalLogger.info("!!!: "+handler.getEncoding());
			//globalLogger.removeHandler(handler);
			if (handler instanceof StreamHandler) {
				try {
					//console
					handler.setEncoding("Cp866");
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (handler instanceof FileHandler) {
				try {
					// logfile
					handler.setEncoding("utf8");
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//			globalLogger.info("@@@: "+handler.getEncoding());
		}
*/

		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_CHAT, PlayerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.SERVER_COMMAND, ServerListener, Event.Priority.Normal, this);
	}

	@Override
	public void onDisable(){ 
		Logger.getLogger("Minecraft").info( prefix + " is disabled!" );
	}

/*
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
	{
	        String commandName = command.getName().toLowerCase();
	        
	        if(sender instanceof Player)
	        {
	            
	            if (commandName.equals("test")) {
	            	//TODO
	            	return true;
	            }
	            else if (commandName.equals("plua")) {

	            	if (args.length < 1) { return false; }
	            	// TODO
	                return true;
	            }
	        }
		return false;
	}
*/
}
