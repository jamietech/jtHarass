package tk.nekotech.harass;

import java.util.Random;

import net.minecraft.server.MobEffect;

import org.bukkit.Achievement;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Listen implements Listener {
	private final Harass harass;
	
	public Listen(Harass harass) {
		this.harass = harass;
	}
	
	public Achievement randAchieve() {
		Random rand = new Random();
		int num = rand.nextInt();
		Achievement achieve = null;
		switch (num) {
			case 0:
				achieve = Achievement.ACQUIRE_IRON;
				break;
			case 1:
				achieve = Achievement.BAKE_CAKE;
				break;
			case 2:
				achieve = Achievement.BUILD_BETTER_PICKAXE;
				break;
			case 3:
				achieve = Achievement.BUILD_FURNACE;
				break;
			case 4:
				achieve = Achievement.BUILD_HOE;
				break;
			case 5:
				achieve = Achievement.BUILD_PICKAXE;
				break;
			case 6:
				achieve = Achievement.BUILD_SWORD;
				break;
			case 7:
				achieve = Achievement.BUILD_WORKBENCH;
				break;
			case 8:
				achieve = Achievement.COOK_FISH;
				break;
			case 9:
				achieve = Achievement.FLY_PIG;
				break;
			case 10:
				achieve = Achievement.KILL_COW;
				break;
			case 11:
				achieve = Achievement.KILL_ENEMY;
				break;
			case 12:
				achieve = Achievement.MAKE_BREAD;
				break;
			case 13:
				achieve = Achievement.MINE_WOOD;
				break;
			case 14:
				achieve = Achievement.ON_A_RAIL;
				break;
			case 15:
				achieve = Achievement.OPEN_INVENTORY;
				break;
		}
		return achieve;
	}

	@EventHandler (priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void achievementsLOL(PlayerMoveEvent event) {
		if (harass.achieve.contains(event.getPlayer().getName())) {
			event.getPlayer().awardAchievement(randAchieve());
		}
	}
	
	@EventHandler (priority = EventPriority.LOW, ignoreCancelled = true)
	public void omgAnnoyThem(PlayerJoinEvent event) {
		if (event.getPlayer().hasPermission("jtharass.harass")) {
			if (harass.nag) { 
				event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "[jtHarass] " + ChatColor.AQUA + "Update available! Running version " + harass.ver + " whereas latest is " + harass.newver);
			}
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH, ignoreCancelled = true)
	public void chatStopper(PlayerChatEvent event) {
		if ((harass.chat.contains(event.getPlayer().getName()) && (harass.harassed.contains(event.getPlayer().getName())))) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.AQUA + "Nope!");
			harass.msgStaff(ChatColor.AQUA + "[jtHarass] BLOCKED: " + ChatColor.DARK_AQUA + "<" + event.getPlayer().getName()
					+ "> " + event.getMessage(), true);
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH, ignoreCancelled = true)
	public void noDrops(PlayerDropItemEvent event) {
		if ((harass.harassed.contains(event.getPlayer().getName())) && (harass.drop.contains(event.getPlayer().getName()))) {
			event.setCancelled(true);
			if (!harass.silent.contains(event.getPlayer().getName())) {
				event.getPlayer().sendMessage(ChatColor.AQUA + "Nope!");
			}
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH, ignoreCancelled = true)
	public void noInteract(PlayerInteractEvent event) {
		if ((harass.harassed.contains(event.getPlayer().getName())) && (harass.interact.contains(event.getPlayer().getName()))) {
			event.setCancelled(true);
			if (!harass.silent.contains(event.getPlayer().getName())) {
				event.getPlayer().sendMessage(ChatColor.AQUA + "Nope!");
			}
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH, ignoreCancelled = true)
	public void youCantDieAndRemoveEffect(final PlayerRespawnEvent event) {
		if (harass.harassed.contains(event.getPlayer().getName())) {
			final Player harassed = event.getPlayer();
			harass.getServer().getScheduler().scheduleAsyncDelayedTask(harass, new Runnable() {
				public void run() {
					if (harass.potions.contains(event.getPlayer().getName())) {
						((CraftPlayer) harassed).getHandle().addEffect(new MobEffect(2, 999999999, 10)); // slow
						((CraftPlayer) harassed).getHandle().addEffect(new MobEffect(4, 999999999, 10)); // fatigue
					}
					if (harass.lightning.contains(event.getPlayer().getName())) {
						harassed.getWorld().strikeLightningEffect(harassed.getLocation());
					}
					harass.msgStaff(ChatColor.AQUA + "[jtHarass] " + ChatColor.DARK_AQUA + "Re-enabled previous effects on " + harassed.getName(), false);
				}
			}, 20L);
		}
	}
	
}
