package cn.mxz.task.achieve;

import java.util.List;

import cn.mxz.levelupreward.Reward;

public interface LevelUpRewardPlayer {

	boolean isFinishAllSendGold();

	/**
	 * 领取过的所有奖励等级
	 * @return
	 */
	List<Integer> getAcceptLevels();

	List<Reward> getRewards();

}
