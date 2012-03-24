package tk.nekotech.harass.helpers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import tk.nekotech.harass.Harass;

public class Staff {
	private Harass harass;
	
	public Staff(Harass harass) {
		this.harass = harass;
	}
	
	private ChatColor A = ChatColor.AQUA;
	private ChatColor DA = ChatColor.DARK_AQUA;
	public String OUTOFDATEMESSAGE = DA + "[jtHarass]" + A + " Server " + DA + "out of date" + A + ". Running version " + DA + harass.ver + A + " whereas latest is " + DA + harass.newver + A + " download new version from " + DA + "http://dev.bukkit.org/server-mods/jtharass"; 

	public void messageStaff(String message, boolean log) {
		for (Player player : harass.getServer().getOnlinePlayers()) {
			if (player.hasPermission(harass.permissions.ADMIN)) {
				player.sendMessage(message);
			}
		}
		if (log)
			harass.getLogger().info(ChatColor.stripColor(message));
	}
	
	public void messageStaffT(String message, boolean log) {
		for (Player player : harass.getServer().getOnlinePlayers()) {
			if (player.hasPermission(harass.permissions.ADMIN)) {
				player.sendMessage(ChatColor.DARK_AQUA + "[jtHarass]" + ChatColor.AQUA + " " + message);
			}
		}
		if (log)
			harass.getLogger().info(ChatColor.stripColor(message));
	}
	
}
