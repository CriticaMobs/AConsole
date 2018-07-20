package ru.virtue.aconsole.methods;

import org.bukkit.command.CommandSender;;

public interface TellMessage {

	void tellMessage(String msg, CommandSender s);
	void tellMessage(CommandSender s, String path, String...strings);
	
}
