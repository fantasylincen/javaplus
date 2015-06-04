package config.equipment;

import java.util.HashMap;
import java.util.Map;

/**
 * 装备属性类型
 * @author DXF
 */
public enum EquPropertyType {

	/** 物理攻击 */
	PHY_ATTACK		( 1 ),
	
	/** 魔法攻击 */
	MAG_ATTACK		( 2 ),
	
	/** 生命  */
	HP				( 3 ),
	
	/** 暴击 */
	CRIT 			( 4 ),
	
	/** 韧性 */
	TENACITY		( 5 ),
	
	/** 命中 */
	HIT				( 6 ),
	
	/** 闪避 */
	DODGE			( 7 );
	
	
	private final byte number;
	
	EquPropertyType( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, EquPropertyType> numToEnum = new HashMap<Byte, EquPropertyType>();
	static{
		for( EquPropertyType a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static EquPropertyType fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
}
