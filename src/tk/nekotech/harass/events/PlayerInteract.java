package tk.nekotech.harass.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import tk.nekotech.harass.Harass;

public class PlayerInteract implements Listener {
	private Harass harass;
	
	public PlayerInteract(Harass harass) {
		this.harass = harass;
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (harass.arraylists.INTERACT.contains(event.getPlayer().getName())) {
			event.setCancelled(true);
			if (!harass.arraylists.SILENT.contains(event.getPlayer().getName()))
				event.getPlayer().sendMessage(harass.colors.randColor() + "You may not interact with " + event.getClickedBlock().getType().toString().toLowerCase().replace("_", " ") + "!");
		}
	}

}
