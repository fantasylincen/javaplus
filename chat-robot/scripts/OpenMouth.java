import user.UserInfo;
import util.GMUtil;

public class OpenMouth {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String open(String user) {
		UserInfo u = GMUtil.getUser(user);
		u.chatTime = 0;
		return "解除禁言成功:" + user;
	}

}