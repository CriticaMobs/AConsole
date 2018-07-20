package ru.virtue.aconsole;

import org.bukkit.Bukkit;      
import org.bukkit.plugin.java.JavaPlugin;

import ru.virtue.aconsole.command.Console;
import ru.virtue.aconsole.listener.PlayerCommandPreprocess;
import ru.virtue.aconsole.methods.MethodsManager;
import ru.virtue.aconsole.yml.YmlConfiguration;
import ru.virtue.aconsole.yml.YmlLanguage;
import ru.virtue.aconsole.yml.YmlLog;

public class AConsole extends JavaPlugin
{
	private MethodsManager methodsManager;
	private YmlLog log;
	private YmlConfiguration cfg;
	
	@Override
	public void onEnable(){
		cfg = new YmlConfiguration(this);
		YmlLanguage lang = new YmlLanguage(this);
		methodsManager = new MethodsManager(lang, cfg);
		log = new YmlLog(this, cfg);
		getCommand("console").setExecutor(new Console(methodsManager.getPermission(), methodsManager.getReturnList(), methodsManager.getTellMessage(), log, lang, methodsManager.getPlayersSpy()));
		Bukkit.getPluginManager().registerEvents(new PlayerCommandPreprocess(this, methodsManager.getCommandVerify(), methodsManager.getTellMessage(), log, methodsManager.getPlayersSpy()), this);
	}
	
	@Override
	public void onDisable(){
		if(cfg.getConfig().getBoolean("Log.delete")) {
			log.deleteLog();
		}
	}
	
	
	
}
