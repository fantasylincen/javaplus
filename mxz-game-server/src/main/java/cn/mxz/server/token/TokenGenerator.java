package cn.mxz.server.token;

import cn.javaplus.util.Util;

public class TokenGenerator {

	private static final int KEY_LEN = 8;

	private static final String tokenKey = "kkttyybbddcclllccc55658944";

	public static String generate(String uname) {
		String key = KeyGenerator.getRandomString(KEY_LEN).toLowerCase();
		key = Util.Secure.md5(uname + tokenKey + key) + key;
		return key;
	}

	/**
	 * @param roleId
	 * @param token
	 * @return 是否正确
	 */
	public static boolean checkToken(String roleId, String token) {

		String key = token.substring(token.length() - KEY_LEN, token.length());
		String m = token.substring(0, token.length() - KEY_LEN);

		String md5 = Util.Secure.md5(roleId + tokenKey + key);
		return m.equals(md5);
	}
}
