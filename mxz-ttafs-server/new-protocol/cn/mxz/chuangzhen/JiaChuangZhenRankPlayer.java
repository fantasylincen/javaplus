package cn.mxz.chuangzhen;

import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class JiaChuangZhenRankPlayer implements ChuangZhenRankPlayer {

	private ChuangZhenRankPlayerImpl	e;

	public JiaChuangZhenRankPlayer(ChuangZhenRankPlayerImpl e) {
		this.e = e;
	}

	/**
	 * @return
	 * @see cn.mxz.chuangzhen.ChuangZhenRankPlayerImpl#getFighterId()
	 */
	public int getFighterId() {
		return e.getFighterId();
	}

	/**
	 * @return
	 * @see cn.mxz.chuangzhen.ChuangZhenRankPlayerImpl#isMan()
	 */
	public boolean isMan() {
		return e.isMan();
	}

	/**
	 * @return
	 * @see cn.mxz.chuangzhen.ChuangZhenRankPlayerImpl#getNick()
	 */
	public String getNick() {
		return e.getNick();
	}

	/**
	 * @return
	 * @see cn.mxz.chuangzhen.ChuangZhenRankPlayerImpl#getUserId()
	 */
	public String getUserId() {
		return e.getUserId();
	}

	/**
	 * @return
	 * @see cn.mxz.chuangzhen.ChuangZhenRankPlayerImpl#getLevel()
	 */
	public int getLevel() {
		return e.getLevel();
	}

	/**
	 * @return
	 * @see cn.mxz.chuangzhen.ChuangZhenRankPlayerImpl#getVipLevel()
	 */
	public int getVipLevel() {
		return e.getVipLevel();
	}

	/**
	 * @return
	 * @see cn.mxz.chuangzhen.ChuangZhenRankPlayerImpl#getRank()
	 */
	public int getRank() {
		return e.getRank();
	}

	/**
	 * @return
	 * @see cn.mxz.chuangzhen.ChuangZhenRankPlayerImpl#getFloor()
	 */
	public int getFloor() {
		String userId = e.getUserId();
		City city = CityFactory.getCity(userId);
		UserCounter his = city.getUserCounterHistory();

		int floor = e.getFloor();

		int f = his.get(CounterKey.CHUANG_ZHEN_LATEST_FLOOR_MAX);
		if(floor != f) {
			his.set(CounterKey.CHUANG_ZHEN_LATEST_FLOOR_MAX, floor);
		}

		if(floor <= 0) {
			floor = 1;
		}
		
		return floor;
	}

	/**
	 * @return
	 * @see cn.mxz.chuangzhen.ChuangZhenRankPlayerImpl#getStar()
	 */
	public int getStar() {
		String userId = e.getUserId();
		City city = CityFactory.getCity(userId);
		UserCounter his = city.getUserCounterHistory();

		int star = e.getStar();

		int f = his.get(CounterKey.CHUANG_ZHEN_LATEST_STAR_MAX);
		if(star != f) {
			his.set(CounterKey.CHUANG_ZHEN_LATEST_STAR_MAX, star);
		}
		if(star <= 0) {
			star = 3;
		}
		return star;
	}

}
