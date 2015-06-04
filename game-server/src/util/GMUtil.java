package util;

import cn.javaplus.util.Util;

public class GMUtil {

	public static final user.UserInfo getUser(String name) {

		user.UserInfo u = user.UserManager.getInstance().getByNickName(name);

		if (u == null) {

			Integer id;
			try {
				id = new Integer(name);
			} catch (NumberFormatException e) {
				throw new RuntimeException("玩家不存在:" + name);
			}
			u = user.UserManager.getInstance().getByName(id);
		}
		if(u == null)
			throw new RuntimeException("玩家不存在:" + name);
		return u;

	}

	/**
	 * 输入111个/一百一十1枚/111颗   返回 111
	 */
	public static int getCount(String cnt) {
		cnt = cnt.replaceAll("个", "");
		cnt = cnt.replaceAll("点", "");
		cnt = cnt.replaceAll("枚", "");
		cnt = cnt.replaceAll("颗", "");
		cnt = cnt.replaceAll("匹", "");
		cnt = cnt.replaceAll("条", "");
		cnt = cnt.replaceAll("头", "");
		cnt = cnt.replaceAll("张", "");
		cnt = cnt.trim();
		if (!cnt.matches("[0-9]+")) {
			cnt = "" + Util.Chinese.chineseToDigit(cnt);
		}
		return new Integer(cnt);

	}
}
