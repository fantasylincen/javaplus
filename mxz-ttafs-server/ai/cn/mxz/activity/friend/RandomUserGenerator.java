package cn.mxz.activity.friend;

import java.util.Collection;
import java.util.List;

import cn.mxz.city.City;

/**
 * 随机好友推荐器
 * @author 林岑
 *
 */
public interface RandomUserGenerator {

	/**
	 * 推荐最多10个用户待玩家添加为好友
	 * @return
	 */
	Collection<City> recommendFriends();

	/**
	 * 所有可添加为好友的玩家列表
	 * @return
	 */
	List<City> getCitys();

}