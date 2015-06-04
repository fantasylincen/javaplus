package cn.mxz.openserver;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

/**
 * 开服礼包
 *
 * @author 林岑
 *
 */
@Communication
public interface OpenServerTransform {

	/**
	 * 开服礼包界面
	 * @return
	 */
	OpenServerUI getOpenServerUI();

	/**
	 * 领取第几天的开赴礼包奖励
	 * @param day
	 */
	void receive(int day);

	void setUser(City user);
}
