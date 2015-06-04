package cn.mxz.rankinglist;

import cn.mxz.city.City;

/**
 * 排行榜
 *
 * @author 林岑
 */
public interface RankingList {

	/**
	 * 获得玩家的排名
	 *
	 * @param user
	 * @return
	 */
	int getRank(City city);

	/**
	 * 排行榜刷新剩余时间
	 *
	 * @return
	 */
	int getRefreshSec();

	/**
	 * 全服等级排行
	 *
	 * @param id
	 *            玩家帐号
	 * @return
	 */
	int getLevelRank(String id);
}
