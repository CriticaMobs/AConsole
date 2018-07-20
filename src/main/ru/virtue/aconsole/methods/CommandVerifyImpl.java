package ru.virtue.aconsole.methods;

import org.bukkit.entity.Player;

public class CommandVerifyImpl implements CommandVerify{

	private TellMessage tellmsg;
	
	public CommandVerifyImpl(TellMessage tellmsg) {
		this.tellmsg = tellmsg;
	}
	
	@Override
	public boolean сommandVerify(Player p, String command) {
		String w = command.split(" ")[0].toLowerCase();
		if(w.equalsIgnoreCase("/console") || w.equalsIgnoreCase("/aconsole:console") || w.equalsIgnoreCase("/aconsole") || w.equalsIgnoreCase("/aconsole:aconsole")){
			return false;
		}
		if(command.replace(" ", "").toLowerCase().contains("console")){
			tellmsg.tellMessage(p, "Messages.sudo");
			return true;
		}
		return false;
	}

}
