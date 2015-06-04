package cn.mxz.czfk;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

/**
 * 充值反馈
 * @author 林岑
 *
 */
@Communication
public interface FeedBackTransform {

	/**
	 * 充值反馈界面
	 * @return
	 */
	FeedBackUI getUI();
	
	/**
	 * 领取某个礼包
	 * @param id   excel id
	 * @return
	 */
	FeedBackUI receiveById(int id);

	void setUser(City user);
}
