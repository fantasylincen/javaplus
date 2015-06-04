import java.io.IOException;

import user.UserInfo;
import user.UserStatus;
import util.GMUtil;

public class KickUser {

	public String run() {
		return "RUN_FOUNCTION";
	}

	/**
	 * 设置玩家某项属性
	 */
	public String kick(String user) {
		UserInfo u = GMUtil.getUser(user);
		kick(u);
		return "封号成功:" + user;
	}

	/**
	 * 封号 封号之后， 首先记得将玩家踢下线，然后让他不能登陆
	 * 
	 * @param u
	 */
	public void kick(UserInfo u) {
		try {
			u.setStatus(UserStatus.BAN);
			if (u.getCon() != null)// lc加 2014年12月30日 11:55:40
				u.getCon().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}