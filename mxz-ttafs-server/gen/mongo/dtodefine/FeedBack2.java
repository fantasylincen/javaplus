package mongo.dtodefine;

import java.util.List;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface FeedBack2 {

	@Key
	String getUname();

	/**
	 * 充值量
	 */
	int getRechargeGold();

	/**
	 * 已经领取过的礼包id
	 * @return
	 */
	List<ReceivedBox2> getReceivedIds();
}
