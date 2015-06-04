package cn.mxz.prop;

/**
 * 可添加到背包中的物品
 * @author 	林岑
 * @since	2012年11月8日 17:16:54
 */

public interface IntoBagAble {

	/**
	 * 类型ID
	 */
	int getTypeId();

	/**
	 * 道具ID, 数据库单用户唯一标识
	 */
	int getId();

	/**
	 * 等级
	 */
	int getLevel();

}
