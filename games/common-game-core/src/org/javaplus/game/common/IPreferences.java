package org.javaplus.game.common;

public interface IPreferences {

	String getString(Object key);

	void put(Object key, Object str);

	int getInt(Object key);

	void putTemp(Object key, Object v);

	int getTempInt(Object key);

	float getFloat(Object key);

	void clear();

	long getLong(Object key);

}
