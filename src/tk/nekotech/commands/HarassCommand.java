package tk.nekotech.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.nekotech.harass.Harass;

public class HarassCommand implements CommandExecutor {
	private Harass harass;
	
	public HarassCommand(Harass harass) {
		this.harass = harass;
	}
	
	private ChatColor A = ChatColor.AQUA;
	private ChatColor DA = ChatColor.DARK_AQUA;
	private String T = DA + "[jtHarass] " + A;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender.hasPermission(harass.permissions.ADMIN)) {
			if (args.length == 0) {
				sender.sendMessage(T + "You need to specify a player and/or flags!");
				return true;
			}
			if (args[0].startsWith("-")) {
				if (args[0].replace("-", "").toLowerCase().equals("help")) {
					harass.help.parseHelp(sender, args[0].replace("-", ""));
					return true;
				}
				Player target = harass.getServer().getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(T + "Player " + DA + args[0] + A + " could not be found. Either matches too many players or not online!");
				} else {
					harass.harassment.addHarass(sender, target, args[0].replace("-", ""));
				}
			} else {
				Player target = harass.getServer().getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(T + "Player " + DA + args[0] + A + " could not be found. Either matches too many players or not online!");
				} else {
					String flags = harass.flags.ACHIEVEMENT + harass.flags.CHAT + harass.flags.DROP + harass.flags.INTERACT + harass.flags.LIGHTNING + harass.flags.POTIONS;
					harass.harassment.addHarass(sender, target, flags);
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You don't have access to that command!");
		}
		return true;
	}

}
