package cn.mxz.pvp;

import java.util.List;

public interface PvpDuiHuanUI {
	
	/**
	 * 我的荣誉
	 * @return
	 */
	int getRongYu();
	
	/**
	 * 物品列表
	 * @return
	 */
	List<PvpDuiHuanItem> getItems();
}
