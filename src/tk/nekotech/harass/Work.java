package tk.nekotech.harass;

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
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if ((sender.hasPermission("jtharass.harass") || (sender.isOp()))) {
			final Player player = harass.getServer().getPlayer(args[0]);
			
			if (args.length == 0) {
				sender.sendMessage(ChatColor.AQUA + "[jtHarass] " + ChatColor.DARK_AQUA + "You need to specify a player!");
			}
			if (player == null) {
				sender.sendMessage(ChatColor.AQUA + "[jtHarass] " + ChatColor.DARK_AQUA + "That player is offline or matches multiple");
			} else {
				if (harass.harassed.contains(player.getName())) {
					harass.harassed.remove(player.getName());
					harass.msgStaff(ChatColor.AQUA + "[jtHarass] " + ChatColor.DARK_AQUA + sender.getName() + " unharasses " + player.getName() + "!", true);
					harass.msgStaff(ChatColor.AQUA + "[jtHarass] " + ChatColor.DARK_AQUA + "Please note that " + player.getName() + " still has potion effects active. Slay and respawn!", false);
				} else {
					harass.harassed.add(player.getName());
					harass.msgStaff(ChatColor.AQUA + "[jtHarass] " + ChatColor.DARK_AQUA + sender.getName() + " harassed " + player.getName() + "!", true);
					player.getWorld().strikeLightningEffect(player.getLocation());
					((CraftPlayer) player).getHandle().addEffect(new MobEffect(2, 999999999, 10)); // slow
					((CraftPlayer) player).getHandle().addEffect(new MobEffect(4, 999999999, 10)); // fatigue
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You don't have access to that command, " + sender.getName() + "!");
		}
		return true;
	}

}
