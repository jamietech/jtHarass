package tk.nekotech.harass.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import tk.nekotech.harass.Harass;

public class PlayerChat implements Listener {
	private Harass harass;
	
	public PlayerChat(Harass harass) {
		this.harass = harass;
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerChat(PlayerChatEvent event) {
		if (harass.arraylists.CHAT.contains(event.getPlayer().getName())) {
			event.setCancelled(true);
			// TODO: Proper colouring
			harass.staff.messageStaffT("Blocked: <" + event.getPlayer().getName() + "> " + event.getMessage(), true);
		}
	}

}
