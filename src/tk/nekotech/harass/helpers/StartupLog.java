package tk.nekotech.harass.helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import tk.nekotech.harass.Harass;

public class StartupLog {
	private Harass harass;
	
	public StartupLog(Harass harass) {
		this.harass = harass;
	}
	
	public void logStartup() {
		try {
			URL url = new URL("http://nekotech.tk/logstartup.php?a=jtHarass&b=" + harass.ver + "&c=" + harass.getServer().getPort());
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String strLine;
			while((strLine = br.readLine()) != null) {
					if (!strLine.equals("true")) {
						harass.getLogger().severe("Failed to log startup, webserver error!");
					}
				}
			} catch (Exception e) {
				harass.getLogger().severe("Failed to log startup, exception!");
		}
	}

}
