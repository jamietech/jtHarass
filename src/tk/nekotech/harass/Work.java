package tk.nekotech.harass;

import java.util.ArrayList;

import net.minecraft.server.MobEffect;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Work implements CommandExecutor {
	private Harass harass;
	
	public Work(Harass harass) {
		this.harass = harass;
	}
	
	public void omgBad(Player p) {
		((CraftPlayer) p).getHandle().addEffect(new MobEffect(2, 999999999, 10)); // slow
		((CraftPlayer) p).getHandle().addEffect(new MobEffect(4, 999999999, 10)); // fatigue
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		final ChatColor red = ChatColor.RED;
		final ChatColor blue = ChatColor.DARK_AQUA;
		final ChatColor aqua = ChatColor.AQUA;
		final String tag = blue + "[jtHarass] " + aqua;
		if ((sender.hasPermission("jtharass.harass") || (sender.isOp()))) {
			if (args.length == 0) {
				sender.sendMessage(tag + "You need to specify a player and/or flags!");
			}
			final String s = sender.getName();
			ArrayList<String> h = harass.harassed;
			if (!args[0].startsWith("-")) {
				final Player p = harass.getServer().getPlayer(args[1]);
				if (p == null) {
					sender.sendMessage(tag + "That player is not online, try again!");
				} else {
					if (args[0].equalsIgnoreCase("-help")) {
						sender.sendMessage(tag + "Welcome to the jtHarass system! This system is designed to train bad players.");
						sender.sendMessage(tag + "Flags: l-lightning c-chat p-potions c-chat d-drop i-interact s-silent");
						sender.sendMessage(tag + "Silent will not spam the user, interact will stop block changes");
						sender.sendMessage(tag + "Drop will stop item drops, chat will stop chat");
						StringBuilder perms = new StringBuilder();
						final ChatColor green = ChatColor.GREEN;
						if (sender.hasPermission("jtharass.lightning")) perms.append(green + "Lightning"); else perms.append(red + "Lightning");
						if (sender.hasPermission("jtharass.chat")) perms.append(green + " Chat"); else perms.append(red + " Chat");
						if (sender.hasPermission("jtharass.potions")) perms.append(green + " Potions"); else perms.append(red + " Potions");
						if (sender.hasPermission("jtharass.chat")) perms.append(green + " Chat"); else perms.append(red + " Chat");
						if (sender.hasPermission("jtharass.drop")) perms.append(green + " Drop"); else perms.append(red + " Drop");
						if (sender.hasPermission("jtharass.interact")) perms.append(green + " Interact"); else perms.append(red + " Interact");
						if (sender.hasPermission("jtharass.silent")) perms.append(green + " Silent"); else perms.append(red + " Silent");
						sender.sendMessage(tag + "You can do the following: " + perms.toString());
					}
					boolean fail = false;
					StringBuilder reason = new StringBuilder();
					StringBuilder effects = new StringBuilder();
					if (args[0].contains("l")) {
						if (sender.hasPermission("jtharass.lightning")) {
							harass.lightning.add(p.toString());
							if (effects.length() == 0) effects.append("Lightning"); else effects.append(", Lightning");
						} else {
							fail = true;
							reason.append(red + " Lightning ");
						}
					}
					if (args[0].contains("c")) {
						if (sender.hasPermission("jtharass.chat")) {
							harass.chat.add(p.toString());
							if (effects.length() == 0) effects.append("Chat"); else effects.append(", Chat");
						} else {
							fail = true;
							reason.append(red + " Chat ");
						}
					}
					if (args[0].contains("p")) {
						if (sender.hasPermission("jtharass.potions")) {
							harass.potions.add(p.toString());
							omgBad(p);
							if (effects.length() == 0) effects.append("Potions"); else effects.append(", Potions");
						} else {
							fail = true;
							reason.append(red + " Potions ");
						}
					}
					if (args[0].contains("c")) {
						if (sender.hasPermission("jtharass.chat")) {
							harass.chat.add(p.toString());
							if (effects.length() == 0) effects.append("Chat"); else effects.append(", Chat");
						} else {
							fail = true;
							reason.append(red + " Chat ");
						}
					}
					if (args[0].contains("d")) {
						if (sender.hasPermission("jtharass.drop")) {
							harass.drop.add(p.toString());
							if (effects.length() == 0) effects.append("Drop"); else effects.append(", Drop");
						} else {
							fail = true;
							reason.append(red + " Drop ");
						}
					}
					if (args[0].contains("i")) {
						if (sender.hasPermission("jtharass.interact")) {
							harass.interact.add(p.toString());
							if (effects.length() == 0) effects.append("Interact"); else effects.append(", Interact");
						} else {
							fail = true;
							reason.append(red + " Interact ");
						}
					}
					if (args[0].contains("s")) {
						if (sender.hasPermission("jtharass.silent")) {
							harass.silent.add(p.toString());
							if (effects.length() == 0) effects.append("Silent"); else effects.append(", Interact");
						} else {
							fail = true;
							reason.append(red + " Silent ");
						}
					}
					if (fail) {
						h.remove(p.toString());
						if (harass.chat.contains(p.toString())) {
							harass.chat.remove(p.toString());
						}
						if (harass.lightning.contains(p.toString())) {
							harass.lightning.remove(p.toString());
						}
						if (harass.potions.contains(p.toString())) {
							harass.potions.remove(p.toString());
						}
						if (harass.chat.contains(p.toString())) {
							harass.chat.remove(p.toString());
						}
						if (harass.drop.contains(p.toString())) {
							harass.drop.remove(p.toString());
						}
						if (harass.interact.contains(p.toString())) {
							harass.interact.remove(p.toString());
						}
						if (harass.silent.contains(p.toString())) {
							harass.silent.remove(p.toString());
						}
						harass.msgStaff(tag + s + " failed permission check. Removed effects from " + p + effects.toString(), true);
						sender.sendMessage(tag + "You failed the following permission checks:" + reason.toString());
					} else {
						harass.msgStaff(tag + s + " harasses " + p + " with the following effects " + effects.toString(), true);
					}
				}
			} else {
				final Player p = harass.getServer().getPlayer(args[0]);
				if (p == null) {
					sender.sendMessage(tag + "That player is not online, try again!");
				} else {
					if (!h.contains(p.toString())) {
						h.add(p.toString());
						harass.msgStaff(tag + s + " fully harassed (minus silent) " + p + "!", true);
						p.getWorld().strikeLightningEffect(p.getLocation());
						harass.chat.add(p.toString());
						harass.lightning.add(p.toString());
						harass.potions.add(p.toString());
						harass.chat.add(p.getName());
						harass.drop.add(p.getName());
						harass.interact.add(p.getName());
					} else {
						h.remove(p.toString());
						if (harass.chat.contains(p.toString())) {
							harass.chat.remove(p.toString());
						}
						if (harass.lightning.contains(p.toString())) {
							harass.lightning.remove(p.toString());
						}
						if (harass.potions.contains(p.toString())) {
							harass.potions.remove(p.toString());
						}
						if (harass.chat.contains(p.toString())) {
							harass.chat.remove(p.toString());
						}
						if (harass.drop.contains(p.toString())) {
							harass.drop.remove(p.toString());
						}
						if (harass.interact.contains(p.toString())) {
							harass.interact.remove(p.toString());
						}
						if (harass.silent.contains(p.toString())) {
							harass.silent.remove(p.toString());
						}
						harass.msgStaff(tag + s + " un-harassed " + p + "! Potion effects may still be active.", true);
					}
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You don't have access to that command, " + sender.getName() + "!");
		}
		return true;
	}

}
