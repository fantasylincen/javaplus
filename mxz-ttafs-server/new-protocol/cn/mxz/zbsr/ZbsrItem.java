package cn.mxz.zbsr;

/**
 * 可购买的某个物品
 * @author 林岑
 *
 */
public interface ZbsrItem {

	/**
	 * 物品ID   装备商人表里面的id
	 * @return
	 */
	int getId();

	/**
	 * 装备类型ID
	 * @return
	 */
	int getEquipmentTempletId();
	
	/**
	 * 名字
	 * @return
	 */
	String getName();

	/**
	 * 原价
	 * @return
	 */
	int getGoldOld();

	/**
	 * 现价
	 * @return
	 */
	int getGoldNew();
	
	/**
	 * 是否特价
	 * @return
	 */
	boolean getIsTeJia();
	
	/**
	 * 是否可以兑换
	 * @return
	 */
	boolean getCanReceive();
}
