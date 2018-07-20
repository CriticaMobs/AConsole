package ru.virtue.aconsole.yml;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class YmlFile {

	YamlConfiguration cfg;
	File file;
	
	YmlFile(JavaPlugin plugin, String name, Boolean isCreate) {
		 file = new File(plugin.getDataFolder(), name + ".yml");
		 if(!file.exists()) {
			 if(isCreate) {
				 try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }else {
				 plugin.saveResource(name + ".yml", false);
			 }
		 }
		 cfg = YamlConfiguration.loadConfiguration(file);
	}
	
	public void save() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public FileConfiguration getConfig() {
		return cfg;
	}
	
	public void load() {
		try {
			cfg.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
