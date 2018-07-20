package ru.virtue.aconsole.methods;

import org.bukkit.ChatColor;

public class ColorTextImpl implements ColorText
{

	@Override
	public String color(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
}
