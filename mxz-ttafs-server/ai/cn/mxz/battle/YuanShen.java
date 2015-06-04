package cn.mxz.battle;

import cn.javaplus.math.Fraction;
import cn.mxz.Attribute;
import cn.mxz.user.team.god.ShenJiaAble;

/**
 * 元神
 * @author 林岑
 *
 */
public interface YuanShen extends ShenJiaAble {

	/**
	 * 元神类型
	 * @return
	 */
	int getType();

	/**
	 * 元神等级
	 * @return
	 */
	int getLevel();

	/**
	 * 加成
	 * @return
	 */
	Attribute getAddition();

	/**
	 * 增加经验
	 * @param exp 
	 */
	void addExp(int exp);

	/**
	 * 重置这个元神
	 */
	void reset();

	/**
	 * 元神经验
	 * @return
	 */
	Fraction getExp();

	/**
	 * 总经验
	 * @return
	 */
	int getExpAll();

	float getExpPar();

	int getStep();

}
