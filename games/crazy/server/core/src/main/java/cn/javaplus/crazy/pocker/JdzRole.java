package cn.javaplus.crazy.pocker;

import java.util.HashMap;
import java.util.Map;

public class JdzRole {

	private static Map<String, Integer> map;

	/**
	 * -1 表示重置游戏
	 * 
	 * @return
	 */
	public static final Integer getDz(String key) {
		if (map == null) {
			map = new HashMap<String, Integer>();
			map.put("000", -1);
			map.put("00100", 2);
			map.put("001010", 1);
			map.put("001011", 2);
			map.put("001100", 0);
			map.put("001101", 2);
			map.put("001110", 1);
			map.put("001111", 2);
			map.put("0100", 1);
			map.put("01010", 0);
			map.put("01011", 1);
			map.put("01100", 2);
			map.put("01101", 1);
			map.put("01110", 0);
			map.put("01111", 1);
			map.put("100", 0);
			map.put("1010", 2);
			map.put("1011", 0);
			map.put("1100", 1);
			map.put("1101", 0);
			map.put("1110", 2);
			map.put("1111", 0);
		}
		return map.get(key);

	}
}
