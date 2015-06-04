package cn.mxz.hhdlb;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

/**
 * 豪华大礼包
 * 
 * @author 林岑
 */
@Communication
public interface HhdlbTransform {

	HhdlbUI getUI();

	/**
	 * 领取奖励
	 */
	void receive();

	void setUser(City user);
}
