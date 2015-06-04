package cn.mxz.chuangzhen;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

import com.google.common.collect.Lists;

import db.dao.impl.ChuangZhenRankingListDao;
import db.dao.impl.DaoFactory;

public class ChuangZhenRankingList {

	private static ChuangZhenRankingList instance;

	private ArrayList<ChuangZhenPlayerImpl> getAll() {

		ArrayList<ChuangZhenPlayerImpl> all = Lists.newArrayList();
		ChuangZhenRankingListDao DAO = DaoFactory.getChuangZhenRankingListDao();
		List<db.domain.ChuangZhenRankingList> a = DAO.getAll();

		for (db.domain.ChuangZhenTable c : a) {
			ChuangZhenPlayerImpl p = new ChuangZhenPlayerImpl(c);
			int maxFloor = p.getMaxFloor();
			if (maxFloor > 0) {
				all.add(p);
			}
		}
		return all;
	}

	public static final ChuangZhenRankingList getInstance() {
		if (instance == null) {
			instance = new ChuangZhenRankingList();
		}
		return instance;
	}

	/**
	 * 获得指定玩家的排行
	 * 
	 * @param player
	 * @return
	 */
	public int getRank(ChuangZhenPlayer player) {
		List<ChuangZhenPlayer> list = getList(player);
		if (list != null) {
			return list.indexOf(player) + 1;
		}

		return 0;
	}

	/**
	 * 4 20-30 5 31-40 6 41以上
	 * 
	 * @param count
	 * @return
	 */
	public List<ChuangZhenPlayer> getAll(int count) {

		if (count == 4) {
			return get(20, 30);
		}
		if (count == 5) {
			return get(31, 40);
		}
		if (count == 6) {
			return get(41, 100);
		}
		throw new SureIllegalOperationException("无法识别的上阵人数!");
	}

	private List<ChuangZhenPlayer> get(int min, int max) {
		List<ChuangZhenPlayer> ls = Lists.newArrayList();
		for (ChuangZhenPlayer c : getAll()) {
			int lv = c.getLevel();
			if (min <= lv && lv <= max) {
				ls.add(c);
			}
		}
		// return Util.Collection.sub(ls, 50);
		return ls;
	}

	private List<ChuangZhenPlayer> getList(ChuangZhenPlayer p) {
		int level = p.getLevel();
		if (20 <= level && level <= 30) {
			return get(20, 30);
		} else if (31 <= level && level <= 40) {
			return get(31, 40);
		} else if (41 <= level) {
			return get(41, 100);
		}
		return null;
	}

	public void saveRankToday(ChuangZhenPlayer player, int rank) {
		String id = player.getId();
		City city = CityFactory.getCity(id);
		UserCounter his = city.getUserCounterHistory();

		int today = AbstactRankingListPro.getDay();

		int i = his.get(CounterKey.CHUANG_ZHEN_RANK, today);

		if (i != rank) {
			his.set(CounterKey.CHUANG_ZHEN_RANK, rank, today);
		}
	}

	public void updateTodayRank(ChuangZhenPlayer player) {
		int rank = getRank(player);
		saveRankToday(player, rank);		
	}
}
