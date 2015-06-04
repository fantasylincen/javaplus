package cn.mxz.czfk2;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

/**
 * 充值反馈2
 * @author 林岑
 *
 */
@Communication
public interface FeedBack2Transform {

	/**
	 * 充值反馈2界面
	 * @return
	 */
	FeedBack2UI getUI();
	
	/**
	 * 领取某个礼包
	 * @param id   excel id
	 * @return
	 */
	FeedBack2UI receiveById(int id);

	void setUser(City user);
}
