package aconsole.methods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import aconsole.main.AConsole;

public class Methods implements IMethods {
	
	private AConsole m;
	
	public Methods(AConsole main) {
		m = main;
	}
	
	public void writeLOG(Player p, String st) {
        try {
        	FileWriter fw = new FileWriter(m.log);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String date = dtf.format(LocalDate.now());
            fw.write(ChatColor.translateAlternateColorCodes('&', m.getConfig().getString("Log.save").replace("%date%", date).replace("%command%", st).replace("%name%", p.getName())));
            fw.close();
        }
        catch (IOException ex) {
            Bukkit.getLogger().info("[AConsole] §cERROR §f - " + ex.toString());
        }
    }
	public void tellMESSAGE(CommandSender p, String msg){
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	public boolean havePermission(Player p){
    	if(checkPermission(p, "command")) {
    		return true;
    	}
        if(m.getConfig().getBoolean("Allow-Players")){
        	if(!m.getConfig().getStringList("Players").contains(p.getName())){
        		tellMESSAGE(p, m.getConfig().getString("Messages.not_on_the_list", "&9* &dAConsole &9* &7Пожалуйста укажите сообщение Messages/not_on_the_list"));
        		return true;
        	}
        }else{
            if(m.getConfig().getBoolean("OpPlayers")){
            	if(!p.isOp()){
            		tellMESSAGE(p, m.getConfig().getString("Messages.no_op", "&9* &dAConsole &9* &7Пожалуйста укажите сообщение Messages/no_op"));
            		return true;
            	}
            }
        }
		return false;
	}
	public boolean readCommand(String msg){
		String w = msg.split(" ")[0].toLowerCase();
		if(w.equalsIgnoreCase("/console") || w.equalsIgnoreCase("/aconsole:console") || w.equalsIgnoreCase("/aconsole") || w.equalsIgnoreCase("/aconsole:aconsole")){
			return false;
		}
		if(msg.replace(" ", "").toLowerCase().contains("console")){
			return true;
		}
		return false;
	}
	public void loadConfig(){
        m.log = new File(m.getDataFolder().getAbsolutePath() + File.separator + "log.yml");
        if(m.getConfig().getBoolean("Log.enabled")){
            if (!m.log.exists()) {
                try {
                    m.log.createNewFile();
                }
                catch (IOException ex) {
                    Bukkit.getLogger().info("[AConsole] §cERROR §f - " + ex.toString());
                }
            }
        }
		if(!new File(m.getDataFolder(), "config.yml").exists()){
			m.saveDefaultConfig();
		}
	}

	@Override
	public boolean returnWhiteList(Player p, String command) {
        if(m.getConfig().getBoolean("white-list")) {
        	List<String> wl = m.getConfig().getStringList("whitelist");
            if (wl.contains(command.toLowerCase())) {
            	return false;
            }
            return true;
        }
		return false;
	}

	@Override
	public boolean returnBlackList(Player p, String command) {
        if(m.getConfig().getBoolean("black-list")){
        	List<String> bl = m.getConfig().getStringList("blacklist");
        	if (bl.contains(command)) {
                return true;
            }
        }
		return false;
	}

	@Override
	public boolean checkPermission(Player p, String command) {
		if(m.getConfig().contains("Permissions." + command)) {
			if(m.getConfig().getBoolean("Permissions." + command + ".enabled")) {
				if(p.hasPermission(m.getConfig().getString("Permissions." + command + ".permission"))) {
					return false;
				}else {
					tellMESSAGE(p, m.getConfig().getString("Permissions." + command + ".message"));
					return true;
				}
			}
		}
		return false;
	}
}
