package cn.mxz.cornucopia;

import java.util.ArrayList;
import java.util.List;

import message.S;
import cn.javaplus.util.Util;
import cn.mxz.CornucopiaTemplet;
import cn.mxz.CornucopiaTempletConfig;
import cn.mxz.VipPrivilegeTemplet;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.IMessageSender;
import cn.mxz.city.PlayerProperty;
import cn.mxz.events.EventDispatcher2Impl;
import cn.mxz.user.Player;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.counter.UserCounterSetter;

import com.google.common.collect.Lists;

import define.D;

class CornucopiaImpl extends EventDispatcher2Impl implements Cornucopia {

	private String	id;

	CornucopiaImpl(String id) {
		this.id = id;
	}

	@Override
	public void run() {
		int times = getRunTimesToday() + 1;
		check(times);
		reduce(times);
		sendReward(times);
		addTimes();
	}

	/*
	 * 扣钱
	 */
	private void reduce(int times) {
		CornucopiaTemplet temp = CornucopiaTempletConfig.get(times);
		Player player = getCity().getPlayer();
		player.reduceGold(temp.getCouponsNeed());
	}

	private void check(int times) {
		CornucopiaTemplet temp = CornucopiaTempletConfig.get(times);
		if (temp == null) {
			throw new OperationFaildException(S.S10167);
		}

		if (getRunTimesToday() >= getMaxTime()) {
			throw new OperationFaildException(S.S10239);
		}
	}

	/*
	 * 发奖
	 */
	private void sendReward(int times) {
		CornucopiaTemplet temp = CornucopiaTempletConfig.get(times);
		Player player = getCity().getPlayer();
		CornucopiaPrize p = new CornucopiaPrize(temp, player);

		int cashOld = player.get(PlayerProperty.CASH);

		String awards = p.getAwards();
		PrizeSenderFactory.getPrizeSender().send(player, awards);

		int cashNew = player.get(PlayerProperty.CASH);

		resetYunShi(); // 重置运势
		
		sendMessage(p, player);

		saveToAll(cashNew - cashOld);
	}

	private void sendMessage(CornucopiaPrize p, Player player) {
		int add = p.getCashAdd();
		int cash = p.getCash();
		City city = player.getCity();
		IMessageSender s = city.getMessageSender();
		if(add==0) {
			s.send(S.S60266, cash);
		} else {
			s.send(S.S60267, cash, add);
		}
	}

	private void resetYunShi() {

//		int add = Util.Random.get(D.YUN_SHI_ADD_MIN, D.YUN_SHI_ADD_MAX);
//		boolean isAdd = Util.Random.isHappen(0.5f);
		City city = getCity();
		UserCounter u = city.getUserCounter();
//		int yunShi = u.get(CounterKey.YUN_SHI);
//
//		if (isAdd) {
//			yunShi += add;
//		} else {
//			yunShi -= add;
//		}
//
//		if (yunShi > D.YUN_SHI_MAX) {
//			yunShi = D.YUN_SHI_MAX;
//		}
//
//		if (yunShi < D.YUN_SHI_MIN) {
//			yunShi = D.YUN_SHI_MIN;
//		}
		
		List<Scope> ss = getScopes();
		
		int min = getMin(ss);
		int max = getMax(ss);
		int yunShi = Util.Random.get(min, max);
		u.set(CounterKey.YUN_SHI, yunShi);
	}
	
	private int getMin(List<Scope> ss) {
		int min = Integer.MAX_VALUE;
		for (Scope scope : ss) {
			if(scope.min < min) {
				min = scope.min;
			}
		}
		return min;
	}
	private int getMax(List<Scope> ss) {
		int max = Integer.MIN_VALUE;
		for (Scope scope : ss) {
			if(scope.max > max) {
				max = scope.max;
			}
		}
		return max;
	}

	private static class Scope {
		int min;
		int max;
	}
	private List<Scope> getScopes() {
		String[] split = D.YAO_QIAN_SHU_BIAO_QING.split(",");
		ArrayList<Scope> ls = Lists.newArrayList();
		for (String string : split) {
			String[] ss = string.split(":");

			String scope = ss[0];
			List<Integer> all = Util.Collection.getIntegers(scope);
			Scope s = new Scope();
			s.min = all.get(0);
			s.max = all.get(1);
			ls.add(s);
		}
		return ls;
	}

	private void saveToAll(int add) {
		City city = getCity();
		UserCounterSetter u = city.getUserCounterAuto();
		u.add(CounterKey.CORNUCOPIA_ALL, add);
	}

	/*
	 * 记录摇钱次数
	 */
	private void addTimes() {

		City city = getCity();

		UserCounterSetter c = city.getUserCounterAuto();
		int t = 1;

//		if (getCity().isTester()) { // 快速摇钱测试
//			t = (100 - getRunTimesToday()) / 2;
//			if (t == 0) {
//				t = 1;
//			}
//		}

		c.add(CounterKey.CORNUCOPIA_RUN_TIMES, t);
	}

	@Override
	public int getRunTimesToday() {
		City city = getCity();
		UserCounter c = city.getUserCounter();
		return c.get(CounterKey.CORNUCOPIA_RUN_TIMES);
	}

	public City getCity() {
		return CityFactory.getCity(id);
	}

	@Override
	public float getYunShi() {
		City city = getCity();
		UserCounter uc = city.getUserCounter();
		int yunShix100 = uc.get(CounterKey.YUN_SHI);

		if (yunShix100 == 0) {
			yunShix100 = initYunShi(uc);
		}

		return yunShix100 / 100f;

		// 10 - 50
		// 2 - 5
		// 先增长, 后减少

	}

	/**
	 * 初始化运势
	 *
	 * @param uc
	 * @return
	 */
	private int initYunShi(UserCounterSetter uc) {
		int random = Util.Random.get(D.YUN_SHI_MIN, D.YUN_SHI_MAX);
		uc.set(CounterKey.YUN_SHI, random);
		return random;
	}

	@Override
	public int getAll() {
		City city = getCity();
		UserCounter u = city.getUserCounter();
		return u.get(CounterKey.CORNUCOPIA_ALL);
	}

	@Override
	public int getMaxTime() {
		Byte level = (byte) getCity().getVipPlayer().getLevel();
		VipPrivilegeTemplet temp = VipPrivilegeTempletConfig.get(level);
		if (temp == null) {
			throw new NullPointerException(level + "");
		}
		return temp.getMoney();
	}

}
