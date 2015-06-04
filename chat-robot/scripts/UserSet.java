import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;

import java.util.ArrayList;
import java.util.List;

import user.UserInfo;
import util.GMUtil;

public class UserSet {

	public String run() {
		return "RUN_FOUNCTION";
	}

	/**
	 * 设置玩家某项属性
	 */
	public String set(String user, String type, String cnt) {
		UserInfo u = GMUtil.getUser(user);
		int c = GMUtil.getCount(cnt);

		List<UpdateType> ls = new ArrayList<UpdateType>();
		if ("等级".equals(type)) {
			setLevel(u, c, ls);
		} else if ("金币".equals(type)) {
			setCash(u, c, ls);
		} else if ("水晶".equals(type)) {
			setGold(u, c, ls);
		} else {
			return "无法识别的类型：" + type;
		}
		updateUserToClient(u, ls);
		return "设置成功:" + user + ":" + type + ":" + cnt;
	}

	/**
	 * 设置某个玩家金币
	 * 
	 * @param u
	 * @param cash
	 * @param ls
	 */
	public void setCash(UserInfo u, int cash, List<UpdateType> ls) {
		u.setCash(cash);
		ls.add(UpdateType.U_3);
	}

	public void setGold(UserInfo user, int count, List<UpdateType> ls) {
		user.setGold(count);
		ls.add(UpdateType.U_4);
	}

	/**
	 * 设置某个玩家等级
	 * 
	 * @param u
	 * @param ls
	 * @param gold
	 */
	public void setLevel(UserInfo u, int level, List<UpdateType> ls) {
		u.setLevel((short) level);
		ls.add(UpdateType.U_1);
	}

	/**
	 * 将服务器数据刷新到客户端
	 * 
	 * @param u
	 * @param ls
	 */
	public void updateUserToClient(UserInfo u, List<UpdateType> ls) {
		for (UpdateType type : ls)
			UpdateManager.instance.update(u, type);
	}
}