package ru.virtue.aconsole.methods;

import ru.virtue.aconsole.yml.YmlConfiguration;
import ru.virtue.aconsole.yml.YmlLanguage;

public class MethodsManager {

	private TellMessage tellMessage;
	private Permission permission;
	private CommandVerify commandVerify;
	private ReturnList returnList;
	private ColorText colorText;
	private PlayersSpy playersSpy;
	
	public MethodsManager(YmlLanguage lang, YmlConfiguration cfg) {
		colorText = new ColorTextImpl();
		tellMessage = new TellMessageImpl(colorText, lang);
		permission = new PermissionImpl(tellMessage, cfg);
		commandVerify = new CommandVerifyImpl(tellMessage);
		returnList = new ReturnListImpl(cfg);
		playersSpy = new PlayersSpyImpl();
	}
	
	public PlayersSpy getPlayersSpy() {
		return playersSpy;
	}
	
	public ReturnList getReturnList() {
		return returnList;
	}
	
	public ColorText getColorText() {
		return colorText;
	}
	
	public CommandVerify getCommandVerify() {
		return commandVerify;
	}
	
	public Permission getPermission() {
		return permission;
	}
	
	public TellMessage getTellMessage() {
		return tellMessage;
	}
}
