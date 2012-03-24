package tk.nekotech.harass.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import tk.nekotech.harass.Harass;

public class PlayerRespawn implements Listener {
	private Harass harass;
	
	public PlayerRespawn(Harass harass) {
		this.harass = harass;
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		if (harass.arraylists.HARASSED.contains(event.getPlayer().getName())) {
			if (harass.arraylists.POTIONS.contains(event.getPlayer().getName())) {
				harass.potions.enablePotions(event.getPlayer());
			}
		}
	}

}
