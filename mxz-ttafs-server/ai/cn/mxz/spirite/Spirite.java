package cn.mxz.spirite;

/**
 * 战士魂魄
 * @author 林岑
 *
 */
public interface Spirite {

	/**
	 * 魂魄数量
	 * @return
	 */
	int getCount();

	/**
	 * 品质
	 * @return
	 */
	int getStep();

	/**
	 * 战士ID
	 * @return
	 */
	int getTypeId();

	/**
	 * 我是否拥有这个魂魄对应的战士
	 * @return
	 */
	boolean hasFighter();

	/**
	 * 魂魄是否装满了, 达到招募或者升级条件
	 * @return
	 */
	boolean isFull();

	/**
	 * 升星所需最大值
	 * @return
	 */
	int getCountMax();

	db.domain.Spirite getDto();

	boolean canLevelUp();
}
