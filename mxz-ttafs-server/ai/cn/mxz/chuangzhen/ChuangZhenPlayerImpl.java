package cn.mxz.chuangzhen;

import java.lang.reflect.Method;

import cn.mxz.VipPrivilegeTemplet;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.PlayerProperty;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.user.team.Formation;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.counter.UserCounterSetter;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.vip.VipPlayer;
import db.dao.impl.DaoFactory;
import db.domain.ChuangZhen;
import db.domain.ChuangZhenImpl;
import db.domain.ChuangZhenTable;
import define.D;

public class ChuangZhenPlayerImpl implements ChuangZhenPlayer {

	City city;
	ChuangZhen dto;
	ChuangZhenReward reward;
	private ChuangZhenHeadsImpl heads;

	public ChuangZhenPlayerImpl(City city) {
		this.city = city;
		dto = lazyGet();
		reward = new ChuangZhenRewardImpl(this);
	}

	public ChuangZhenPlayerImpl(ChuangZhenTable dto) {
		this.dto = new ChuangZhenImpl();
		int count = 0;

		this.dto.setAttack(dto.getAttack());
		count++;
		this.dto.setDefend(dto.getDefend());
		count++;
		this.dto.setFloor(dto.getFloor());
		count++;
		this.dto.setHp(dto.getHp());
		count++;
		this.dto.setMAttack(dto.getMAttack());
		count++;
		this.dto.setMDefend(dto.getMDefend());
		count++;
		this.dto.setSpeed(dto.getSpeed());
		count++;
		this.dto.setStar(dto.getStar());
		count++;
		this.dto.setStarRemain(dto.getStarRemain());
		count++;
		this.dto.setTimes(dto.getTimes());
		count++;
		this.dto.setUname(dto.getUname());
		count++;
		this.dto.setFloorMaxToday(dto.getFloorMaxToday());
		count++;
		this.dto.setStarMaxToday(dto.getStarMaxToday());
		count++;

		// Debuger.debug("ChuangZhenPlayerImpl.ChuangZhenPlayerImpl() set 数量:" +
		// count);

		Method[] methods = ChuangZhenImpl.class.getMethods();
		for (Method method : methods) {
			String name = method.getName();
			if (name.startsWith("get")) {
				// Debuger.debug("ChuangZhenPlayerImpl.ChuangZhenPlayerImpl()" +
				// name);
				count--;
			}
		}
		if (count != -1) { // 因为有一个getClass 方法
			throw new RuntimeException("貌似没有完全复制 ???" + count);
		}
		this.city = CityFactory.getCity(dto.getUname());
		reward = new ChuangZhenRewardImpl(this);
	}

	ChuangZhen lazyGet() {
		if (dto == null) {
			dto = DaoFactory.getChuangZhenDao().get(city.getId());
			if (dto == null) {
				dto = new ChuangZhenImpl();
				dto.setUname(city.getId());
				dto.setFloor(1);
				// dto.setStarRemain(D.INIT_CHUANG_ZHEN_STAR);
				DaoFactory.getChuangZhenDao().add(dto);
			}
		}
		return dto;
	}

	@Override
	public int getCurrentFloor() {
		return lazyGet().getFloor();
	}

	@Override
	public int getMaxFloor() {
		ChuangZhen dto = lazyGet();
		return dto.getFloorMaxToday();
	}

	@Override
	public int getCurrentTimes() {
		return lazyGet().getTimes();
	}

	@Override
	public int getMaxTimes() {
		if (city.isTester()) {
			return 100;
		}
		return D.CHUANG_ZHEN_MAX_TIMES + getVipTimesAddition();
	}

	/**
	 * VIP 渡劫次数增量
	 * @return
	 */
	private int getVipTimesAddition() {
		VipPlayer vipPlayer = city.getVipPlayer();
		int level = vipPlayer.getLevel();
		VipPrivilegeTemplet temp = VipPrivilegeTempletConfig.get((byte) level);
		return temp.getCopterTime();
	}

	@Override
	public int getStar() {
		return lazyGet().getStar();
	}

	@Override
	public int getRemainStar() {
		return lazyGet().getStarRemain();
	}

	@Override
	public Addition getAddition() {
		return new AdditionImpl(lazyGet());
	}

	@Override
	public PlayerCamp camp() {
		return city.getFormation().getSelected();
	}

	@Override
	public int getNextAddition() {
		int floor = getCurrentFloor();
		int i = get(D.CHUANG_ZHEN_ADDITION_FLOOR, floor - 1);
		if (i == 0) {
			return D.CHUANG_ZHEN_ADDITION_FLOOR;
		}
		return i;
	}

	// public static void main(String[] args) {
	// for (int floor = 1; floor < 20; floor++) {
	// int i = get(D.CHUANG_ZHEN_ADDITION_FLOOR, floor - 1);
	// // if (i == 0) {
	// // System.out.println(D.CHUANG_ZHEN_ADDITION_FLOOR);
	// // } else {
	// System.out.println(floor + ":" + i);
	// // }
	// }
	// }
	//
	@Override
	public int getNextReward() {
		int floor = getCurrentFloor();
		int i = get(D.CHUANG_ZHEN_REWARD_FLOOR, floor - 1);
		if (i == 0) {
			return D.CHUANG_ZHEN_REWARD_FLOOR;
		}
		return i;
	}

	private static int get(int d, int floor) {
		int k = floor % d;
		int c = d - k;
		c = c % d;
		return c;
	}

	// public static void main(String[] args) {
	// for (int floor = 0; floor < 20; floor++) {
	// int d = D.CHUANG_ZHEN_ADDITION_FLOOR;
	//
	// int k = floor % d;
	// int c = d - k;
	// c = c % d;
	// System.out.println("ChuangZhenPlayerImpl.main()" + c);
	// }
	// }

	@Override
	public int getRank() {
		return ChuangZhenRankingList.getInstance().getRank(this);
	}

	@Override
	public int getCash() {
		return city.getPlayer().get(PlayerProperty.CASH);
	}

	@Override
	public void addStar(int received) {
		ChuangZhen dto = lazyGet();
		dto.addStar(received);
		dto.addStarRemain(received);

		Debuger.debug("ChuangZhenPlayerImpl.addStar(): " + dto.getStar() + "/"
				+ dto.getStarRemain());

		UserCounterSetter his = city.getUserCounterHistory();
		{
			int star = dto.getStar();
			int stoday = dto.getStarMaxToday();

			his.set(CounterKey.CHUANG_ZHEN_STAR_RECEIVE, received,
					dto.getFloor());

			if (star > stoday) {
				dto.setStarMaxToday(star);
			}
		}
		{
			UserCounter u = city.getUserCounter();
			int max = u.get(CounterKey.CHUANG_ZHEN_MAX_TIMES);

			if (dto.getTimes() > max) {
				u.set(CounterKey.CHUANG_ZHEN_MAX_TIMES, dto.getTimes());
			}
		}
		DaoFactory.getChuangZhenDao().update(dto);
	}

	@Override
	public void floorUp() {
		ChuangZhen dto = lazyGet();
		dto.addFloor(1);

		int floor = dto.getFloor();
		int fToday = dto.getFloorMaxToday();

		if (floor > fToday) {
			dto.setFloorMaxToday(floor - 1);
		}

		DaoFactory.getChuangZhenDao().update(dto);
		Debuger.debug("ChuangZhenPlayerImpl.floorUp()");

		((ChuangZhenRewardImpl) reward).generate();

		clearRewardMark();
	}

	@Override
	public void reset() {
		ChuangZhen dto = lazyGet();
		dto.setStar(0);
		// int s = dto.getStarRemain();
		// if (s < D.INIT_CHUANG_ZHEN_STAR) {
		// dto.setStarRemain(D.INIT_CHUANG_ZHEN_STAR);
		// }
		dto.setStarRemain(0);
		dto.setFloor(1);
		dto.setAttack(0);
		dto.setDefend(0);
		dto.setHp(0);
		dto.setMAttack(0);
		dto.setMDefend(0);
		dto.setSpeed(0);
		dto.addTimes(1);
		DaoFactory.getChuangZhenDao().update(dto);
		Debuger.debug("ChuangZhenPlayerImpl.reset()");

		((ChuangZhenRewardImpl) reward).generate();

		clearRewardMark();
	}

	private void clearRewardMark() {
		UserCounter his = city.getUserCounterHistory();
		his.clear(CounterKey.RECEIVE_BATTLE_REWARD);
		his.clear(CounterKey.RECEIVE_ADDITION_REWARD);
	}

	@Override
	public ChuangZhenReward getChuangZhenReward() {
		return reward;
	}

	@Override
	public int getRemainFloor() {
		return getNextReward();
	}

	@Override
	public int getStarMaxHistory() {

		ChuangZhen dto = lazyGet();
		return dto.getStarMaxToday();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((city == null) ? 0 : city.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChuangZhenPlayerImpl other = (ChuangZhenPlayerImpl) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.getId().equals(other.city.getId()))
			return false;
		return true;
	}

	@Override
	public String getNick() {
		return city.getPlayer().getNick();
	}

	@Override
	public String getId() {
		return city.getId();
	}

	@Override
	public ChuangZhenHeads getHeads() {
		if (heads == null) {
			heads = new ChuangZhenHeadsImpl(this);
		}
		return heads;
	}

	public int getLevel() {
		return city.getLevel();
	}

	@Override
	public int getFormationCount() {
		Formation formation = city.getFormation();
		return formation.getMaxCount();
	}

}
