package mod.gamenature.skullforge.update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import mod.gamenature.skullforge.common.SkullForge;

public class UpdateCheck {


	public static boolean isUpdateAvailable() throws IOException, MalformedURLException, UnknownHostException {
		BufferedReader versionFile = new BufferedReader(new InputStreamReader(new URL("http://download.gamenature.net/files/skullversion.txt").openStream()));
		String curVersion = versionFile.readLine();

		if (!curVersion.contains(SkullForge.version)) {
			return true;
		}



		versionFile.close();

		return false;
	}

}