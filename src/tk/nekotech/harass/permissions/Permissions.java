package tk.nekotech.harass.permissions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Permissions {
	
	public String ADMIN = "jtharass.admin";
	public String ADMIN_INFO = "Allows the player to use the base /harass command and recieve information about harass";
	
	public String ACHIEVEMENT = "jtharass.achieve";
	public String ACHIEVEMENT_INFO = "Allows the player to use the 'a' flag to spam the target with achievements on move";
	
	public String CHAT = "jtharass.chat";
	public String CHAT_INFO = "Allows the player to use the 'c' flag which blocks the target from using chat";
	
	public String DROP = "jtharass.drop";
	public String DROP_INFO = "Allows the player to use the 'd' flag which blocks the target from dropping items";
	
	public String EMPTY = "jtharass.empty";
	public String EMPTY_INFO = "Allows the player to use the 'e' flag which empties the targets inventory";
	
	public String INTERACT = "jtharass.interact";
	public String INTERACT_INFO = "Allows the player to use the 'i' flag which blocks the target from interacting with the world";
	
	public String LIGHTNING = "jtharass.lightning";
	public String LIGHTNING_INFO = "Allows the player to use the 'l' flag which strikes the target with lightning";
	
	public String POTIONS = "jtharass.potions";
	public String POTIONS_INFO = "Allows the player to use the 'p' flag which adds potion effects to the target";
	
	public String SILENT = "jtharass.silent";
	public String SILENT_INFO = "Allows the player to use the 's' flag which stops sending of messages from harass to the target";
	
	public String getPlayerPermissions(Player a) {
		StringBuilder b = new StringBuilder();
		if (a.hasPermission(ACHIEVEMENT)) {
			b.append(ChatColor.GREEN + " Achievement");
		} else {
			b.append(ChatColor.RED + " Achievement");
		}
		if (a.hasPermission(CHAT)) {
			b.append(ChatColor.GREEN + " Chat");
		} else {
			b.append(ChatColor.RED + " Chat");
		}
		if (a.hasPermission(DROP)) {
			b.append(ChatColor.GREEN + " Drop");
		} else {
			b.append(ChatColor.RED + " Drop");
		}
		if (a.hasPermission(EMPTY)) {
			b.append(ChatColor.RED + " Empty");
		} else {
			b.append(ChatColor.RED + " Empty");
		}
		if (a.hasPermission(INTERACT)) {
			b.append(ChatColor.GREEN + " Interact");
		} else {
			b.append(ChatColor.RED + " Interact");
		}
		if (a.hasPermission(LIGHTNING)) {
			b.append(ChatColor.GREEN + " Lightning");
		} else {
			b.append(ChatColor.RED + " Lightning");
		}
		if (a.hasPermission(POTIONS)) {
			b.append(ChatColor.GREEN + " Potions");
		} else {
			b.append(ChatColor.RED + " Potions");
		}
		if (a.hasPermission(SILENT)) {
			b.append(ChatColor.GREEN + " Silent");
		} else {
			b.append(ChatColor.RED + " Silent");
		}
		return b.toString();
	}

}
