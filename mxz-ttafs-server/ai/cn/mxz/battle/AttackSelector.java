package cn.mxz.battle;

import cn.mxz.fighter.Fighter;

/**
 * 战斗出手顺序选择器
 * @author 林岑
 */
interface AttackSelector {

	/**
	 * 重置选择器, 该方法调用后, 又从第一个活着的战士开始
	 */
	void reset();

	/**
	 * 是否还有人能出手
	 */
	boolean hasNext();

	/**
	 * 下一个出手的战士, 如果没有 了, 就返回null
	 */
	Fighter next();

}