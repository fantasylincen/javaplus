package cn.mxz.newpvp;

import java.util.List;

import cn.mxz.city.City;

/**
 * 竞技场, 用于统一管理所有竞技场玩家的信息
 * @author 林岑
 *
 */
public interface PvpPlace {

	int getRankInAll(String id);

	int getRankInAll(PvpPlayer player);

	/**
	 * 获得我附近的玩家
	 *
	 * @return
	 */
	List<PvpPlayer> getNearests(String userId);

	/**
	 * 获取指定ID的pvp玩家
	 * @param id
	 * @return
	 */
	PvpPlayer get(String id);

	/**
	 * 获取某一段的PVP玩家
	 * @param rankStart
	 * @param count
	 * @return
	 */
	List<PvpPlayer> getTops(int rankStart, int count);

	/**
	 * 第一名玩家
	 * @return
	 */
	PvpPlayer getFirst();

	/**
	 * 随机N个玩家给city挑战
	 * @param city
	 * @return
	 */
	List<PvpFightUser> getRandomUsers(City city);

	void resort();

	List<PvpPlayer> getAll();

	/**
	 * 最后一位天尊
	 * @return
	 */
	PvpPlayer getLastTianZun();

	void init();

	List<PvpPlayer> getTianZuns();

//	/**
//	 * 当前服务器有多少个天尊
//	 * @return
//	 */
//	int getTianZunCount();
}
