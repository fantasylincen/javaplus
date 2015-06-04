package mongo.dtodefine;

/**
 * 黑市里面单件物品
 *
 * @author 林岑
 *
 */
interface HeiShiGoods {

	/**
	 * 物品的ID
	 *
	 * @return
	 */
	int getId();

	/**
	 * 剩余数量
	 *
	 * @return
	 */
	int getRemainCount();

	/**
	 * 限兑次数
	 * @return
	 */
	int getLimit();
	
	/**
	 * 是否已经兑换了
	 * @return
	 */
	boolean hasExchange();
	
	/**
	 * 每次兑换数量
	 * @return
	 */
	int getCountExchangeEvery();
}
