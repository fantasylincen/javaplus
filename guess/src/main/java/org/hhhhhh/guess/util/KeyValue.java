package org.hhhhhh.guess.util;


public interface KeyValue extends KeyValueSaveOnly{

	int getInt(Object key);
	long getLong(Object key);
	double getDouble(Object key);
	boolean getBoolean(Object key);
	String getString(Object key);
}
