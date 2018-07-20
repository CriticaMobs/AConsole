package ru.virtue.aconsole.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.virtue.aconsole.methods.Permission;
import ru.virtue.aconsole.methods.PlayersSpy;
import ru.virtue.aconsole.methods.ReturnList;
import ru.virtue.aconsole.methods.TellMessage;
import ru.virtue.aconsole.yml.YmlLanguage;
import ru.virtue.aconsole.yml.YmlLog;

public class Console implements CommandExecutor {
	
	private boolean chat;
	private Permission permission;
	private ReturnList returnList;
	private TellMessage tellMsg;
	private YmlLog log;
	private YmlLanguage lang;
	private PlayersSpy spy;
	
	public Console(Permission permission, ReturnList returnList, TellMessage tellMsg, YmlLog log, YmlLanguage lang, PlayersSpy spy) {
		this.permission = permission;
		this.returnList = returnList;
		this.tellMsg = tellMsg;
		this.lang = lang;
		this.log = log;
		this.spy = spy;
		chat = true;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if (Bukkit.getPlayer(s.getName()) == null) {
			tellMsg.tellMessage("&cOnly for players.", s);
            return true;
        }
        Player p = (Player) s;
        if(permission.hasPermission(p, "Command")){
        	return false;
        }
		if(args.length == 0){
			for(String i : lang.getConfig().getStringList("Command")){
				tellMsg.tellMessage(i, p);
			}
			return true;
		}
		if(args[0].equalsIgnoreCase("spy")){
			if(permission.hasPermission(p, "Spy")) {
				return false;
			}
            if (spy.getList().contains(s)) {
                spy.delPlayer((Player)s);
                tellMsg.tellMessage(lang.getMessage("SpyDisable"), s);
            }else{
            	spy.addPlayer((Player)s);
            	tellMsg.tellMessage(lang.getMessage("SpyEnable"), s);
	            return false;
            }
            return true;
		}
		if(args[0].equalsIgnoreCase("toggle")){
			if(permission.hasPermission(p, "Toggle")) {
				return true;
			}
			if(chat == false){
				tellMsg.tellMessage(lang.getMessage("ConsoleEnabled"), s);
				chat = true;
			}else{
				tellMsg.tellMessage(lang.getMessage("ConsoleDisabled"), s);
				chat = false;
			}
			return true;
		}
		if(args[0].equalsIgnoreCase("reload")){
			if(permission.hasPermission(p, "Reload")) {
				return true;
			}
			lang.load();
			
			tellMsg.tellMessage(p, "reload");
			return true;
		}
		if(chat == false){
			tellMsg.tellMessage(p, "ConsoleDisabled");
			return false;
		}
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < args.length; ++i) {
            st.append(args[i]).append(" ");
        }
        String command = st.toString().replace("/", "");
        if(permission.hasAllowPlayers(p)) {
        	tellMsg.tellMessage(p, "not_on_the_list");
        	return false;
        }
        if(permission.hasOp(p)) {
        	tellMsg.tellMessage(p, "no_op");
        	return false;
        }
        if(returnList.BlackList(p, command)){
        	tellMsg.tellMessage(p, "blacklist-message", "@cmd", command);
        	return false;
        }
        if(returnList.WhiteList(p, command)){
        	tellMsg.tellMessage(p, "white-message", "@cmd", command);
        	return false;
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        tellMsg.tellMessage(p, "Complete", "@cmd", command);
        log.writeLog(p, "[Console] /" + command);
        return false;
	}
}
