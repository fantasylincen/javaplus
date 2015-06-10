package game.battle;

import java.util.HashMap;
import java.util.Map;



public enum AttackType {

	/**
	 * 回合开始
	 */
	BEGIN_ROUND(100), 
	
	/**
	 * 普通攻击
	 */
	NORMAL_ATTACK(1),
	
	/**
	 * 技能攻击
	 */
	SKILL_ATTACK(2),
	
	/**
	 * 宠物攻击
	 */
	PET_ATTACK(3),
	
	/**
	 * 反击
	 */
	COUNTER_ATTACK(4);
	
	
	private final byte number;
	AttackType( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	public static AttackType fromNumber( byte number ) {
		return numToEnum.get( number );
	}
	
	private static final Map<Byte, AttackType> numToEnum = new HashMap<Byte, AttackType>();
	static{
		for( AttackType a : values() ){
			numToEnum.put( a.number, a );
		}
	}

}
