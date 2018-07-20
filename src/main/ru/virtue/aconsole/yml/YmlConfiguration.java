package ru.virtue.aconsole.yml;

import org.bukkit.plugin.java.JavaPlugin;

public class YmlConfiguration extends YmlFile
{

	public YmlConfiguration(JavaPlugin plugin) {
		super(plugin, "config", false);
		load();
	}
	
	@Override
	public void load() {
		super.load();
	}

	
}
