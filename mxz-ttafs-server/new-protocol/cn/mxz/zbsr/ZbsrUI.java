package cn.mxz.zbsr;

import java.util.List;

/**
 * 装备商店UI
 * @author 林岑
 *
 */
public interface ZbsrUI {

	/**
	 * 可购买的物品列表
	 * @return
	 */
	List<ZbsrItem> getGoods();

	/**
	 * 元宝数量
	 * @return
	 */
	int getGold();

	/**
	 * 剩余刷新时间
	 * @return
	 */
	int getRemainSec();
}
