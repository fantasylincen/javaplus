package org.hhhhhh.fqzs.login;

import java.util.Properties;
import java.util.Set;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.util.Sets;
import org.javaplus.game.common.util.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class AppProperties {


	private Properties prop;

	public AppProperties() {

		Loader loader = Assets.getDefaultLoader();
		FileHandle file = loader.getFile("data/game.properties");
		
		String string = file.readString();
		prop = Util.Properties.getProperties(string);
		Set<Object> keySet = prop.keySet();
		for (Object k : keySet) {
			replaceValue(k, prop);
		}
	}

	public int getInt(Object key) {
		return new Integer(get(key));
	}

	public long getLong(Object key) {
		return new Long(get(key));
	}

	public double getDouble(Object key) {
		return new Double(get(key));
	}

	public boolean getBoolean(Object key) {
		return "true".equals(get(key));
	}

	public String get(Object key) {
		if (key == null)
			return null;
		return prop.getProperty(key + "");
	}


	private void replaceValue(Object k, Properties p) {

		String v = p.getProperty(k.toString());
		Set<Object> ks = Sets.newHashSet(p.keySet());
		for (Object kk : ks) {
			v = v.replaceAll("\\$\\{" + kk + "\\}", p.getProperty(kk.toString()));
		}
		p.setProperty(k.toString(), v);
	}
}
