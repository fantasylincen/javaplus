package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface Nvwa {

	@Key
	String getUname();

	/**
	 * 买了多少个
	 * @return
	 */
	int getBuyCount();
	
	/**
	 * 累计花了多少钱
	 * @return
	 */
	int getGoldAll();
	
	/**
	 * 是否已经退还了多余的钱
	 * @return
	 */
	boolean isSendBack();
}
