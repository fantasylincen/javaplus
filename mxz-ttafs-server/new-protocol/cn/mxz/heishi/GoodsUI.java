package cn.mxz.heishi;

/**
 * 可购买的某个物品
 * @author 林岑
 *
 */
public interface GoodsUI {

	/**
	 * 物品ID
	 * @return
	 */
	int getId();
	
	/**
	 * 该物品兑换后的奖励
	 * @return
	 */
	String getPrize();

	/**
	 * 物品数量
	 * @return
	 */
	int getCount();

	/**
	 * 名字
	 * @return
	 */
	String getName();

	/**
	 * 兑换所需
	 * @return
	 */
	String getNeeds();

	/**
	 * 剩余次数
	 * @return
	 */
	int getCountRemain();

	/**
	 * 限兑次数
	 * @return
	 */
	int getLimit();
	
	/**
	 * 是否已经被兑换过了
	 * @return
	 */
	boolean getHasExchange();
}
