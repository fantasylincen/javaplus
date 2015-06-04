package mongo.dtodefine;

/**
 * 装备商人里面单件物品
 *
 * @author 林岑
 *
 */
interface ZbsrGoods {

	/**
	 * 物品的ID   装备商人表里面的id
	 *
	 * @return
	 */
	int getId();
	
	/**
	 * 装备id
	 * @return
	 */
	int getEquipmentTempletId();
	
	/**
	 * 是否是特价
	 * @return
	 */
	boolean getIsTeJia();
	
	/**
	 * 是否已经兑换了
	 * @return
	 */
	boolean getHasReceive();
}
