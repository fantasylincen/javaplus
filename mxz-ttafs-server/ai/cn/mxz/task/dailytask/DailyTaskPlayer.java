package cn.mxz.task.dailytask;

public interface DailyTaskPlayer {

	int getCornucopiaTimes();

	int getCorona();

	int getDogzDunWuTimes();

	int getProtectedDaJiTimes();

	int getPracticeTimes();

	int getFishTimes();

	int getFindGodTimes();

	/**
	 * 装备强化次数
	 *
	 * @return
	 */
	int getEquipmentLevelUpTimes();

	int getDogzFeedTimes();

	int getSnatchTimes();

	int getSacrificeTimes();

	// int getPvpWinningStreak();

	int getRechargeGold();

	boolean isFinishNightAndNoonShangXiang();

	/**
	 * 通关任意副本
	 *
	 * @return
	 */
	boolean hadPassMissionToday();

	/**
	 * 今日任务完成个数
	 *
	 * @return
	 */
	int getTaskFinishCountToday();

	/**
	 * PVP连胜次数
	 *
	 * @return
	 */
	int getPvpWinningStreak();

	/**
	 * 今日攻击神魔的次数
	 *
	 * @return
	 */
	int getAttackShenMoTimesQLYJ();

	/**
	 * 元神升级次数
	 *
	 * @return
	 */
	int getYuanShenLevelUpTimes();

	/**
	 * 用道具百里挑一寻仙的次数
	 *
	 * @return
	 */
	int getBLTYTimes();

	/**
	 * 用道具万里挑一寻仙的次数
	 *
	 * @return
	 */
	int getWLTYTimes();

	/**
	 * 渡劫次数
	 * @return
	 */
	int getDuJieTimes();

	/**
	 * 是否点赞了
	 * @return
	 */
	boolean hasDianZan();

	/**
	 * 挑战Boss次数
	 * @return
	 */
	int getTiaoZhanBossTimes();

	/**
	 * 黑市购买次数
	 * @return
	 */
	int getHeiShiTimes();

	/**
	 * 每日上香次数
	 * @return
	 */
	short getShangXiangTimes();

}
