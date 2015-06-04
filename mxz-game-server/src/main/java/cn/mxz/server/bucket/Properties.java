package cn.mxz.server.bucket;

public interface Properties {

	int getInt(String key);

	String getString(String key);

	void put(String key, String v);

}
