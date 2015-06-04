import user.UserInfo;
import util.GMUtil;

public class CloseMouth {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String close(String user) {
		UserInfo u = GMUtil.getUser(user);
		u.chatTime = -1;
		return "禁言成功:" + user;
	}

}