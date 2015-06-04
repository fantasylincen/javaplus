package cn.mxz.battle;

/**
 * 战斗结果 如果!isWin()且!isLose()  (又没输又没赢)   那么平局
 * @author 林岑
 */
interface WarResult {
	
	/**
	 * 是否胜利
	 * @return
	 */
	
	boolean isWin();
	
	/**
	 * 是否失败
	 
	 * @return
	 */
	boolean isLose();
}
