package org.javaplus.game.common.properties;

import java.util.Properties;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;
import org.javaplus.game.common.util.Util;

import com.badlogic.gdx.files.FileHandle;

public class GameProperties {

	private static Properties properties;
	private static Loader loader = Assets.getInternal();

	public static void setLoader(Loader loader) {
		GameProperties.loader = loader;
	}
	public static String getString(Object k) {
		if (properties == null) {
			FileHandle f = loader.getFile("data/game.properties");
			String c = f.readString();
			properties = Util.Properties.getProperties(c);
		}
		return properties.getProperty(k.toString());
	}

}
