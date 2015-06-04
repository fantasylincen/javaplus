package cn.mxz.czfk2;

import java.util.List;

public interface FeedBack2UI {

	/**
	 * 礼包列表
	 * @return
	 */
	List<FeedBack2Item> getItems();
	
	/**
	 * 活动期间累计充值
	 * @return
	 */
	int getRechargeAll();
	
	/**
	 * 活动倒计时  (秒)
	 * @return
	 */
	int getRemainSec();
}
