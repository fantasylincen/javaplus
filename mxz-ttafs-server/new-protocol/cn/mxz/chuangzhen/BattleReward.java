package cn.mxz.chuangzhen;

import java.util.List;

/**
 * 渡劫奖励界面
 * @author 林岑
 *
 */
public interface BattleReward {

	/**
	 * 3个物品奖励
	 * @return
	 */
	List<PropReward> getPropReward();

	/**
	 * 每颗星星 获得的金币
	 * @return
	 */
	int getCashEveryStar();

	/**
	 * 最小楼层
	 * @return
	 */
	int getFloorMin();

	/**
	 * 最大楼层数
	 * @return
	 */
	int getFloorMax();

	/**
	 * 获得星星数
	 * @return
	 */
	int getStar();
}
