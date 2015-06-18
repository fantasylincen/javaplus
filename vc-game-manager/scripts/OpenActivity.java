import java.util.List;

import com.google.common.collect.Lists;

import game.activity.ActivityManager;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import telnet.events.SetActivityEevet;
import user.UserInfo;
import user.UserManager;
import define.GameData;

public class OpenActivity {

	public String run() {
		return "RUN_FOUNCTION";
	}

	/**
	 * 开启某个活动
	 */
	public String open(String activityName) {
		openActivity(activityName);
		return "开启活动成功:" + activityName;
	}

	public List<String> name;

	private void initName() {
		name = Lists.newArrayList();

		name.add("豪华午晚宴");
		name.add("强力武装仆从");
		name.add("消费狂欢日"); // 精英副本和小龙挑战次数翻倍 每日购买体力次数翻倍
		name.add("高阶技能刷新日");
		name.add("新手礼包");
		name.add("抽卡送豪礼");
		name.add("英雄主题周");
		name.add("万圣节-强力武装仆从");
		name.add("最强战神-悟空来袭");
		name.add("圣诞节-抢礼物啦"); // 每日可挑战 圣诞嘉年华 活动副本8次
		name.add("圣诞节-圣诞老人"); // 每日单笔充值$99.9获得 圣诞老人*1
		name.add("圣诞节-英雄降临"); // 每日累计充值获得英雄
		name.add("圣诞节-圣诞狂欢"); // 每日赠送2次100体力
	}

	/**
	 * 开启某个活动
	 * 
	 * @param activityName
	 */
	public void openActivity(String activityName) {

		initName();

		int index = name.indexOf(activityName) + 1;

		if (index == 1) {
			SetActivityEevet.isOpen1 = true;
		} else if (index == 2) {
			SetActivityEevet.isOpen2 = true;
		} else if (index == 3) {
			ActivityManager.getInstance().setConsumeOrgyIsOpen(true);
			for (UserInfo user : UserManager.getInstance().getMaps().values())
				UpdateManager.instance.update(user, UpdateType.U_15);
		} else if (index == 4) {
			ActivityManager.getInstance().setRestoreIsOpen(true);
		} else if (index == 5) {
			GameData.isHaveMikkaLogin_4 = true;
		} else if (index == 6) {
			SetActivityEevet.isOpen3 = true;
		} else if (index == 7) {
			if (SetActivityEevet.isOpen4 <= 0)
				SetActivityEevet.isOpen4 = 1;
		} else if (index == 8) {
			SetActivityEevet.isOpen5 = true;
		} else if (index == 9) {
			SetActivityEevet.isOpen6 = true;
		} else if (index == 10) {
			SetActivityEevet.isOpen7 = true;
		} else if (index == 11) {
			SetActivityEevet.isOpen8 = true;
		} else if (index == 12) {
			SetActivityEevet.isOpen9 = true;
		} else if (index == 13) {
			SetActivityEevet.isOpen10 = true;
		}

		SetActivityEevet.update();
		// throw new RuntimeException("未实现此功能");
	}
}