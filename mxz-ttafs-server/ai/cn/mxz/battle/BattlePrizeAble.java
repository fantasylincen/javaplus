package cn.mxz.battle;

import java.util.List;

import cn.mxz.mission.old.PropPrize;

/**
 * 带有战士经验奖励的副本
 * @author 林岑
 *
 */
public interface BattlePrizeAble extends Battle{

	/**
	 * @return 战士奖励
	 */
	public abstract List<BattleExpPrize> getExpPrize();

	/**
	 * 道具奖励
	 * @return
	 */
	public abstract List<PropPrize> getPropPrize();

}