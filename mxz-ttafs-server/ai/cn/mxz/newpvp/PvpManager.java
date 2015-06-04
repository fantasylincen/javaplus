package cn.mxz.newpvp;

import java.util.List;

import mongo.gen.MongoGen.PvpWarSituationDto;
import cn.mxz.city.City;
import cn.mxz.user.team.god.ShenJiaAble;

public interface PvpManager extends ShenJiaAble {

	/**
	 * 我的Pvp信息
	 *
	 * @return
	 */
	PvpPlayer getPlayer();

	/**
	 * PVP 奖励
	 */
	PvpReward getPvpReward();

	/**
	 * 膜拜
	 */
	void kowtow();

	/**
	 * 别人攻击我的战况信息
	 *
	 * @return
	 */
	List<PvpWarSituationDto> getWarSituationsBeHit();

	void addPvpWinTimes(int i);

	void addPvpWinSteakToday(int i);

	void addPvpLoseTimes(int i);

	void clearPvpWinSteakToday();

	/**
	 * 攻击别人的战况信息
	 *
	 * @return
	 */
	List<PvpWarSituationDto> getWarSituationsAttack();

	/**
	 * 获得玩家随机获取的3个PVP玩家
	 * @return
	 */
	List<PvpFightUser> getUsersRandomed();

	/**
	 * 临时保存玩家随机的3个pvp玩家
	 * @param ls
	 */
	void saveRandomUsers(List<PvpFightUser> ls);

	/**
	 * 在随机的3个玩家中, 取得指定ID 的pvp玩家
	 * @param userId
	 * @return
	 */
	PvpFightUser getUserRandomed(String userId);

	/**
	 * 清空随机pvp列表
	 */
	void clearUsersRandomed();

	/**
	 * 获得当前的pvp段位奖励
	 * @return
	 */
	DanReward getDanReward();

//	/**
//	 * 下一个pvp段位奖励
//	 * @return
//	 */
//	IDanReward getNextDanReward();

	/**
	 * 所属的用户
	 * @return
	 */
	City getCity();

	/**
	 * 所有战况日志
	 * @return
	 */
	List<PvpWarSituationDto> getWarSituationsAll();

}
