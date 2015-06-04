package cn.mxz.formation;

/**
 * 可移动位置的阵型接口
 * @author Administrator
 *
 */
interface IFormationMove {
	
	/**
	 * 是否包含此位置
	 * @param targetPos
	 * @return
	 
	boolean isContain( int targetPos );*/
	
	/**
	 * 此位置的英雄下阵
	 * @param srcHeroId
	 */
	void remove(int srcPos);
	
	/**
	 * 直接上阵，不检测上阵人数
	 * @param srcHeroId
	 * @param desPos
	 */
	void put(int srcHeroId, int desPos);
	
	/**
	 * 需要检测上阵人数的
	 * @param srcHeroId
	 * @param desPos
	 */
	boolean add(int srcHeroId, int desPos);

}
