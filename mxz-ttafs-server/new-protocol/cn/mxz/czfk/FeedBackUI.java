package cn.mxz.czfk;

import java.util.List;

public interface FeedBackUI {

	/**
	 * 礼包列表
	 * @return
	 */
	List<FeedBackItem> getItems();
	
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
