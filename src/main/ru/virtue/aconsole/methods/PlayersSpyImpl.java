package ru.virtue.aconsole.methods;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class PlayersSpyImpl implements PlayersSpy
{
	
	private ArrayList<Player> spy = new ArrayList<>();

	@Override
	public ArrayList<Player> getList() {
		return spy;
	}

	@Override
	public void delPlayer(Player p) {
		if(spy.contains(p)) {
			spy.remove(p);
		}
	}

	@Override
	public void addPlayer(Player p) {
		if(!spy.contains(p)) {
			spy.add(p);
		}
	}

	@Override
	public void clearAll() {
		spy.clear();
	}
	
	

}
