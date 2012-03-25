package tk.nekotech.harass.helpers;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.nekotech.harass.Harass;

public class Harassment {
	private Harass harass;
	
	public Harassment(Harass harass) {
		this.harass = harass;
	}
	
	public boolean isHarassed(Player player) {
		if (harass.arraylists.HARASSED.contains(player.getName())) {
			return true;
		}
		return false;
	}
	
	public void addHarass(CommandSender admin, Player player, String flags) {
		if (isHarassed(player)) {
			harass.staff.messageStaffT(player.getName() + " was called to be harassed by " + admin.getName() + " but they're already harassed! (API call error!)", true);
			return;
		}
		harass.arraylists.HARASSED.add(player.getName());
		String f = flags.toLowerCase();
		StringBuilder effects = new StringBuilder();
		if (f.contains(harass.flags.ACHIEVEMENT)) {
			if (admin.hasPermission(harass.permissions.ACHIEVEMENT)) {
				harass.arraylists.ACHIEVEMENT.add(player.getName());
				effects.append(ChatColor.GREEN + " Achievement");
			} else {
				effects.append(ChatColor.RED + " Achievement");
			}
		}
		if (f.contains(harass.flags.CHAT)) {
			if (admin.hasPermission(harass.permissions.ADMIN)) {
				harass.arraylists.CHAT.add(player.getName());
				effects.append(ChatColor.GREEN + " Chat");
			} else {
				effects.append(ChatColor.RED + " Chat");
			}
		}
		if (f.contains(harass.flags.DROP)) {
			if (admin.hasPermission(harass.permissions.DROP)) {
				harass.arraylists.DROP.add(player.getName());
				effects.append(ChatColor.GREEN + " Drop");
			} else {
				effects.append(ChatColor.RED + " Drop");
			}
		}
		if (f.contains(harass.flags.EMPTY)) {
			if (admin.hasPermission(harass.permissions.EMPTY)) {
				player.getInventory().clear();
				effects.append(ChatColor.GREEN + " Empty");
			} else {
				effects.append(ChatColor.RED + " Empty");
			}
		}
		if (f.contains(harass.flags.INTERACT)) {
			if (admin.hasPermission(harass.permissions.INTERACT)) {
				harass.arraylists.INTERACT.add(player.getName());
				effects.append(ChatColor.GREEN + " Interact");
			} else {
				effects.append(ChatColor.RED + " Interact");
			}
		}
		if (f.contains(harass.flags.LIGHTNING)) {
			if (admin.hasPermission(harass.permissions.LIGHTNING)) {
				player.getWorld().strikeLightningEffect(player.getLocation());
				effects.append(ChatColor.GREEN + " Lightning");
			} else {
				effects.append(ChatColor.RED + " Lightning");
			}
		}
		if (f.contains(harass.flags.POTIONS)) {
			if (admin.hasPermission(harass.permissions.POTIONS)) {
				harass.potions.enablePotions(player);
				effects.append(ChatColor.GREEN + " Potions");
			} else {
				effects.append(ChatColor.RED + " Potions");
			}
		}
		if (f.contains(harass.flags.SILENT)) {
			if (admin.hasPermission(harass.permissions.SILENT)) {
				harass.arraylists.SILENT.add(player.getName());
				effects.append(ChatColor.GREEN + " Silent");
			} else {
				effects.append(ChatColor.RED + " Silent");
			}
		}
		harass.staff.messageStaffT(admin.getName() + " harasses " + player.getName() + " with the following effects:", true);
		harass.staff.messageStaff(effects.toString(), true);
	}
	
	public void unHarass(CommandSender admin, Player player) {
		if (!isHarassed(player)) {
			harass.staff.messageStaffT(player.getName() + " was called to be unharassed, no active harassment found!", true);
			return;
		}
		StringBuilder effects = new StringBuilder();
		harass.arraylists.HARASSED.remove(player.getName());
		if (harass.arraylists.ACHIEVEMENT.contains(player.getName())) {
			harass.arraylists.ACHIEVEMENT.remove(player.getName());
			effects.append(" Achievement");
		}
		if (harass.arraylists.CHAT.contains(player.getName())) {
			harass.arraylists.CHAT.remove(player.getName());
			effects.append(" Chat");
		}
		if (harass.arraylists.DROP.contains(player.getName())) {
			harass.arraylists.DROP.remove(player.getName());
			effects.append(" Drop");
		}
		if (harass.arraylists.INTERACT.contains(player.getName())) {
			harass.arraylists.INTERACT.remove(player.getName());
			effects.append(" Interact");
		}
		if (harass.arraylists.POTIONS.contains(player.getName())) {
			harass.potions.disablePotions(player);
			effects.append(" Potions");
		}
		if (harass.arraylists.SILENT.contains(player.getName())) {
			harass.arraylists.SILENT.remove(player.getName());
			effects.append(" Silent");
		}
		if (effects.length() == 0) {
			harass.staff.messageStaffT(admin.getName() + " unharasses " + player.getName(), true);
		} else {
			harass.staff.messageStaffT(admin.getName() + " unharasses " + player.getName() + " and removes the following effects: ", true);
			harass.staff.messageStaff(ChatColor.AQUA + effects.toString(), true);
		}
	}

}
