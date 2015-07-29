package cn.javaplus.clickscreen.game;

import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.util.Util;

import cn.javaplus.clickscreen.App;

public class KeyGenerator {

	public static String generateKey(String best) {
		IPreferences p = App.getPreferences();
		String pwd = p.getString("password");
		pwd = Util.Secure.md5(pwd);
		return Util.Secure.md5(App.getConfigs().getConfig("COMMIT_SCORE_KEY", "xxxyyy") + best + pwd);
	}

}
