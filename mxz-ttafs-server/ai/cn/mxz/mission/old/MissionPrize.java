package cn.mxz.mission.old;

import java.util.List;

import cn.mxz.battle.BattleExpPrize;

/**
 * 关卡奖励
 * @author 林岑
 *
 */
public interface MissionPrize {

	/**
	 * 战斗奖励, 提取之后战斗奖励会变为一个空列表
	 * @return
	 */
	
	List<BattleExpPrize> getFighterPrize();

	/**
	 * 道具奖励, 提取后道具奖励变为一个空列表
	 * @return
	 */
	
	List<PropPrize> getPropPrizes();

	/**
	 * 星星奖励
	 * @return
	 */
	
	int getStar();


}
