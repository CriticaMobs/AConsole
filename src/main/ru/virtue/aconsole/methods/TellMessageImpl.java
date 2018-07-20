package ru.virtue.aconsole.methods;

import org.bukkit.command.CommandSender;
import ru.virtue.aconsole.yml.YmlLanguage;

public class TellMessageImpl implements TellMessage{

	private ColorText ct;
	private YmlLanguage lang;
	
	public TellMessageImpl(ColorText ct, YmlLanguage lang) {
		this.lang = lang;
		this.ct = ct;
	}
	
	@Override
	public void tellMessage(String msg, CommandSender s) {
		s.sendMessage(ct.color(msg));
	}

	@Override
	public void tellMessage(CommandSender s, String path, String... strings) {
		try {
			String txt = lang.getMessage(path, strings);
			for(int i = 0; i < strings.length; i+=2) {
				txt = txt.replace(strings[i], strings[i+1]);
			}
			tellMessage(txt, s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
