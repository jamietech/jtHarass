package tk.nekotech.harass.helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import tk.nekotech.harass.Harass;

public class Version {
	private Harass harass;

	public Version(Harass harass) {
		this.harass = harass;
	}
	
	public void checkVersion() {
		try {
			URL url = new URL("http://nekotech.tk/vercheck.php?a=jtHarass&b=" + harass.ver);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String strLine;
			while((strLine = br.readLine()) != null) {
				if (strLine.contains("u:")) {
					String[] s = strLine.split(":");
					harass.newver = s[1];
					harass.outOfDate = true;
					harass.getLogger().info("Update available! You're running v" + harass.version + " whereas latest is " + harass.newver);
				} else {
					harass.getLogger().severe("Failed to check for updates, webserver error!");
				}
			}
		} catch (Exception e) {
			harass.getLogger().severe("Failed to check for updates, exception!");
		}
	}

}
