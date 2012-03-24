package tk.nekotech.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import tk.nekotech.harass.Harass;

public class HarassCommand implements CommandExecutor {
	private Harass harass;
	
	public HarassCommand(Harass harass) {
		this.harass = harass;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		// TODO: Command
		return false;
	}

}
