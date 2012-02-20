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
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ruFix extends JavaPlugin {

	private final ruFixPlayerListener PlayerListener = new ruFixPlayerListener(this);
	private final ruFixServerListener ServerListener = new ruFixServerListener(this);

	public static boolean ruFixDebug = false;
	public static String ruFixConsole = "UTF-8";
	public static String ruFixLogFile = "UTF-8";

	public static char[] fromGame = {};
	public static char[] toGame = {};
	
	public static String prefix = null;

	private FileConfiguration config = null;
	private File configFile = null;
	
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

// в PLAYER_CHAT Event.Priority.Lowest - важен дл€ выполнени€ перекодировки и применени€ еЄ дл€ всех эвентов		
// Lowest - выполн€етс€ одним из первых
// Highest - выполн€етс€ одним из последних
		
		pm.registerEvents(PlayerListener, this);
		pm.registerEvents(ServerListener, this); // закомментируйте эту строчку и плагин не будет фильтровать сообщения из консоли
		
		// ниже - старые ивенты. Я не знаю зачем они тут, просто чтоб не потерялись.
		//pm.registerEvent(Event.Type.PLAYER_CHAT, PlayerListener, Event.Priority.Lowest, this);
		//pm.registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, PlayerListener, Event.Priority.Lowest, this);
		//pm.registerEvent(Event.Type.SERVER_COMMAND, ServerListener, Event.Priority.Lowest, this);

	
		readTables();
	}

	@Override
	public void onDisable(){ 
		Logger.getLogger("Minecraft").info( prefix + " is disabled!" );
	}
	
    public void saveConfig() {
	     if (config == null || configFile == null) {
	     return;
	     }
	     try {
 	         config.save(configFile);
 	     } catch (IOException ex) {
	         Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + configFile, ex);
	     }
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
    	//старые конфиги. deprecated.
    	//Configuration config = new Configuration(file);
    	
    	config = YamlConfiguration.loadConfiguration(configFile);
    	
    	//config.load();
    	//writeNode("Console", "UTF-8", config);
    	//writeNode("LogFile", "UTF-8", config);
    	//writeNode("Debug", false, config);

    	List<String> tables = new ArrayList<String>();
   	 
    	tables.add("ru");
//    	tables.add("gr");
    	 
    	//writeNode("Tables", tables, config);
    	
    	saveConfig();

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
		// старые конфиги. deprecated.
		//File file = new File(getDataFolder(), "config.yml");
		//Configuration config = new Configuration(file);
    	//config.load();
		
		File configFile = new File(getDataFolder(), "config.yml");
	    config = YamlConfiguration.loadConfiguration(configFile);

		List<String> fixTables = config.getStringList("Tables");
    	
    	if (fixTables != null) {
    		for (String fixTable : fixTables)
            {
    			File file = new File(getDataFolder(), fixTable + ".tbl");
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
