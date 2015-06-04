import user.UserInfo;
import user.UserStatus;
import util.GMUtil;

public class UnKick {

	public String run() {
		return "RUN_FOUNCTION";
	}

	/**
	 * 设置玩家某项属性
	 */
	public String unKick(String user) {
		UserInfo u = GMUtil.getUser(user);
		u.setStatus( UserStatus.LOGIN );
		return "解除封号成功:" + user;
	}


}