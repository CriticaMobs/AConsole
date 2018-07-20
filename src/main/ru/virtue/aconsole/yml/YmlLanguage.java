package ru.virtue.aconsole.yml;

import java.text.MessageFormat;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class YmlLanguage extends YmlFile
{
	private Map<String, String> messages;

	public YmlLanguage(JavaPlugin plugin) {
		super(plugin, "language", false);
		this.loadLanguage();
	}
	@Override
	public void load() {
		super.load();
		this.loadLanguage();
	}
	
	private void loadLanguage() {
		messages = cfg.getKeys(false).stream().filter(cfg::isString).collect(Collectors.toMap(Function.identity(), s -> ChatColor.translateAlternateColorCodes('&', cfg.getString(s))));
	}
	
	public String getMessage(String path, String...strings) {
		String msg = messages.get(path);
        if (strings.length > 0) {
            msg = MessageFormat.format(msg, (Object[]) strings);
        }
		return msg;
	}

}
