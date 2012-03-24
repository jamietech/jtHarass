package tk.nekotech.harass.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tk.nekotech.harass.Harass;

public class PlayerMove implements Listener {
	private Harass harass;
	
	public PlayerMove(Harass harass) {
		this.harass = harass;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerMove(PlayerMoveEvent event) {
		if (harass.arraylists.ACHIEVEMENT.contains(event.getPlayer().getName())) {
			event.getPlayer().awardAchievement(harass.achievements.randAchievement());
		}
	}

}
