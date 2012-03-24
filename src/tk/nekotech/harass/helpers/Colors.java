package tk.nekotech.harass.helpers;

import java.util.Random;

import org.bukkit.ChatColor;

public class Colors {
	
	public ChatColor randColor() {
		ChatColor color = null;
		Random rand = new Random();
		int number = rand.nextInt(15);
		switch (number) {
			case 0:
				color = ChatColor.AQUA;
				break;
			case 1:
				color = ChatColor.BLACK;
				break;
			case 2:
				color = ChatColor.BLUE;
				break;
			case 3:
				color = ChatColor.DARK_AQUA;
				break;
			case 4:
				color = ChatColor.DARK_BLUE;
				break;
			case 5:
				color = ChatColor.DARK_GRAY;
				break;
			case 6:
				color = ChatColor.DARK_GREEN;
				break;
			case 7:
				color = ChatColor.DARK_PURPLE;
				break;
			case 8:
				color = ChatColor.DARK_RED;
				break;
			case 9:
				color = ChatColor.GOLD;
				break;
			case 10:
				color = ChatColor.GRAY;
				break;
			case 11:
				color = ChatColor.GREEN;
				break;
			case 12:
				color = ChatColor.LIGHT_PURPLE;
				break;
			case 13:
				color = ChatColor.RED;
				break;
			case 14:
				color = ChatColor.YELLOW;
				break;
		}
		return color;
	}

}
