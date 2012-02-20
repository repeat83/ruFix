package me.repeat.ruFix;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class ruFix extends JavaPlugin {

	private final ruFixPlayerListener PlayerListener = new ruFixPlayerListener(this);
	private final ruFixServerListener ServerListener = new ruFixServerListener(this);

	public static boolean ruFixDebug = false;
	public static String ruFixConsole = "UTF-8";
	public static String ruFixLogFile = "UTF-8";

	public static char[] fromGame = {};
	public static char[] toGame = {};
	
	public static String prefix = null;

	
	@Override
	public void onEnable() { 

        PluginDescriptionFile pdfFile = this.getDescription();
		prefix = "[" + pdfFile.getName()+ "]";

		Logger.getLogger("Minecraft").info( prefix + " version " + pdfFile.getVersion() + " is enabled!" );

		readConfig();		

		try {
			ruFixHandler.handlerChange();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PluginManager pm = this.getServer().getPluginManager();

// в PLAYER_CHAT Event.Priority.Lowest - важен для выполнения перекодировки и применения её для всех эвентов		
// Lowest - выполняется одним из первых
// Highest - выполняется одним из последних
		pm.registerEvent(Event.Type.PLAYER_CHAT, PlayerListener, Event.Priority.Lowest, this);
		pm.registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, PlayerListener, Event.Priority.Lowest, this);
		pm.registerEvent(Event.Type.SERVER_COMMAND, ServerListener, Event.Priority.Lowest, this);

	
		readTables();
	}

	@Override
	public void onDisable(){ 
		Logger.getLogger("Minecraft").info( prefix + " is disabled!" );
	}

	private void writeNode(String node, Object value, Configuration config){
		if (config.getProperty(node) == null) config.setProperty(node, value);
	}
	
	private void readConfig() {
    	getDataFolder().mkdir();
    	File file = new File(getDataFolder(), "config.yml");
    	try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Configuration config = new Configuration(file);
    	config.load();
    	writeNode("Console", "UTF-8", config);
    	writeNode("LogFile", "UTF-8", config);
    	writeNode("Debug", false, config);

    	List<String> tables = new ArrayList<String>();
   	 
    	tables.add("ru");
//    	tables.add("gr");
    	 
    	writeNode("Tables", tables, config);
    	
    	config.save();

    	ruFixDebug = config.getBoolean("Debug", false);
    	ruFixConsole = config.getString("Console", "UTF-8");
    	ruFixLogFile = config.getString("LogFile", "UTF-8");
    	
    	
/*    	String[] split = config.getString("Vanilla.hiddenplugins", "").split(",");
    	for (int i = 0; i < split.length; i++){
    		hiddenPlugins.add(split[i].toLowerCase());
    	}
*/
    }

	private void readTables() {
		File file = new File(getDataFolder(), "config.yml");
		Configuration config = new Configuration(file);
    	config.load();

		List<String> fixTables = config.getStringList("Tables", null);
    	
    	if (fixTables == null) {
    		//not read
    	} else {
    		for (String fixTable : fixTables)
            {
    	    	file = new File(getDataFolder(), fixTable+".tbl");
    	    	FileReader input;
				try {
					input = new FileReader(file);
	    	    	BufferedReader bufRead = new BufferedReader(input);
	    	    	
	    	    	// read remark
	    	    	bufRead.readLine();

	    	    	fromGame = bufRead.readLine().toCharArray();
	    	    	toGame = bufRead.readLine().toCharArray();

//	    	    	System.out.print(line);
//	    	    	System.out.print(new String (fromGame));
//	    	    	System.out.print(fromGame.length);
//	    	    	System.out.print(new String (toGame));
//	    	    	System.out.print(toGame.length);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
     	}
	}
}
