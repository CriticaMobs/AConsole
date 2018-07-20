package ru.virtue.aconsole.yml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class YmlLog extends YmlFile
{

	private YmlConfiguration cfg;
	
	public YmlLog(JavaPlugin main, YmlConfiguration cfg) {
		super(main, "log", true);
		this.cfg = cfg;
		load();
	}
	
	@Override
	public void load() {
		super.load();
	}
	
	public void deleteLog() {
		file.delete();
	}
	
	public void writeLog(Player p, String st) {
		FileWriter fw = null;
        try {
        	fw = new FileWriter(file, true);
        	BufferedWriter bw = new BufferedWriter(fw);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy|MM|dd");
            String date = dtf.format(LocalDate.now());
            bw.write(ChatColor.translateAlternateColorCodes('&', cfg.getConfig().getString("Log.save").replace("%date%", date).replace("%command%", st).replace("%name%", p.getName())));
            bw.newLine();
            fw.flush();
            bw.close();
        }
        catch (IOException ex) {
            Bukkit.getLogger().info("[AConsole] §cERROR §f - " + ex.toString());
        }
    }
	
}
