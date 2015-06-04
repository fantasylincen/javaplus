package cn.mxz.mission.star;

import java.util.List;

/**
 * 副本星星管理器
 * @author 林岑
 *
 */
public interface MissionStarManager {

	/**
	 * 根据章节ID, 获得这个章节里面所有星星获得情况
	 * @param chapterId
	 * @return
	 */
	List<MissionStar> getStarByCapterId(int chapterId);

	/**
	 * 根据副本ID, 获得这个章节里面所有星星获得情况
	 * @param sceneId
	 * @return
	 */
	List<MissionStar> getStarBySceneId(int sceneId);

	/**
	 * 清除指定副本的星星 历史最大星星数量不清空
	 * @param missionId
	 */
	void clear(int missionId);

	/**
	 * 获取某一关的副本星星
	 * @param missionId
	 * @return
	 */
	MissionStar getStarByMissionId(int missionId);

	/**
	 * 获取可以领取的星星奖励
	 *
	 * @param chapterId
	 * @return #RewardType
	 */
	int getStarReceivedStep(int chapterId);

	/**
	 * 领取某个章节当前可以领取的副本星星奖励
	 * @param chapterId
	 */
	void receivedStarReward(int chapterId);


	/**
	 * 领取副本星星奖励
	 *
	 * @param chapterId 章节ID
	 * @param percent 1: 30%通关奖励 2: 60%通关奖励 3: 90%通关奖励
	 *
	 */
	void receivedStarReward(int chapterId, int percent);

	/**
	 * 当前获得的星星数
	 * @param missionId
	 * @return
	 */
	MissionStar getCurrentStarByMissionId(int missionId);

	/**
	 * 增加Boss得星
	 * @param mapId
	 * @param star
	 */
	void addBossStar(int mapId, int star);

	/**
	 * 增加支线Boss得星
	 * @param mapId
	 * @param star
	 */
	void addBranchBossStar(int mapId, int star);

	/**
	 * 增加小怪得星
	 * @param mapId
	 * @param star
	 */
	void addDemonStar(int mapId, int star);

	boolean hasReceive(int chapterId, int type);

	/**
	 * 完成度
	 * @param chapterId
	 * @return
	 */
	float getPercent(int chapterId);

	/**
	 * 是否达到领取条件
	 * @param chapterId
	 * @param type
	 * @return
	 */
	boolean canReceive(int chapterId, int type);


}
