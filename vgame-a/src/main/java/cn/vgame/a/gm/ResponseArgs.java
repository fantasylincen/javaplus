package cn.vgame.a.gm;

import java.util.HashMap;
import java.util.Set;

import cn.javaplus.collections.map.Maps;

public class ResponseArgs {

	HashMap<String, Object> map = Maps.newHashMap();
	public Set<String> keySet() {
		return map.keySet();
	}

	public Object getValue(String key) {
		return map.get(key);
	}

	public void put(String key, Object value) {
		map.put(key, value);
	}
}
