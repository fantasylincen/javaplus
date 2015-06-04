package cn.mxz;

public interface HostTemplet {

	/**
	 * 品阶等级组合列
	 */
	String getSpetLv();

	/**
	 * 技能品质
	 */
	int getFormationSpet();

	/**
	 * 技能等级
	 */
	int getFormationLv();

	/**
	 * 基础概率
	 */
	float getHostPro();

	/**
	 * 必定升级的总概率
	 */
	float getSumPro();

}