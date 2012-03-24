package tk.nekotech.harass.helpers;

import java.util.Random;

import org.bukkit.Achievement;

public class Achievements {
	
	public Achievement randAchievement() {
		Random rand = new Random();
		int num = rand.nextInt();
		Achievement achieve = null;
		switch (num) {
			case 0:
				achieve = Achievement.ACQUIRE_IRON;
				break;
			case 1:
				achieve = Achievement.BAKE_CAKE;
				break;
			case 2:
				achieve = Achievement.BUILD_BETTER_PICKAXE;
				break;
			case 3:
				achieve = Achievement.BUILD_FURNACE;
				break;
			case 4:
				achieve = Achievement.BUILD_HOE;
				break;
			case 5:
				achieve = Achievement.BUILD_PICKAXE;
				break;
			case 6:
				achieve = Achievement.BUILD_SWORD;
				break;
			case 7:
				achieve = Achievement.BUILD_WORKBENCH;
				break;
			case 8:
				achieve = Achievement.COOK_FISH;
				break;
			case 9:
				achieve = Achievement.FLY_PIG;
				break;
			case 10:
				achieve = Achievement.KILL_COW;
				break;
			case 11:
				achieve = Achievement.KILL_ENEMY;
				break;
			case 12:
				achieve = Achievement.MAKE_BREAD;
				break;
			case 13:
				achieve = Achievement.MINE_WOOD;
				break;
			case 14:
				achieve = Achievement.ON_A_RAIL;
				break;
			case 15:
				achieve = Achievement.OPEN_INVENTORY;
				break;
		}
		return achieve;
	}

}
