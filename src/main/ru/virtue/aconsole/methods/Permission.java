package ru.virtue.aconsole.methods;

import org.bukkit.entity.Player;

public interface Permission {

	boolean hasPermission(Player p, String path);
	boolean hasAllowPlayers(Player p);
	boolean hasOp(Player p);
}
