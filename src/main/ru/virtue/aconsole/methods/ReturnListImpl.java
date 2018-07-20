package ru.virtue.aconsole.methods;

import java.util.List;

import org.bukkit.entity.Player;

import ru.virtue.aconsole.yml.YmlConfiguration;

public class ReturnListImpl implements ReturnList{

	private YmlConfiguration cfg;
	
	public ReturnListImpl(YmlConfiguration cfg) {
		this.cfg = cfg;
	}
	
	@Override
	public boolean BlackList(Player p, String str) {
        if(cfg.getConfig().getBoolean("black-list")){
        	List<String> bl = cfg.getConfig().getStringList("blacklist");
        	for(String msg : bl) {
        		if(str.contains(msg)) {
        			return true;
        		}
        	}
        }
		return false;
	}

	@Override
	public boolean WhiteList(Player p, String str) {
        if(cfg.getConfig().getBoolean("white-list")) {
        	List<String> wl = cfg.getConfig().getStringList("whitelist");
        	if(!wl.contains(str.split(" ")[0])) {
        		return true;
        	}
        }
		return false;
	}

}
