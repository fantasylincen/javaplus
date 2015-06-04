package cn.mxz.activity.boss;

import cn.mxz.battle.Battle;
import cn.mxz.battle.BattlePrizeAble;

interface BossBattle extends Battle, BattlePrizeAble {

	/**
	 * 生成Boss战斗奖励
	 * 
	 * @return
	 */
	
	BossReward generateReward();

}
