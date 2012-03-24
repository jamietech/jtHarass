package tk.nekotech.harass.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import tk.nekotech.harass.Harass;

public class PlayerDropItem implements Listener {
	private Harass harass;
	
	public PlayerDropItem(Harass harass) {
		this.harass = harass;
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		if (harass.arraylists.DROP.contains(event.getPlayer().getName())) {
			event.setCancelled(true);
			if (!harass.arraylists.SILENT.contains(event.getPlayer().getName()))
				event.getPlayer().sendMessage(harass.colors.randColor() + "You may not drop " + event.getItemDrop().getType().toString().toLowerCase().replace("_", "") + "!");
		}
	}

}
