package cn.mxz.openserver;

import java.util.List;

/**
 * 开服礼包UI界面
 * @author 林岑
 *
 */
public interface OpenServerUI {

	/**
	 * 所有开赴礼包奖励
	 * @return
	 */
	List<OpenServerRewardUI> getRewards();
}
