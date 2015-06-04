package cn.mxz;


/**
 * 属性
 * @author 	林岑
 * @since	2013年6月3日 18:28:11
 */
public interface Attribute {

	/**
	 * 气血
	 * @return
	 */
	int getHp();

	/**
	 * 普通攻击
	 * @return
	 */
	int getAttack();

	/**
	 * 物理防御
	 * @return
	 */
	int getDefend();

	/**
	 * 法术攻击
	 * @return
	 */
	int getMAttack();

	/**
	 * 法术防御
	 * @return
	 */
	int getMDefend();

	/**
	 * 速度
	 * @return
	 */
	int getSpeed();

	int getCrit();
	int getRCrit();
	int getCritAddition();

	int getHit();
	int getDodge();

	int getBlock();
	int getRBlock();
	int getMagic();



}
