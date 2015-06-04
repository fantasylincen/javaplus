package cn.mxz.pvp;

public interface PvpDuiHuanItem {

	/**
	 * 物品ID
	 * @return
	 */
	int getTypeId();
	
	/**
	 * 所需荣誉
	 * @return
	 */
	int getRongYuNeed();
	
	/**
	 * 今日可兑换xxx次
	 * @return
	 */
	int getChangeTimes();
	
	/**
	 * 是否可以兑换
	 * @return
	 */
	boolean getCanDuiHuan();
	
	/**
	 * 冷却时间 秒
	 * @return
	 */
	int getRemainSec();
}
