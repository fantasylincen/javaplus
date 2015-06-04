package cn.mxz.activity.friend;

import java.util.Collection;
import java.util.List;

import cn.mxz.city.City;
import cn.mxz.friend.Friend;

import com.google.common.collect.Lists;

class RandomInNormalFriend implements RandomUserGenerator {

	private City	city;

	RandomInNormalFriend(City city) {
		this.city = city;
	}

	@Override
	public Collection<City> recommendFriends() {
		List<City> citys = getCitys();
		cn.javaplus.util.Util.Collection.upset(citys);
		return cn.javaplus.util.Util.Collection.sub(citys, 10);
	}

	/**
	 * 过滤掉所有已经是好友的玩家
	 * @param citys
	 */
	private void filter(List<City> citys) {
//		Iterator<City> it = citys.iterator();
//		ActivityFriendManager am = city.getActivityFriendManager();
//		while (it.hasNext()) {
//			City city = (City) it.next();
//			if(am.isFriend(city.getId())) {
//				it.remove();
//			}
//		}
	}

	@Override
	public List<City> getCitys() {
		List<Friend> all = city.getFriendManager().getAll();
		return buildAll(all);
	}

	private List<City> buildAll(List<Friend> all) {
		List<City> citys = Lists.newArrayList();
		for (Friend friend : all) {
			citys.add(friend.getFriendCity());
		}
		filter(citys);
		return citys;
	}

}
