import game.util.fighting.FightingFormula;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import user.UserInfo;
import util.GMUtil;
import cn.javaplus.string.StringPrinter;

import com.google.common.collect.Lists;

import define.GameData;

public class GetUserData {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public class UserData {

		private String attributeName;
		private Object value;

		/**
		 * @param attributeName
		 *            属性名字
		 * @param value
		 *            值
		 */
		public UserData(String attributeName, Object value) {
			this.attributeName = attributeName;
			this.value = value;
		}

		public String getAttributeName() {
			return attributeName;
		}

		public Object getValue() {
			return value;
		}

	}

	/**
	 * 设置玩家某项属性
	 */
	public String getData(String user) {
		UserInfo u = GMUtil.getUser(user);

		List<UserData> datas = getUserDatas(u);

		StringPrinter out = new StringPrinter();
		for (UserData data : datas) {
			out.print("&nbsp;&nbsp;&nbsp;" + data.getAttributeName() + " : " + data.getValue() + "<br>");
		}
		return out.toString();
	}

	public List<UserData> getUserDatas(UserInfo u) {
		ArrayList<UserData> ls = Lists.newArrayList();
		ls.add(new UserData("创建时间", secondsToDateStr(u.getCreateTime())));
		ls.add(new UserData("在线情况", (u.isOnline() ? "在线" : "离线(".concat(secondsToDateStr(u.getLastLogoutTime()))
				.concat(")"))));
		ls.add(new UserData("昵称", u.getNickName()));
		ls.add(new UserData("UID", u.getUID()));
		ls.add(new UserData("等级", u.getLevel()));
		ls.add(new UserData("vip等级", u.getVipLevel()));
		ls.add(new UserData("经验", u.getCurExp()));
		ls.add(new UserData("体力", u.getStrength() + "/" + u.getStrengthMax()));
		ls.add(new UserData("金币", u.getCash()));
		ls.add(new UserData("水晶", u.getGold()));
		ls.add(new UserData("战斗力", FightingFormula.run(u)));
		int grade = u.getDanGradingManager().getInfo().grade();
		String temp = String.valueOf( u.getDanGradingManager().getInfo().danGrad().toName() ).concat( "(" ).concat( String.valueOf( grade ) ).concat( ")" );
		ls.add(new UserData("匹配段位", temp));
		int index = GameData.ranking.indexOf(u.getUID());
		ls.add(new UserData("RANK排名", index == -1 ? "无排名" : (index + 1) ));
		ls.add(new UserData("奖杯", u.getTrophyNumer()));
		ls.add(new UserData("英雄背包信息", u.getHeroManager().toString()));
		ls.add(new UserData("装备背包信息", u.getEquipmentManager().toString()));
		return ls;
	}

	/**
	 * 把一个用秒数保存的时间值转换为易读的字符串
	 * 
	 * @param seconds
	 * @return
	 */
	public String secondsToDateStr(int seconds) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式
		return dateFormat.format(new Date(seconds * 1000l));

	}
}