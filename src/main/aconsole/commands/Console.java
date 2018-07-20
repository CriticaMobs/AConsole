package aconsole.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import aconsole.main.AConsole;

public class Console implements CommandExecutor {
	
	private AConsole m;
	private boolean chat;
	
	public Console(AConsole main) {
		m = main;
		chat = true;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if (Bukkit.getPlayer(s.getName()) == null) {
			m.getMethods().tellMESSAGE(s, "&cOnly for players.");
            return true;
        }
        Player p = (Player) s;
        if(m.getMethods().havePermission(p)){
        	return false;
        }
		if(args.length == 0){
			for(String i : m.getConfig().getStringList("Messages.Command")){
				m.getMethods().tellMESSAGE(p, i);
			}
			return true;
		}
		if(args[0].equalsIgnoreCase("spy")){
			if(m.getMethods().checkPermission(p, "spy")) {
				return true;
			}
            if (!m.spy.contains(s)) {
            	m.spy.add((Player)s);
            	m.getMethods().tellMESSAGE(p, m.getConfig().getString("Messages.Spy.enable", "&9* &dAConsole &9* &7Пожалуйста укажите сообщение Messages/Spy/enable"));
            }else{
	            if (m.spy.contains(s)) {
	                m.spy.remove((Player)s);
	                m.getMethods().tellMESSAGE(p, m.getConfig().getString("Messages.Spy.disable", "&9* &dAConsole &9* &7Пожалуйста укажите сообщение Messages/Spy/disable"));
	            }
	            return true;
            }
            return true;
		}
		if(args[0].equalsIgnoreCase("toggle")){
			if(m.getMethods().checkPermission(p, "toggle")) {
				return true;
			}
			if(chat == false){
				m.getMethods().tellMESSAGE(p, m.getConfig().getString("Messages.enabled", "&9* &dAConsole &9* &7Пожалуйста укажите сообщение Messages/enabled"));
				chat = true;
			}else{
				m.getMethods().tellMESSAGE(p, m.getConfig().getString("Messages.disabled", "&9* &dAConsole &9* &7Пожалуйста укажите сообщение Messages/disabled"));
				chat = false;
			}
			return true;
		}
		if(args[0].equalsIgnoreCase("reload")){
			if(m.getMethods().checkPermission(p, "reload")) {
				return true;
			}
			m.saveDefaultConfig();
			m.reloadConfig();
			m.getMethods().tellMESSAGE(p, m.getConfig().getString("Messages.reload", "&9* &dAConsole &9* &7Пожалуйста укажите сообщение Messages/reload"));
			return true;
		}
		if(chat == false){
			m.getMethods().tellMESSAGE(p, m.getConfig().getString("Messages.disabled", "&9* &dAConsole &9* &7Пожалуйста укажите сообщение Messages/disabled"));
			return false;
		}
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < args.length; ++i) {
            st.append(args[i]).append(" ");
        }
        String command = st.toString().replace("/", "");
        if(m.getMethods().returnBlackList(p, command)){
        	m.getMethods().tellMESSAGE(p, m.getConfig().getString("Messages.blacklist-message").replace("@cmd", command));
        	return true;
        }
        if(m.getMethods().returnWhiteList(p, command)){
        	m.getMethods().tellMESSAGE(p, m.getConfig().getString("Messages.white-message").replace("@cmd", command));
        	return true;
        }
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command);
        m.getMethods().tellMESSAGE(p, m.getConfig().getString("Messages.Complete", "&9* &dAConsole &9* &7Пожалуйста укажите сообщение Messages/Spy/enable").replace("@cmd", command));
        m.getMethods().writeLOG(p, command);
        return false;
	}
}
