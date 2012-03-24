package tk.nekotech.harass.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tk.nekotech.harass.Harass;

public class PlayerJoin implements Listener {
	private Harass harass;
	
	public PlayerJoin(Harass harass) {
		this.harass = harass;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (event.getPlayer().hasPermission(harass.permissions.ADMIN)) {
			if (harass.outOfDate) {
				event.getPlayer().sendMessage(harass.staff.OUTOFDATEMESSAGE);
			}
		}
	}

}
