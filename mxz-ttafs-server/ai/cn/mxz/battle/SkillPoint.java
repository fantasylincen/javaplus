package cn.mxz.battle;

/**
 * 技能作用点
 * @author 林岑
 *
 */
interface SkillPoint {

	int getHpEnd();

	int getPosition();

	boolean isUnder();

	/**
	 * 该技能释放后攻击增量
	 * @return
	 */
	int getAttack();

	/**
	 * 该技能释放后防御增量
	 * @return
	 */
	int getDefend();


	/**
	 * 该技能释放后法术增量增量
	 * @return
	 */
	int getMAttack();


	/**
	 * 该技能释放后法术防御增量
	 * @return
	 */
	int getMDefend();


	/**
	 * 该技能释放后暴击增量
	 * @return
	 */
	int getCrit();


	/**
	 * 该技能释放后闪避增量
	 * @return
	 */
	int getDodge();

	/**
	 * 该技能释放后速度增量
	 * @return
	 */
	int getSpeed();

	/**
	 * 生效的BuffID
	 * @return	如果没有, 返回-1
	 */
	int getBufferId();

	boolean isCrit();

	boolean isBlock();

	boolean isHit();

}
