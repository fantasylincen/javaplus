package cn.mxz.mission;

import java.util.List;

import cn.mxz.battle.MissionBattle;
import cn.mxz.bossbattle.Prize;
import cn.mxz.events.EventDispatcher2;
import cn.mxz.mission.star.MissionStarManager;
import cn.mxz.mission.type.EventType;
import db.domain.UserMission;


public interface IMissionManager extends EventDispatcher2 {

	IMission enter( int missionId );



	/**
	 * path用于考虑主线，支线交叉点上分路的情况
	 * @param path
	 * @return
	 */
	Object run(int path, EventType type );

	int[] getDemonCamp(int path);


	void giveUp( int missionId );


	IMission getCurMission();



	/**
	 * 副本星星管理器
	 * @return
	 */
	MissionStarManager getStarManager();

	/**
	 * 当前正在进行的副本ID, 如果没有, 值为-1
	 * @return
	 */
	int getCurMissionId();


	int getMaxMissionId();

	/**
	 * 用了还原丹之后，清空玩家的死亡次数
	 */
	void clearDieCount();

	/**
	 * 获取玩家的当前死亡次数，用于计算死亡时间
	 * @return
	 */
	int getDieCount();

	/**
	 * 成功闯关，结束后，开宝箱
	 * @return
	 */
	List<Prize> successCrossMission();



	void backMainBranch();



	/**
	 * 获取此关卡的最大星星数量
	 * @param missionId
	 * @return
	 */
//	int getMaxStar(int missionId);


	/**
	 * 仅仅针对当前正在进行的关卡
	 * @return
	 */
	Boolean branchIsCross();



	MissionBattle challenge(int missionId, Boolean isBranch);






	int[] getBossCamp(int missionId, Boolean isBranch);


	/**
	 * 针对任意关卡
	 * @return
	 */
	Boolean branchIsCross(int missionId);


	/**
	 * 获取指定关卡的今日闯关次数(int[0]),今日重置次数(int[1])
	 * @param missionId
	 * @return
	 */
	int[] getChallengeData(int missionId);



	void refreshChallenge(int missionId, Boolean IsBranch);



	MoppingUp getMoppingUp();



	/**
	 * 是否完美通关
	 * @param missionId
	 * @return
	 */
	boolean isPerfect(int missionId);


	/**
	 * 用一个数组返回章节进度，[总boss数，完成boss数]
	 * @param chapterId
	 * @return
	 */
	int[] getCompletion(int chapterId);


	/**
	 * 获取某个章节的所有的宝箱奖励
	 * @param chapterId
	 * @return
	 */
	List<MissionBox> getBoxes(int chapterId);
	
	List<UserMission> getRunningMission();
	
	/**
	 * 如果此关卡玩家仅仅通了 主线，支线未通过，则允许玩家直接跳到分叉点上
	 * 此时，当前关卡必须不为空
	 */
	public void directJumpBranch();

}
