package tk.nekotech.harass.helpers;

import org.bukkit.command.CommandSender;

import tk.nekotech.harass.Harass;

public class Flags {
	private Harass harass;
	
	public Flags(Harass harass) {
		this.harass = harass;
	}
	
	public String ACHIEVEMENT = "a";
	public String ACHIEVEMENT_DESC = "Spams the target player with achievements when they move.";
	
	public String CHAT = "c";
	public String CHAT_DESC = "Blocks the target player from talking in chat but shows staff blocked messages.";
	
	public String DROP = "d";
	public String DROP_DESC = "Blocks the target player from dropping items and alerts them if silent flag not active.";
	
	public String EMPTY = "e";
	public String EMPTY_DESC = "Empties the target players inventory upon entering harass mode.";
	
	public String INTERACT = "i";
	public String INTERACT_DESC = "Blocks the target player from interacting with the world and alerts them if silent flag not active.";
	
	public String LIGHTNING = "l";
	public String LIGHTNING_DESC = "Strikes the target player with lightning upon entering harass mode.";
	
	public String NODES = "n";
	public String NODES_DESC = "Reveals the players permission nodes.";
	
	public String POTIONS = "p";
	public String POTIONS_DESC = "Forces potion effects onto the target player upon entering harass mode and respawn.";
	
	public String SILENT = "s";
	public String SILENT_DESC = "Stops drop and interact cancel messages being from being sent to the target player.";
	
	public void setup() {
		harass.permissions.genInfo();
	}
	
	public String getPlayerFlags(CommandSender player) {
		String flags = "";
		if (player.hasPermission(harass.permissions.ACHIEVEMENT)) {
			flags += ACHIEVEMENT;
		}
		if (player.hasPermission(harass.permissions.CHAT)) {
			flags += CHAT;
		}
		if (player.hasPermission(harass.permissions.DROP)) {
			flags += DROP;
		}
		if (player.hasPermission(harass.permissions.EMPTY)) {
			flags += EMPTY;
		}
		if (player.hasPermission(harass.permissions.INTERACT)) {
			flags += INTERACT;
		}
		if (player.hasPermission(harass.permissions.LIGHTNING)) {
			flags += LIGHTNING;
		}
		if (player.hasPermission(harass.permissions.POTIONS)) {
			flags += POTIONS;
		}
		if (player.hasPermission(harass.permissions.SILENT)) {
			flags += SILENT;
		}
		if (flags == "") {
			return "No flags available!";
		} else {
			return flags + " + \"n\" for node information";
		}
	}
	
}
