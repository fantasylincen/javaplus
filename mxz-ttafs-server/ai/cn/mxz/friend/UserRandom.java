package cn.mxz.friend;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.javaplus.util.Util;
import cn.mxz.activity.friend.RandomUserGenerator;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class UserRandom implements RandomUserGenerator {

	private City	city;

	public UserRandom(City city) {
		this.city = city;
	}

	@Override
	public List<City> getCitys() {

		List<City> all = Lists.newArrayList(getWorld().getAll());
		List<City> onlineAll = Lists.newArrayList(getWorld().getOnlineAll());

		all.removeAll(onlineAll);

		onlineAll.addAll(all);

		return onlineAll;
	}

	/**
	 * 随机3级内玩家
	 * @return
	 */
	public List<City> getCitysByLevel() {

		List<City> all = Lists.newArrayList(getWorld().getAll());

		List<City> onlineAll = Lists.newArrayList(getWorld().getOnlineAll());

		all.removeAll(onlineAll);

		onlineAll.addAll(all);

		Iterator<City> it = onlineAll.iterator();

		while (it.hasNext()) {

			City city = it.next();

			int level = city.getLevel();

			if(Math.abs(level - this.city.getLevel()) > 3) {

				it.remove();
			}
		}

		return onlineAll;
	}

	private World getWorld() {
		return WorldFactory.getWorld();
	}

	/**
	 * 推荐好友
	 *
	 * @return
	 */
	@Override
	public Collection<City> recommendFriends() {

		List<City> all = Lists.newArrayList(getWorld().getOnlineAll());

		all.remove(city);

		if (all.size() > 10) {

			return sub(all);
		}

		Set<City> ls = Sets.newHashSet();

		all = Lists.newArrayList(getWorld().getAll());

		ls.addAll(getRandomLevelNear(all, city));

		ls.addAll(getRandom(all, city));

		return sub(Lists.newArrayList(ls));
	}

	private Collection<City> sub(List<City> all) {
		cn.javaplus.util.Util.Collection.upset(all);
		return cn.javaplus.util.Util.Collection.sub(all, 10);
	}

	private Collection<? extends City> getRandom(List<City> all, City city2) {
		cn.javaplus.util.Util.Collection.upset(all);
		return cn.javaplus.util.Util.Collection.sub(all, 3);
	}

	private List<City> getRandomLevelNear(List<City> all, City c) {
		sortByLevel(all, c);
		return Util.Collection.sub(all, 4);
	}

	private void sortByLevel(List<City> all, final City c) {
		Collections.sort(all, new Comparator<City>() {

			@Override
			public int compare(City o1, City o2) {
				int a = Math.abs(getLevel(o1) - getLevel(c));
				int b = Math.abs(getLevel(o2) - getLevel(c));
				return a - b;
			}

			private int getLevel(City o1) {
				int level = o1.getPlayer().get(PlayerProperty.USER_LEVEL_CACHE);
				return level;
			}
		});
	}

}
