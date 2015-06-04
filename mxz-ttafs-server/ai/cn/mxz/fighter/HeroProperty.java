package cn.mxz.fighter;

/**
 * 英雄属性
 * 
 * @author 林岑
 * 
 */
public enum HeroProperty {

	HP,

	SP,

	ATTACK,

	MATTACK,

	DEFEND,

	MDEFEND,

	DODGE,

	HIT,

	SPEED,

	/** 传承次数 */
	INHERIT_COUNT,

	/**
	 * 技能经验
	 */
	SKILL_EXP,

	SKILL_LEVEL,

	QUALITY, 
	
	SKILL_ID, 
	
	/** 法力 */
	FA_LI;

	public int getValue() {

		return ordinal();
	}

	
	public static HeroProperty value(int value) {

		for (HeroProperty e : values()) {

			if (e.getValue() == value) {

				return e;
			}
		}

		throw new RuntimeException("无法识别的值");
	}
}
