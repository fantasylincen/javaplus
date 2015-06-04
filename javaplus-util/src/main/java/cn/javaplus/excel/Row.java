package cn.javaplus.excel;

public interface Row {

	boolean getBool(Object name);

	int getInt(Object name);

	String get(Object name);

	double getDouble(Object name);

	float getFloat(Object name);

	long getLong(Object name);
}
