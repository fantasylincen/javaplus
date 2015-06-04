package cn.mxz.onlinereward;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

/**
 * 在线奖励
 * @author 林岑
 *
 */
@Communication
public interface OnlineRewardTransform {

	/**
	 * 在线奖励界面
	 * @return
	 */
	OnlineRewardUI getUI();
	
	/**
	 * 领取某个
	 * @param id   excel id
	 * @return
	 */
	OnlineRewardUI receiveById(int id);

	void setUser(City user);
}
