import game.activity.ActivityManager;

import java.util.List;

import com.google.common.collect.Lists;

import telnet.events.SetActivityEevet;
import define.GameData;

public class GetActivityData {

	public String run() {
		return "RUN_FOUNCTION";
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

	public String getData() {

		initName();

		StringBuilder content = new StringBuilder();
		int i = 0;

		content.append("(" + i + ")").append(name.get(i++)).append(foundToString(SetActivityEevet.isOpen1))
				.append("<br>");
		content.append("(" + i + ")").append(name.get(i++)).append(foundToString(SetActivityEevet.isOpen2))
				.append("<br>");
		content.append("(" + i + ")").append(name.get(i++))
				.append(foundToString(ActivityManager.getInstance().isConsumeOrgyIsOpen())).append("<br>");
		content.append("(" + i + ")").append(name.get(i++))
				.append(foundToString(ActivityManager.getInstance().isRestoreIsOpen())).append("<br>");
		content.append("(" + i + ")").append(name.get(i++)).append(foundToString(GameData.isHaveMikkaLogin_4))
				.append("<br>");
		content.append("(" + i + ")").append(name.get(i++)).append(foundToString(SetActivityEevet.isOpen3))
				.append("<br>");
		content.append("(" + i + ")").append(name.get(i++))
				.append("(" + (SetActivityEevet.isOpen4 > 0 ? SetActivityEevet.isOpen4 : "关闭") + ")").append("<br>");
		content.append("(" + i + ")").append(name.get(i++)).append(foundToString(SetActivityEevet.isOpen5))
				.append("<br>");
		content.append("(" + i + ")").append(name.get(i++)).append(foundToString(SetActivityEevet.isOpen6))
				.append("<br>");
		content.append("(" + i + ")").append(name.get(i++)).append(foundToString(SetActivityEevet.isOpen7))
				.append("<br>");
		content.append("(" + i + ")").append(name.get(i++)).append(foundToString(SetActivityEevet.isOpen8))
				.append("<br>");
		content.append("(" + i + ")").append(name.get(i++)).append(foundToString(SetActivityEevet.isOpen9))
				.append("<br>");
		content.append("(" + i + ")").append(name.get(i++)).append(foundToString(SetActivityEevet.isOpen10))
				.append("<br>");

		return content.toString();
	}

	private String foundToString(boolean isOpen) {
		return "(" + (isOpen ? "开启" : "关闭") + ")";
	}

	public int indexOf(String activityName) {
		if (activityName.matches("[0-9]\\d*"))
			return Integer.parseInt(activityName);

		for (int i = 0; i < name.size(); i++) {
			if (name.get(i).contains(activityName))
				return i;
		}
		return -1;
	}

}