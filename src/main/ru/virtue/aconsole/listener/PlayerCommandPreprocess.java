package ru.virtue.aconsole.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import ru.virtue.aconsole.AConsole;
import ru.virtue.aconsole.methods.CommandVerify;
import ru.virtue.aconsole.methods.PlayersSpy;
import ru.virtue.aconsole.methods.TellMessage;
import ru.virtue.aconsole.yml.YmlLog;

public class PlayerCommandPreprocess implements Listener {

	private AConsole m;
	private CommandVerify commandVerify;
	private TellMessage tellMsg;
	private YmlLog log;
	private PlayersSpy spy;
	
	public PlayerCommandPreprocess(AConsole main, CommandVerify commandVerify, TellMessage tellMessage, YmlLog log, PlayersSpy spy) {
		this.commandVerify = commandVerify;
		this.tellMsg = tellMessage;
		this.m = main;
		this.spy = spy;
		this.log = log;
	}
	
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
    	if(e.isCancelled()){
    		return;
    	}
    	if(m.getConfig().getBoolean("Log.global")) {
    		if(e.getMessage().startsWith("/")) {
    			log.writeLog(e.getPlayer(), "[Global] " + e.getMessage());
    		}
    	}
    	if(commandVerify.сommandVerify(e.getPlayer(), e.getMessage())){
    		e.setCancelled(true);
    	}
        for (String ignore : m.getConfig().getStringList("ignore.commands")) {
        	if(e.getMessage().split(" ")[0].equalsIgnoreCase("/" + ignore)){
        		return;
        	}
        }
        if(m.getConfig().getStringList("ignore.admins").contains(e.getPlayer().getName())){
        	return;
        }
        for(Player p : spy.getList()) {
        	if(p == e.getPlayer()) {
        		return;
        	}
        	tellMsg.tellMessage(p, "Spy", "@command", e.getMessage(), "@player", e.getPlayer().getName(), "@prefix", PermissionsEx.getUser(e.getPlayer().getName()).getPrefix());
        }
    }

}
