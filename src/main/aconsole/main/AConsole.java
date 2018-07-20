package aconsole.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;      
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import aconsole.commands.Console;
import aconsole.hooks.PlayerCommandPreprocess;
import aconsole.methods.IMethods;
import aconsole.methods.Methods;

public class AConsole extends JavaPlugin
{
	public File log;
	public List<Player> spy = new ArrayList<>();
	public IMethods methods = new Methods(this);
	
	public void onEnable(){
		getCommand("console").setExecutor(new Console(this));
		Bukkit.getPluginManager().registerEvents(new PlayerCommandPreprocess(this), this);
		getMethods().loadConfig();
	}
	
	public void onDisable(){
		if(getConfig().getBoolean("Log.delete")){
			if(log.exists()){
				log.delete();
			}else getMethods().loadConfig();
		}
	}
	
	public IMethods getMethods() {
		return methods;
	}
	
	
}
