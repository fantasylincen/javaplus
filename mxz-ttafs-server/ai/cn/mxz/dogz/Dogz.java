package cn.mxz.dogz;

import cn.mxz.Attribute;
import cn.mxz.events.EventDispatcher2;
import cn.mxz.fighter.Fighter;
import db.domain.UserDogz;

/**
 *
 * 神兽
 *
 * @author 林岑
 *
 */
public interface Dogz extends EventDispatcher2, Fighter {

	/**
	 * 是否出战
	 *
	 * @return
	 */
	boolean isFighting();

	/**
	 * 获得对所有神将的加成
	 *
	 * @return
	 */
	Attribute getAdditionToGod();

	/**
	 * 设置神兽出战
	 *
	 * @param b
	 */
	void setFighting(boolean b);

	/**
	 * @return
	 */
	DogzSkill getSkill();

	/**
	 * 激活主动技能作用
	 */

	void activateSkill();

	/**
	 * 反激活神兽技能
	 */
	void deactivateSkill();

	/**
	 * 神兽技能是否被激活
	 *
	 * @return
	 */
	boolean isActivateSkill();

	/**
	 * 是否被激怒了
	 * @return
	 */
	boolean isAngry();

	/**
	 * 增加怒气值
	 * @param a
	 */
	void addAngry(int a);

	/**
	 * 怒气值
	 * @return
	 */
	int getAngryValue();

	/**
	 * 不发怒了
	 */
	void unangry();

	/**
	 * 设置神兽的怒气
	 * @param initAngry
	 */
	void setAngry(int initAngry);

	UserDogz getDto();

}
