package cn.mxz.chuangzhen;

import java.util.List;

public interface RankingListPro {

	/**
	 * 昨日排名
	 * @return
	 */
	int getRankYestoday();

	/**
	 * 当前排名
	 * @return
	 */
	int getRank();

	/**
	 * 排行榜上的玩家列表
	 * @return
	 */
	List<ChuangZhenRankPlayer> getPlayers();
}
