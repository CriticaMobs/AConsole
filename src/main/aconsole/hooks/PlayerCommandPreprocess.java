package aconsole.hooks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import aconsole.main.AConsole;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerCommandPreprocess implements Listener {

	private AConsole m;
	
	public PlayerCommandPreprocess(AConsole aConsole) {
		m = aConsole;
	}
	
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
    	if(e.isCancelled()){
    		return;
    	}
    	if(m.getMethods().readCommand(e.getMessage())){
    		m.getMethods().tellMESSAGE(e.getPlayer(), m.getConfig().getString("Messages.sudo"));
    		m.getMethods().tellMESSAGE(e.getPlayer(), "&9* &dAConsole &9* &7�� �� ������ ������������ ��� ������� �� ������� ������");
    		e.setCancelled(true);
    	}
    	if(m.getConfig().getBoolean("Log.global")){
    		m.getMethods().writeLOG(e.getPlayer(), "[CHAT] " + e.getMessage());
    	}
        for (String ignore : m.getConfig().getStringList("ignore.commands")) {
        	if(e.getMessage().split(" ")[0].equalsIgnoreCase("/" + ignore)){
        		return;
        	}
        }
        if(m.getConfig().getStringList("ignore.admins").contains(e.getPlayer().getName())){
        	return;
        }
        for(Player p : m.spy) {
        	if(p == e.getPlayer()) {
        		return;
        	}
        	m.getMethods().tellMESSAGE(p, m.getConfig().getString("Messages.Spy.message").replace("@command", e.getMessage()).replace("@player", e.getPlayer().getName()).replace("@prefix", PermissionsEx.getUser(e.getPlayer().getName()).getPrefix()));
        }
    }

}
