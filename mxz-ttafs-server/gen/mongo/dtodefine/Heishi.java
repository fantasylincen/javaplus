package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface Heishi {

	@Key
	String getUserName();
	
	int		getQsjs();
	
	/**
	 * 商品的购买情况，采用propId,count,propId,count来分隔
	 * @return
	 */
	String	getBuyStr();
	
	
}
