package ru.virtue.aconsole.methods;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public interface PlayersSpy {

	ArrayList<Player> getList();
	void delPlayer(Player p);
	void addPlayer(Player p);
	void clearAll();
}
