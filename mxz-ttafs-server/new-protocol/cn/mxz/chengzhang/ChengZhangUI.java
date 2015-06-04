package cn.mxz.chengzhang;

import java.util.List;

public interface ChengZhangUI {
	/**
	 * @return 总计返额
	 */
	int getBackAll();

	/**
	 * @return 购买价格
	 */
	int getPrice();
	
	/**
	 * @return 是否已经购买
	 */
	boolean getHasBought();
	
	/**
	 * 所有奖励列表
	 * @return
	 */
	List<ChengZhangItemUI> getItems();
}
