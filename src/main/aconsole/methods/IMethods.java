package aconsole.methods;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface IMethods {

	void writeLOG(Player p, String st);
	void tellMESSAGE(CommandSender p, String msg);
	boolean havePermission(Player p);
	boolean readCommand(String msg);
	boolean returnWhiteList(Player p, String command);
	boolean returnBlackList(Player p, String command);
	boolean checkPermission(Player p, String command);
	void loadConfig();
}
