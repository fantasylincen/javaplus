package cn.mxz.vipcard;

import java.util.Calendar;

import mongo.gen.MongoGen.DailyTaskDto;
import cn.javaplus.util.Util;
import cn.mxz.VipTempletConfig;
import cn.mxz.base.task.Task;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.task.dailytask.DailyTaskManager;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import define.D;

public class VipCardPlayerImpl implements VipCardPlayer {

	private City		user;
	private UserCounter	counter;

	public VipCardPlayerImpl(City city) {
		this.user = city;
		counter = user.getUserCounterHistory();
	}

	private int getStartSecond() {
		return counter.get(CounterKey.VIP_CARD_START);
	}

	private void setStartSecond() {
		counter.set(CounterKey.VIP_CARD_START, (int) System.currentTimeMillis() / 1000);
	}

	/**
	 * 获取vip月卡的剩余时间
	 *
	 * @return
	 */
	@Override
	public int getRemainDay() {
		Calendar now = Calendar.getInstance();
		Calendar start = Calendar.getInstance();
		start.setTimeInMillis(getStartSecond() * 1000l);

		return Util.Time.getElapsedDays(start, now);
	}

	@Override
	public int getVipCardType() {
		return counter.get(CounterKey.VIP_CARD_TYPE);
	}

	/**
	 * 购买vip月卡
	 */
	@Override
	public void buy() {

		int type = getCanUse();

		int needCash = VipTempletConfig.get(type).getPrice();
		user.getPlayer().reduce(PlayerProperty.CASH, needCash);
		setStartSecond();
		counter.set(CounterKey.VIP_CARD_TYPE, type);
	}

	/**
	 * 计算可以购买的vip月卡
	 *
	 * @return
	 */
	private int getCanUse() {
		int[] allData = VipTempletConfig.getArrayByAccumulate();
		int goldTotal = counter.get(CounterKey.TOTAL_RECHARGE_GOLD_COUNT);
		for (int i = 0; i < allData.length; i++) {
			if (goldTotal < allData[i]) {
				return i;
			}
		}
		return allData.length;
	}

	@Override
	public int getLevel() {
		return user.getVipPlayer().getLevel();
	}

	@Override
	public boolean hasReceived() {
		// 0:什么都没有买 1:白银月卡 2:黄金月卡 3:至尊月卡
		int t = getVipCardType();
		if (t == 0) {
			return false;
		}

		DailyTaskManager d = user.getDailyTaskManager();

		Task<DailyTaskDto> task;
		if (t == 1) {
			task = d.getTask(D.VIP_CARD_BAI_YIN_TADK_ID);
		} else if (t == 2) {
			task = d.getTask(D.VIP_CARD_HUANG_JIN_TADK_ID);
		} else {
			task = d.getTask(D.VIP_CARD_ZHI_ZUN_TADK_ID);
		}

		return task.isGiveBack();
	}

}
