package cn.mxz.newpvp;

import cn.javaplus.math.Fraction;
import cn.mxz.city.City;
import cn.mxz.events.EventDispatcher2;
import cn.mxz.formation.PlayerCamp;
import db.domain.Pvp;
import db.domain.PvpExtra;

public interface PvpPlayer extends EventDispatcher2, Comparable<PvpPlayer> {

	/**
	 * 当前连胜次数
	 *
	 * @return
	 */
	int getCurrentWinStreak();

	/**
	 * 失败场次
	 *
	 * @return
	 */
	int getLoseTimes();

	/**
	 * 最大连胜次数
	 *
	 * @return
	 */
	int getMaxWinStreak();

	/**
	 * 精力
	 *
	 * @return
	 */
	int getPower();

	/**
	 * 20. 时的排行, 如果已经领取了排行奖励, 那么这个值为-1
	 *
	 * @return
	 */
	int getRankInEightClock();

	/**
	 * 胜率
	 *
	 * @return
	 */
	float getWinPercent();

	/**
	 * 晋级赛中胜利次数
	 *
	 * @return
	 */
	int getWinTimesInUpWatch();

	/**
	 * 今日胜利次数
	 *
	 * @return
	 */
	int getWinTimesToday();

	/**
	 * 玩家城池
	 *
	 * @return
	 */
	City getCity();

	/**
	 * 积分
	 *
	 * @return
	 */
	int getScore();

	/**
	 * 段位ID
	 *
	 * @return
	 */
	int getDan();

	/**
	 * 修行
	 *
	 * @return
	 */
	Fraction getPractice();

	/**
	 * 晋级状态: w:胜利 l: 失败 t:平局 例如 : wwwt: 前三场胜利 第四场平局 第五场未开始
	 *
	 * @return
	 */
	String getStatus();

	/**
	 * 剩余挑战次数
	 *
	 * @return
	 */
	int getRemainTimes();

	/**
	 * 增加剩余挑战次数
	 *
	 * @param times
	 */
	void addRemainTimes(int times);

	/**
	 * 扣除挑战次数
	 *
	 * @param times
	 */
	void reduceRemainTimes(int times);

	/**
	 * Pvp段位ID,,,修行值..基础数据
	 *
	 * @return
	 */
	Pvp getDto();
	/**
	 * 晋级结束
	 *
	 * @param isWin
	 */
	void upWatchEnd(boolean isWin);

	/**
	 * 是否进入晋级赛
	 *
	 * @return
	 */
	boolean isInUpMatch();

	/**
	 * 普通赛胜利
	 *
	 * @param battle
	 */
	void normalWatchWin(PvpBattle battle);

	/**
	 * 是否可晋级
	 *
	 * @return
	 */
	boolean canEnterUpWatch();

	/**
	 * 晋级
	 */
	void enterUpWatch();

	/**
	 * 获得在晋级赛中的当前战斗次数
	 *
	 * @return
	 */
	int getFightingTimes();

	/**
	 * 升级
	 */
	void levelUp();

	/**
	 * 从晋级模式, 转换到正常模式
	 */
	void toNormal();

//	/**
//	 * 发送奖励
//	 */
//	void sendPrize();

	/**
	 * 晋级失败后
	 *
	 * @param upper
	 */
	void upWatchFaild(City upper);

	/**
	 * 被攻击了
	 *
	 * @param under
	 */
	void addBeAttackMessage(PlayerCamp under);

	/**
	 * 在所有用户中的排行
	 *
	 * @return
	 */
	int getRankInAll();

	/**
	 * 被膜拜了
	 */
	void beKowtow();

	/**
	 * 额外信息
	 *
	 * @return
	 */
	PvpExtra getExtraDto();

	/**
	 * 更新到数据库
	 */
	void commit();

	/**
	 * 被膜拜次数
	 *
	 * @return
	 */
	int getBeKowtowTimes();

	/**
	 * 最好排名
	 *
	 * @return
	 */
	int getBestRank();

	/**
	 * 更新最好排名
	 *
	 * @param warSituationId
	 *            战况信息ID
	 * @param newRank
	 *            新排行
	 */
	void updateBestRank(int newRank, int warSituationId);

	/**
	 * 是否不能领取排名奖励
	 *
	 * @return
	 */
	boolean canNotReceiveRankReward();

	/**
	 * 领取竞技场排名奖励
	 */
	void receiveRankReward();

	int getKowtowTimes();

	/**
	 * 剩余使用符文次数
	 *
	 * @return
	 */
	int getRemainUseFuWenTimes();

	/**
	 * 排位上升时的战况信息ID
	 *
	 * @return
	 */
	int getWarsituationId();

	void updateWarSituationId(int situationId);

	/**
	 * 段位等级
	 *
	 * @return
	 */
	int getDanLevel();

	int getShenJia();

	/**
	 * 胜利次数
	 *
	 * @return
	 */
	int getWinTimes();

	/**
	 * 降低胜点(修行) 只降级 不降段位
	 */
	void reducePractice(int reduce);

	/**
	 * 是否处于保护期
	 *
	 * @return
	 */
	boolean isProtected();

	/**
	 * 减少一次被保护次数
	 */
	void reduceProtectTime();

	/**
	 * 连续失败次数
	 * @return
	 */
	int getLoseStreak();

	/**
	 * 玩家ID
	 * @return
	 */
	String getId();

//	/**
//	 * 曾今是否进入过晋级赛
//	 * @return
//	 */
//	boolean isUpAgo();
//
//	/**
//	 * 是否第一次晋级
//	 * @param b
//	 */
//	void setIsFirstUp(boolean b);

	/**
	 * 当前段位内胜利次数
	 * @return
	 */
	int getCurrentWinTimesInDanId();
	
	/**
	 * 当前段位内胜利次数
	 * @return
	 */
	void setCurrentWinTimesInDanId(int times);

	void updateShenJia();

	boolean isTianZun();

	/**
	 * 是否是角色创建当天
	 * @return
	 */
	boolean isCreateToday();

	boolean hasInit();

	void saveCreateDay();

	boolean isFirstUp();

	void setFirstUp(boolean b);

	int getRankRewardRemainSec();
}
