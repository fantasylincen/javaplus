package cn.vgame.b.system;

import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.b.Server;

public class Const {

	public long getLong(String key) {
		Row row = get(key);
		return row.getLong("value");
	}

	public int getInt(String key) {
		Row row = get(key);
		return row.getInt("value");
	}

	public float getFloat(String key) {
		Row row = get(key);
		return row.getFloat("value");
	}

	public boolean getBool(String key) {
		Row row = get(key);
		return row.getBool("value");
	}

	public double getDouble(String key) {
		Row row = get(key);
		return row.getDouble("value");
	}
	
	public String getString(String key) {
		Row row = get(key);
		return row.get("value");
	}

	private Row get(String key) {
		Sheet c = Server.getXml().get("const");
		Row row = c.get(key);
		return row;
	}

}
