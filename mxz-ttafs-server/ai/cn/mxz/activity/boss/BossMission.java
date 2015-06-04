package cn.mxz.activity.boss;

import java.util.List;

import cn.mxz.battle.BattleExpPrize;
import cn.mxz.user.mission.Mission;

/**
 * Boss副本
 * 
 * @author 林岑
 * 
 */

public interface BossMission extends Mission {

	/**
	 * Boss列表
	 * 
	 * @return
	 */
	List<Boss> getBossList();

	/**
	 * 获得指定ID的Boss
	 * 
	 * @param bossId
	 * @return
	 */
	Boss getBoss(int bossId);

	/**
	 * 获得最后一次Boss奖励
	 * 
	 * @return
	 */
	BossReward getLastBossReward();

	/**
	 * 好友分享积分信息
	 * 
	 * @return
	 */
	FriendSharedPoints getFriendPoints();

	/**
	 * 我发现的Boss
	 * 
	 * @return
	 */
	List<Boss> getBossFoundByMe();

	/**
	 * 保存每次打Boss的奖励
	 * 
	 * @param reward
	 */
	void saveBossReward(BossReward reward);

	/**
	 * 保存Boss副本战士经验奖励
	 * 
	 * @param fighterPrize
	 */
	void save(List<BattleExpPrize> fighterPrize);
}
