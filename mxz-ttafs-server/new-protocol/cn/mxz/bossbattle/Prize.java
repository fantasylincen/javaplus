package cn.mxz.bossbattle;

import cn.mxz.base.prize.AwardAble;


/**
 * 奖励
 * @author 林岑
 *
 */
public interface Prize extends AwardAble {

	/**
	 * 奖励ID
	 * @return
	 */
	int getId();

	/**
	 * 奖励数量
	 * @return
	 */
	int getCount();
}