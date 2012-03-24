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
		harass.getServer().getScheduler().scheduleAsyncDelayedTask(harass, new Runnable() {
			public void run() {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 10));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 999999999, 10));
				harass.staff.messageStaffT("Re-enabled potion effects on " + player.getName(), true);
			}
		}, 20L);
	}

}
