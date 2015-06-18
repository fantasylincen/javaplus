package cn.javaplus.mxzrobot.token;

import java.util.Map;

import cn.javaplus.util.Util;

import com.google.common.collect.Maps;

public class TokenManager {

	private static Map<String, String> map = Maps.newHashMap();

	public static String get(String uname) {
		return map.get(uname);
	}

	public static String createToken(String uname) {
		String str = Util.Random.getRandomString(32);
		map.put(uname, str);
		return str;
	}

}
