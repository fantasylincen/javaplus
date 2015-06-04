package cn.mxz.server.token;

public class UnameMather {

	public static boolean matches(String uname) {
		if (uname == null) {
			return false;
		}
		// String regex =
		// "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		String regex = "[\\w][\\w_\\d]+";
		return uname.matches(regex);
	}

}
