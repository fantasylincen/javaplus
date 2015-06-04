package cn.mxz;

public interface BasisTemplet {

	/**
	 * 品阶等级组合列
	 */
	String getSpetLv();

	/**
	 * 技能等级
	 */
	int getFormationLv();

	/**
	 * 技能品质
	 */
	int getFormationSpet();

	/**
	 * 基础概率
	 */
	float getBasisPro();

}