package tk.nekotech.harass.helpers;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.nekotech.harass.Harass;

public class Potions {
	private Harass harass;
	
	public Potions(Harass harass) {
		this.harass = harass;
	}
	
	public void enablePotions(final Player player) {
		if (harass.arraylists.POTIONS.contains(player.getName())) {
			harass.getServer().getScheduler().scheduleAsyncDelayedTask(harass, new Runnable() {
				public void run() {
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 10));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 999999999, 10));
					harass.staff.messageStaffT("Re-enabled potion effects on " + player.getName(), true);
				}
			}, 20L);
		} else {
			harass.arraylists.POTIONS.add(player.getName());
			harass.getServer().getScheduler().scheduleAsyncDelayedTask(harass, new Runnable() {
				public void run() {
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 10));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 999999999, 10));
					harass.staff.messageStaffT("Enabled potion effects on " + player.getName(), true);
				}
			}, 20L);
		}
	}
	
	public void disablePotions(final Player player) {
		if (harass.arraylists.POTIONS.contains(player.getName())) {
			harass.arraylists.POTIONS.remove(player.getName());
			harass.getServer().getScheduler().scheduleAsyncDelayedTask(harass, new Runnable() {
				public void run() {
					player.removePotionEffect(PotionEffectType.SLOW);
					player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
					harass.staff.messageStaffT("Removed potion effects from " + player.getName(), true);
				}
			}, 20L);
		} else {
			harass.staff.messageStaffT("Potion effect removal called on " + player.getName() + " whilst they had no harass active!", true);
		}
	}

}
