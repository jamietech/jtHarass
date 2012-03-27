package tk.nekotech.harass.permissions;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import tk.nekotech.harass.Harass;

public class Permissions {
	private Harass harass;
	
	public Permissions(Harass harass) {
		this.harass = harass;
	}
	
	public String ADMIN = "jtharass.admin";
	public String ADMIN_INFO = null;
	
	public String ACHIEVEMENT = "jtharass.achieve";
	public String ACHIEVEMENT_INFO = null;
	
	public String CHAT = "jtharass.chat";
	public String CHAT_INFO = null;
	
	public String DROP = "jtharass.drop";
	public String DROP_INFO = null;
	
	public String EMPTY = "jtharass.empty";
	public String EMPTY_INFO = null;
	
	public String INTERACT = "jtharass.interact";
	public String INTERACT_INFO = null;
	
	public String LIGHTNING = "jtharass.lightning";
	public String LIGHTNING_INFO = null;
	
	public String POTIONS = "jtharass.potions";
	public String POTIONS_INFO= null;
	
	public String SILENT = "jtharass.silent";
	public String SILENT_INFO= null;
	
	public void genInfo() {
		ADMIN_INFO = "Allows the player to use the base /harass command and recieve information about harass";
		ACHIEVEMENT_INFO = "Allows use of " + harass.flags.ACHIEVEMENT + " flag";
		CHAT_INFO = "Allows use of " + harass.flags.CHAT + " flag";
		DROP_INFO = "Allows use of " + harass.flags.DROP + " flag";
		EMPTY_INFO = "Allows use of " + harass.flags.EMPTY + " flag";
		INTERACT_INFO = "Allows use of " + harass.flags.INTERACT + " flag";
		LIGHTNING_INFO = "Allows use of " + harass.flags.LIGHTNING + " flag";
		POTIONS_INFO = "Allows use of " + harass.flags.POTIONS + " flag";
		SILENT_INFO = "Allows the use of " + harass.flags.SILENT + " flag";
	}
	
	public String getPlayerPermissions(CommandSender a) {
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
