package cn.javaplus.mxzrobot.actions;

import java.util.Set;

public interface Args {
	
	int getInt(Object key);

	String getString(Object key);

	double getDouble(Object key);

	long getLong(Object key);

	boolean getBoolean(Object key);

	Set<String> getKeys();
}
