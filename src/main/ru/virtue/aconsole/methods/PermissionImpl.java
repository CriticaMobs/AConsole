package ru.virtue.aconsole.methods;

import org.bukkit.entity.Player;

import ru.virtue.aconsole.yml.YmlConfiguration;

public class PermissionImpl implements Permission{

	private TellMessage tellMsg;
	private YmlConfiguration cfg;
	
	public PermissionImpl(TellMessage tellMessage, YmlConfiguration cfg) {
		this.cfg = cfg;
		this.tellMsg = tellMessage;
	}

	@Override
	public boolean hasPermission(Player p, String path) {
		if(cfg.getConfig().getBoolean("Permissions." + path + ".enabled")) {
			if(p.hasPermission(cfg.getConfig().getString("Permissions." + path + ".permission"))) {
				return false;
			}else {
				tellMsg.tellMessage(p, path + "Message");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasAllowPlayers(Player p) {
        if(cfg.getConfig().getBoolean("Allow-Players") && !cfg.getConfig().getBoolean("OpPlayers")){
        	if(!cfg.getConfig().getStringList("Players").contains(p.getName())){
        		return true;
        	}
        }
		return false;
	}

	@Override
	public boolean hasOp(Player p) {
		if(!cfg.getConfig().getBoolean("Allow-Players")) {
	    	if(cfg.getConfig().getBoolean("OpPlayers") && !p.isOp()){
	    		return true;
	    	}
		}
		return false;
	}
	
	
}
