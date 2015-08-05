package cn.javaplus.twolegs.bucket;

public interface Properties {

	int getInt(String key);

	String getString(String key);

	void put(String key, String v);

}
