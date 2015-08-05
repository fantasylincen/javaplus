package org.javaplus.game.common;

import java.util.HashMap;
import java.util.Map;

import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.properties.GameProperties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferencesImpl implements IPreferences {

	private Map<Object, String> temp = new HashMap<Object, String>();
	private Preferences preferences;

	@Override
	public String getString(Object key) {
		if (exist(key)) {
			return new String(getTemp(key));
		}
		String string = get().getString(key.toString());

		if (string != null) {
			putTemp(key, string);
		}

		return string;
	}

	private String getTemp(Object key) {
		String v = temp.get(key);
		if (v == null) {
			return null;
		}
		return v.toString();
	}

	private boolean exist(Object key) {
		return temp.containsKey(key);
	}

	@Override
	public void put(Object key, Object o) {
		get().putString(key.toString(), o.toString());
		get().flush();
		temp.put(key, o.toString());
//		Log.d("PreferencesImpl", "put", key, o);
	}

	private Preferences get() {
		if (preferences == null) {
			Log.d("PreferencesImpl", "get", "xxx");
			preferences = Gdx.app.getPreferences(GameProperties.getString("GAME_RECORD_NAME"));
		}
		return preferences;
	}

	@Override
	public int getInt(Object key) {
		String s = getString(key);
		if (s == null || s.isEmpty()) {
			return 0;
		}
		return new Integer(s);
	}

	@Override
	public float getFloat(Object key) {
		String s = getString(key);
		if (s == null || s.isEmpty()) {
			return 0;
		}
		return new Float(s);
	}

	@Override
	public void putTemp(Object key, Object v) {
		temp.put(key, v.toString());
		Log.d("PreferencesImpl", "putTemp", key, v);
	}

	@Override
	public int getTempInt(Object key) {
		String s = getTemp(key);
		if (s == null) {
			return 0;
		}
		return new Integer(s);
	}

	@Override
	public void clear() {
		preferences.clear();
		preferences.flush();
		temp.clear();
	}

	@Override
	public long getLong(Object key) {
		String s = getString(key);
		if (s == null || s.isEmpty()) {
			return 0;
		}
		return new Long(s);
	}
}
