package cn.mxz.mission.old;

/**
 * 玩家副本星星管理器(可通过MissionStarManagerFactory获得对应的实例)
 * @author 林岑
 *
 */

public interface MissionStarManager_old {

	/**
	 * 记录玩家在mapId上获得 了多少颗星星
	 * @param mapId
	 * @param count
	 */
	void save(int mapId, int count);

	/**
	 * 当前获得的星星数量
	 * @param id 地图Id
	 * @return
	 */
	int getStar(int id);

	/**
	 * 地图最大星星数量
	 * @param id	地图id
	 * @return
	 */
	int getStarMax(int id);

	/**
	 * 结算得分
	 * @param mapId
	 */
	void calcScore(int mapId);

	void onGiveUpMap(GiveUpEvent e);

	/**
	 * 某关的副本评分
	 * @param id
	 * @return
	 */
	int getScore(int id);

}
