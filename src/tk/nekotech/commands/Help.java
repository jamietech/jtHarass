package tk.nekotech.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import tk.nekotech.harass.Harass;

public class Help {
	private Harass harass;
	
	public Help(Harass harass) {
		this.harass = harass;
	}
	
	private ChatColor A = ChatColor.AQUA;
	private ChatColor DA = ChatColor.DARK_AQUA;
	private String T = DA + "[jtHarass] " + A;
	
	public void parseHelp(CommandSender player, String flags) {
		if (flags == null) {
			player.sendMessage(T + "jtHarass allows you to \"Train\" bad players.");
			player.sendMessage(T + "For more information on the flags available to you, please say \"/harass -help flagshere\"");
			player.sendMessage(T + "You have the following flags available: " + harass.flags.getPlayerFlags(player));
		} else {
			int sent = 0;
			if (flags.contains(harass.flags.ACHIEVEMENT)) {
				player.sendMessage(T + harass.flags.ACHIEVEMENT + " - " + harass.flags.ACHIEVEMENT_DESC);
				sent++;
			}
			if (flags.contains(harass.flags.CHAT)) {
				player.sendMessage(T + harass.flags.CHAT + " - " + harass.flags.CHAT_DESC);
				sent++;
			}
			if (flags.contains(harass.flags.DROP)) {
				player.sendMessage(T + harass.flags.DROP + " - " + harass.flags.DROP_DESC);
				sent++;
			}
			if (flags.contains(harass.flags.EMPTY)) {
				player.sendMessage(T + harass.flags.EMPTY + " - " + harass.flags.EMPTY_DESC);
				sent++;
			}
			if (flags.contains(harass.flags.INTERACT)) {
				player.sendMessage(T + harass.flags.INTERACT + " - " + harass.flags.INTERACT_DESC);
				sent++;
			}
			if (flags.contains(harass.flags.LIGHTNING)) {
				player.sendMessage(T + harass.flags.LIGHTNING + " - " + harass.flags.LIGHTNING_DESC);
				sent++;
			}
			if (flags.contains(harass.flags.NODES)) {
				player.sendMessage(T + "Your nodes: " + harass.permissions.getPlayerPermissions(player));
				sent++;
			}
			if (flags.contains(harass.flags.POTIONS)) {
				player.sendMessage(T + harass.flags.POTIONS + " - " + harass.flags.POTIONS_DESC);
				sent++;
			}
			if (flags.contains(harass.flags.SILENT)) {
				player.sendMessage(T + harass.flags.SILENT + " - " + harass.flags.SILENT_DESC);
				sent++;
			}
			if (sent == 0) {
				player.sendMessage(T + "Couldn't parse any flags you provided.");
			}
		}
	}

}
