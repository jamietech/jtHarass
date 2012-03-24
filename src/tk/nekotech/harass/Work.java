package tk.nekotech.harass;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Work implements CommandExecutor {
	private Harass harass;
	
	public Work(Harass harass) {
		this.harass = harass;
	}
	
	public void omgBad(Player p) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 10));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 999999999, 10));
		//((CraftPlayer) p).getHandle().addEffect(new MobEffect(2, 999999999, 10)); // slow
		//((CraftPlayer) p).getHandle().addEffect(new MobEffect(4, 999999999, 10)); // fatigue
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		final ChatColor red = ChatColor.RED;
		final ChatColor blue = ChatColor.DARK_AQUA;
		final ChatColor aqua = ChatColor.AQUA;
		final String tag = blue + "[jtHarass] " + aqua;
		if ((sender.hasPermission("jtharass.harass") || (sender.isOp()))) {
			final Player p;
			if (args.length == 0) {
				sender.sendMessage(tag + "You need to specify a player and/or flags!");
			} else {
				final String s = sender.getName();
				if (args[0].startsWith("-")) {
					if (args.length == 2) {
						p = harass.getServer().getPlayer(args[1]);
					} else {
						p = null;
					}
					boolean clear = false;
					if (args[0].equalsIgnoreCase("-help")) {
						sender.sendMessage(tag + "Welcome to the jtHarass system! This system is designed to train bad players.");
						sender.sendMessage(tag + "Flags: l-lightning c-chat p-potions d-drop i-interact s-silent e-empty a-achievement");
						sender.sendMessage(tag + "Silent will not spam the user, interact will stop block changes");
						sender.sendMessage(tag + "Drop will stop item drops, chat will stop chat");
						sender.sendMessage(tag + "Empty will clear their inventory, achievement will spam them with achievements");
						sender.sendMessage(tag + "Specifying just a player name will harass them with all features if un-harassed or un-harass them.");
						StringBuilder perms = new StringBuilder();
						final ChatColor green = ChatColor.GREEN;
						if (sender.hasPermission("jtharass.lightning")) perms.append(green + "Lightning"); else perms.append(red + "Lightning");
						if (sender.hasPermission("jtharass.chat")) perms.append(green + " Chat"); else perms.append(red + " Chat");
						if (sender.hasPermission("jtharass.potions")) perms.append(green + " Potions"); else perms.append(red + " Potions");
						if (sender.hasPermission("jtharass.drop")) perms.append(green + " Drop"); else perms.append(red + " Drop");
						if (sender.hasPermission("jtharass.interact")) perms.append(green + " Interact"); else perms.append(red + " Interact");
						if (sender.hasPermission("jtharass.silent")) perms.append(green + " Silent"); else perms.append(red + " Silent");
						if (sender.hasPermission("jtharass.empty")) perms.append(green + " Empty"); else perms.append(red + " Empty");
						if (sender.hasPermission("jtharass.achieve")) perms.append(green + " Achievement"); else perms.append(red + " Achievement");
						sender.sendMessage(tag + "You can do the following: " + perms.toString());
					} else {
						if (p == null) {
							sender.sendMessage(tag + "That player is not online, try again!");
						} else {
							boolean fail = false;
							StringBuilder reason = new StringBuilder();
							StringBuilder effects = new StringBuilder();
							if (args[0].contains("l")) {
								if (sender.hasPermission("jtharass.lightning")) {
									harass.lightning.add(p.getName().toString());
									if (effects.length() == 0) effects.append("Lightning"); else effects.append(", Lightning");
								} else {
									fail = true;
									reason.append(red + " Lightning ");
								}
							}
							if (args[0].contains("c")) {
								if (sender.hasPermission("jtharass.chat")) {
									harass.chat.add(p.getName().toString());
									if (effects.length() == 0) effects.append("Chat"); else effects.append(", Chat");
								} else {
									fail = true;
									reason.append(red + " Chat ");
								}
							}
							if (args[0].contains("p")) {
								if (sender.hasPermission("jtharass.potions")) {
									harass.potions.add(p.getName().toString());
									if (effects.length() == 0) effects.append("Potions"); else effects.append(", Potions");
								} else {
									fail = true;
									reason.append(red + " Potions ");
								}
							}
							if (args[0].contains("d")) {
								if (sender.hasPermission("jtharass.drop")) {
									harass.drop.add(p.getName().toString());
									if (effects.length() == 0) effects.append("Drop"); else effects.append(", Drop");
								} else {
									fail = true;
									reason.append(red + " Drop ");
								}
							}
							if (args[0].contains("i")) {
								if (sender.hasPermission("jtharass.interact")) {
									harass.interact.add(p.getName().toString());
									if (effects.length() == 0) effects.append("Interact"); else effects.append(", Interact");
								} else {
									fail = true;
									reason.append(red + " Interact ");
								}
							}
							if (args[0].contains("s")) {
								if (sender.hasPermission("jtharass.silent")) {
									harass.silent.add(p.getName().toString());
									if (effects.length() == 0) effects.append("Silent"); else effects.append(", Silent");
								} else {
									fail = true;
									reason.append(red + " Silent ");
								}
							}
							if (args[0].contains("e")) {
								if (sender.hasPermission("jtharass.empty")) {
									clear = true;
									if (effects.length() == 0) effects.append("ClearInv"); else effects.append(", ClearInv");
								} else {
									fail = true;
									reason.append(red + " Empty ");
								}
							}
							if (args[0].contains("a")) {
								if (sender.hasPermission("jtharass.achieve")) {
									harass.achieve.add(p.getName().toString());
									if (effects.length() == 0) effects.append("Achieve"); else effects.append(", Achieve");
								} else {
									fail = true;
									reason.append(red + " Achieve ");
								}
							}
							if (fail) {
								harass.harassed.remove(p.getName().toString());
								if (harass.chat.contains(p.getName().toString())) harass.chat.remove(p.getName().toString());
								if (harass.lightning.contains(p.getName().toString())) harass.lightning.remove(p.getName().toString());
								if (harass.potions.contains(p.getName().toString())) harass.potions.remove(p.getName().toString());
								if (harass.chat.contains(p.getName().toString())) harass.chat.remove(p.getName().toString());
								if (harass.drop.contains(p.getName().toString())) harass.drop.remove(p.getName().toString());
								if (harass.interact.contains(p.getName().toString())) harass.interact.remove(p.getName().toString());
								if (harass.silent.contains(p.getName().toString())) harass.silent.remove(p.getName().toString());
								if (harass.achieve.contains(p.getName().toString())) harass.achieve.remove(p.getName().toString());
								harass.msgStaff(tag + s + " failed permission check. Removed effects from " + p.getName().toString() + effects.toString(), true);
								sender.sendMessage(tag + "You failed the following permission checks:" + reason.toString());
							} else {
								harass.harassed.add(p.getName().toString());
								harass.msgStaff(tag + s + " harasses " + p.getName().toString() + " with the following effects " + effects.toString(), true);
								if (harass.potions.contains(p.getName().toString())) omgBad(p);
								if (harass.lightning.contains(p.getName().toString())) p.getWorld().strikeLightningEffect(p.getLocation());
								if (clear) p.getInventory().clear();
							}
						}
					}
				} else {
					p = harass.getServer().getPlayer(args[0]);
					if (p == null) {
						sender.sendMessage(tag + "That player is not online, try again!");
					} else {
						if (!harass.harassed.contains(p.getName().toString())) {
							if (sender.hasPermission("jtharass.complete")) {
								harass.harassed.add(p.getName().toString());
								harass.msgStaff(tag + s + " fully harassed (minus silent) " + p.getName().toString() + "!", true);
								omgBad(p);
								p.getWorld().strikeLightningEffect(p.getLocation());
								harass.chat.add(p.getName().toString());
								harass.lightning.add(p.getName().toString());
								harass.potions.add(p.getName().toString());
								harass.chat.add(p.getName().toString());
								harass.drop.add(p.getName().toString());
								harass.interact.add(p.getName().toString());
								harass.achieve.add(p.getName().toString());
							} else {
								sender.sendMessage(tag + "You do not have permission to do so! Use /harass -help");
							}
						} else {
							harass.harassed.remove(p.getName().toString());
							if (harass.potions.contains(p.getName().toString())) {
								harass.msgStaff(tag + sender.getName() + " unharasses " + p.getName().toString() + "! Potion effects may still be active!", true);
							} else {
								harass.msgStaff(tag + sender.getName() + " unharasses " + p.getName().toString() + "!", true);
							}
							if (harass.chat.contains(p.getName().toString())) {
								harass.chat.remove(p.getName().toString());
							}
							if (harass.lightning.contains(p.getName().toString())) {
								harass.lightning.remove(p.getName().toString());
							}
							if (harass.potions.contains(p.getName().toString())) {
								harass.potions.remove(p.getName().toString());
							}
							if (harass.chat.contains(p.getName().toString())) {
								harass.chat.remove(p.getName().toString());
							}
							if (harass.drop.contains(p.getName().toString())) {
								harass.drop.remove(p.getName().toString());
							}
							if (harass.interact.contains(p.getName().toString())) {
								harass.interact.remove(p.getName().toString());
							}
							if (harass.silent.contains(p.getName().toString())) {
								harass.silent.remove(p.getName().toString());
							}
							if (harass.achieve.contains(p.getName().toString())) {
								harass.achieve.remove(p.getName().toString());
							}
						}
					}
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You don't have access to that command, " + sender.getName() + "!");
		}
		return true;
	}

}
