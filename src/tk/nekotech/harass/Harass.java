package tk.nekotech.harass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.*;

import tk.nekotech.harass.Listen;

public class Harass extends JavaPlugin {
	
	public Logger log = Logger.getLogger("Minecraft");
	PluginDescriptionFile pdfFile = this.getDescription();
	
	public ArrayList<String> harassed = new ArrayList<String>();
	public ArrayList<String> lightning = new ArrayList<String>();
	public ArrayList<String> potions = new ArrayList<String>();
	public ArrayList<String> chat = new ArrayList<String>();
	public ArrayList<String> drop = new ArrayList<String>();
	public ArrayList<String> silent = new ArrayList<String>();
	public ArrayList<String> interact = new ArrayList<String>();

	@Override
	public void onDisable() {
		log.info("[jtHarass] Disabled!");
	}

	@Override
	public void onEnable() {
		log.info("[jtHarass] Enabled!");
		
		this.getCommand("harass").setExecutor(new Work(this));
		
		final PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new Listen(this), this);
		
		/*try {
			URL url = new URL("http://nekotech.tk/logstartup.php?a=jtHarass&b=0.2");
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String strLine;
			while((strLine = br.readLine()) != null) {
					if (!strLine.equals("true")) {
						log.severe("[jtHarass] Failed to log startup, webserver error!");
					}
				}
			} catch (Exception e) {
				log.severe("[jtHarass] Failed to log startup, exception!");
		}*/
	}

	public void msgStaff(String message, boolean dolog) {
		for (final Player player : this.getServer().getOnlinePlayers()) {
			if ((player.hasPermission("jtharass.harass")) || (player.isOp())) {
				player.sendMessage(message);
			}
		}
		if (dolog) {
			log.info(ChatColor.stripColor(message));
		}
	}
	
}
