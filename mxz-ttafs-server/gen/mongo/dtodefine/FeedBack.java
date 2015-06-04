package mongo.dtodefine;

import java.util.List;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface FeedBack {

	@Key
	String getUname();

	/**
	 * 充值量
	 */
	int getRechargeGold();

	/**
	 * 已经领取过的礼包
	 * 
	 * @return
	 */
	List<ReceivedBox> getReceivedIds();
}
