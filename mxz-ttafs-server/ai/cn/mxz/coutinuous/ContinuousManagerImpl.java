package cn.mxz.coutinuous;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.javaplus.util.Util;
import cn.mxz.city.City;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import db.dao.impl.ContinuousDao;
import db.dao.impl.DaoFactory;
import db.domain.Continuous;
import db.domain.ContinuousImpl;

public class ContinuousManagerImpl implements ContinuousManager {

	private City						city;

	private final static int			MAX	= 100;

	private Map<Integer, Continuous>	continuous;

	public ContinuousManagerImpl(City city) {
		this.city = city;
		continuous = Maps.newHashMap();
	}

	@Override
	public int getContinuous(ContinuousType type) {
		List<Boolean> all = getStatus(type, MAX);
		return getCount(true, all);
	}

	@Override
	public int getSpaceDays(ContinuousType type) {
		List<Boolean> all = getStatus(type, MAX);
		return getCount(false, all);
	}

	/**
	 * 获得all的头部有值为status的数量
	 *
	 * @param status
	 * @param all
	 * @return
	 */
	private int getCount(boolean status, List<Boolean> all) {
		int count = 0;
		for (Boolean done : all) {
			if (status == done) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}

	@Override
	public int getPreviousSpace(ContinuousType type) {
		List<Boolean> all = Lists.newArrayList(getStatus(type, MAX));
		removeTrueOnHead(all);
		return getCount(false, all);
	}

	/**
	 * 移除all头部的所有值为true的元素
	 *
	 * @param all
	 */
	private void removeTrueOnHead(List<Boolean> all) {
		Iterator<Boolean> it = all.iterator();
		while (it.hasNext()) {
			if (it.next()) {
				it.remove();
			} else {
				break;
			}
		}
	}

	@Override
	public List<Boolean> getStatus(ContinuousType type, int day) {
		Continuous c = getDto(type);
		updateNewest(c);
		String[] all = c.getStatus().split("");
		List<Boolean> ls = Lists.newArrayList();
		for (String x : all) {
			if (!x.isEmpty()) {
				ls.add(x.equals("1"));
			}
		}
		return ls;
	}

	private Continuous getDto(ContinuousType type) {

		Continuous c = continuous.get(type.toNumber());
		if (c == null) {
			c = loadFromDB(type);
			continuous.put(type.toNumber(), c);
		}

		return c;
	}

	private Continuous loadFromDB(ContinuousType type) {
		ContinuousDao DAO = DaoFactory.getContinuousDao();
		Continuous c = DAO.get(type.toNumber(), city.getId());

		if (c == null) {
			c = new ContinuousImpl();
			c.setUname(city.getId());
			c.setStatus("");
			c.setLastUpdateDay(getYestoday());
			c.setType(type.toNumber());
			DAO.add(c);
		}

		return c;
	}

	private int getYestoday() {
		return getCurrentDay() - 1;
	}

	@Override
	public void doIt(ContinuousType type) {
		Continuous c = getDto(type);
		if (hasBeenDoneToday(c)) {
			return;
		}
		updateNewest(c);
		c.setStatus("1" + c.getStatus());
		c.setLastUpdateDay(getCurrentDay());
		ensureLowerThanMax(c);
		DaoFactory.getContinuousDao().update(c);
	}

	/**
	 * 今日是否已经操作过了
	 *
	 * @param c
	 * @return
	 */
	private boolean hasBeenDoneToday(Continuous c) {
		return c.getLastUpdateDay() == getCurrentDay();
	}

	/**
	 * 确保状态长度小于100
	 *
	 * @param c
	 */
	private void ensureLowerThanMax(Continuous c) {
		String status = c.getStatus();
		if (status.length() > MAX) {
			status = status.substring(0, MAX);
			c.setStatus(status);
		}
	}

	/**
	 * 更新到最新状态
	 *
	 * @param c
	 * @param status
	 */
	private void updateNewest(Continuous c) {
		int lastDay = c.getLastUpdateDay();
		int yestoday = getYestoday();
		while (lastDay < yestoday) { // 到前一天
			lastDay++;
			c.setStatus("0" + c.getStatus());
			c.setLastUpdateDay(lastDay);
		}
		ensureLowerThanMax(c);
	}

	private int getCurrentDay() {
		long time = System.currentTimeMillis();
		time += Calendar.getInstance().get(Calendar.ZONE_OFFSET);
		return (int) (time / Util.Time.MILES_ONE_DAY);
	}
}
