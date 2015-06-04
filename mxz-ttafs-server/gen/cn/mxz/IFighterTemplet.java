package cn.mxz;

public interface IFighterTemplet extends Attribute {

	int getSkill();
	/**
	 * id
	 */
	public abstract int getId();

	/**
	 * 神将名字
	 */
	public abstract String getName();

	/**
	 * 职业
	 */
	public abstract int getProfessionId();

	/**
	 * 初始等级
	 */
	public abstract int getLevel();

	/**
	 * 品质id
	 */
	public abstract int getQuality();
	/**
	 * 怪物类型（1小怪 2boss 3主角 4神将 5活动小怪 6活动boss 7精英神将）
	 */
	public abstract int getCategory();
	/**
	 * 普通攻击技能
	 */
	public abstract int getCommonSkill();

	public abstract float getEffectOdds();

	public abstract int getSuffId();

}