package cn.mxz.nvwa;

import java.util.List;

public interface NvwaUI {
	/**
	 * 剩余时间（秒）
	 */
	int getRemainSec();
	
	/**
	 * 全服已购
	 */
	int getCountNow();
	
	/**
	 * 物品id
	 * @return
	 */
	int getBoxId();
	
	/**
	 * 所有分割线
	 */
	List<LineItem> getLines();
	
	/**
	 * 可反元宝
	 */
	int getGoldWillBack();
	
	/**
	 * 已买个数
	 */
	int getBoughtCount();
	
	/**
	 * 原价
	 */
	int getPriceOld();
	
	/**
	 * 现价
	 */
	int getPriceNew();
	
	/**
	 * 已消耗元宝数
	 */
	int getGoldAll();
	
	/**
	 * 拥有元宝数
	 */
	int getGold();
	
	/**
	 * 拥有金贝壳数
	 */
	int getJinBeiKe();
}
