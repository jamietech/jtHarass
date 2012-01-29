package tk.nekotech.harass;

import net.minecraft.server.MobEffect;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Listen implements Listener {
	private final Harass harass;
	
	public Listen(Harass harass) {
		this.harass = harass;
	}

	@EventHandler (priority = EventPriority.HIGH)
	public void chatStopper(PlayerChatEvent event) {
		if (harass.harassed.contains(event.getPlayer().getName())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.AQUA + "Nope!");
			harass.msgStaff(ChatColor.AQUA + "[jtHarass] BLOCKED: " + ChatColor.DARK_AQUA + "<" + event.getPlayer().getName()
					+ "> " + event.getMessage(), true);
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void noDrops(PlayerDropItemEvent event) {
		if (harass.harassed.contains(event.getPlayer().getName())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.AQUA + "Nope!");
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void noEggs(PlayerEggThrowEvent event) {
		if (harass.harassed.contains(event.getPlayer().getName())) {
			event.getEgg().remove();
			event.getPlayer().sendMessage(ChatColor.AQUA + "Nope!");
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void noInteract(PlayerInteractEvent event) {
		if (harass.harassed.contains(event.getPlayer().getName())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.AQUA + "Nope!");
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void youCantDieAndRemoveEffect(PlayerRespawnEvent event) {
		if (harass.harassed.contains(event.getPlayer().getName())) {
			final Player harassed = event.getPlayer();
			harass.getServer().getScheduler().scheduleAsyncDelayedTask(harass, new Runnable() {
				public void run() {
					((CraftPlayer) harassed).getHandle().addEffect(new MobEffect(2, 999999999, 10)); // slow
					((CraftPlayer) harassed).getHandle().addEffect(new MobEffect(4, 999999999, 10)); // fatigue
					harass.msgStaff(ChatColor.AQUA + "[jtHarass] " + ChatColor.DARK_AQUA + "Re-enabled effects on " + harassed.getName(), false);
					harassed.getWorld().strikeLightningEffect(harassed.getLocation());
				}
			}, 20L);
		}
	}
	
}
