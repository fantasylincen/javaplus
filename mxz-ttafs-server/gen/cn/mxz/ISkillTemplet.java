package cn.mxz;

public interface ISkillTemplet {

	/**
	 * 技能ID
	 */
	public abstract int getId();

	/**
	 * 所占法力值
	 */
	public abstract int getMigc();

	/**
	 * 是否第一回合触发(0否 1是)
	 */
	public abstract int getIsTragerFirstRound();

	/**
	 * 技能释放类型（1主动伤害 2辅助buff3被动）
	 */
	public abstract int getReleaseType();

	/**
	 * 主动技能伤害值公式系数
	 */
	public abstract float getHarmPar();

	/**
	 * 固定值伤害（与防御无关）
	 */
	public abstract int getHarmFixed();

	/**
	 * 主动技能伤害成长系数
	 */
	public abstract float getHarmGrowPar();

	/**
	 * 主动技能伤害成长固定值（与防御无关）
	 */
	public abstract float getHarmGrowFixed();

	/**
	 * 是否可以主动使用（1可以主动使用 0不主动使用）
	 */
	public abstract int getAccordUse();

}