package cn.mxz.temp;

import cn.mxz.equipment.EquipmentSnapshoot;
import cn.mxz.market.Goods;

/**
 * 临时变量, 注意这些临时变量, 都不是持久的. 都只是缓存到内存里面, 玩家下线就会消失
 *
 * @author 林岑
 *
 */
public enum TempKey {

	/**
	 * 金币
	 */

	CASH(Integer.class),

	/**
	 * 元宝
	 */
	GOLD(Integer.class),

	/**
	 * 玩家的ID, 现在city.getId 表示角色ID 而不是玩家ID
	 */
	USER_ID(String.class),

//	/**
//	 * 玩家的客户端类型
//	 */
//	CLIENT_TYPE(Integer.class),

	/**
	 * 玩家这一次操作的操作名 格式不确定
	 */
	OPERATION_THIS_TIME(String.class),

	/**
	 * 玩家最后一次操作的操作名 格式不确定
	 */
	OPERATION_LAST_TIME(String.class),

	/**
	 * 本次购买的道具类型
	 */
	BUY_PROP_THIS_TIME(Goods.class),
	
	/**
	 * 本次即将购买的道具类型
	 */
	WILL_BUY_PROP_THIS_TIME(Goods.class),

	/**
	 * 装备快照
	 */
	EQUIPMENT_SNAPSORT(EquipmentSnapshoot.class);

	TempKey(Class<?> c) {
	}

}
