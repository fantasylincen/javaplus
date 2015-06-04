package config.equipment;

import java.util.HashMap;
import java.util.Map;

/**
 * 装备类型
 * @author DXF
 */
public enum EquipType {

	/** 武器 */
	WEAPON		( 1 ),
	
	/** 头盔 */
	HELMET		( 2 ),
	
	/** 胸甲 */
	CUIRASS		( 3 ),
	
	/** 盾牌 */
	SHIELD		( 4 ),
	
	/** 饰品 */
	JEWELRY		( 5 ),
	
	/** 鞋子 */
	SHOE		( 6 );
	
	
	private final byte number;
	
	EquipType( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, EquipType> numToEnum = new HashMap<Byte, EquipType>();
	static{
		for( EquipType a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static EquipType fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
}
