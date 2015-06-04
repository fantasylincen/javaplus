package mongo.dtodefine;
import java.util.List;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * 玩家黑市商品
 * @author 林岑
 *
 */
@Dao
interface Zbsr  {

	@Key
	String getUname();

	/**
	 * 所有黑市里面的物品
	 * @return
	 */
	List<ZbsrGoods> getEquipments();
}
