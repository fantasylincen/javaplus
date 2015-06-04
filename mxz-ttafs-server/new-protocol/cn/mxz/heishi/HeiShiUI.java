package cn.mxz.heishi;

import java.util.List;

/**
 * 神秘商店UI
 * @author 林岑
 *
 */
public interface HeiShiUI {

	/**
	 * 可购买的物品列表
	 * @return
	 */
	List<GoodsUI> getGoods();

	/**
	 * 美酒数量
	 * @return
	 */
	int getWineCount();

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
