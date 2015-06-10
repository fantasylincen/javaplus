package game.pvp;


import java.util.HashMap;
import java.util.Map;

/**
 * 匹配赛类型
 * @author DXF
 */
public enum MatchingType {

	// 绿卡
	GREEN_CARD( 0 ),
	
	// 蓝卡
	BLUE_CARD( 1 ),
	
	// 紫卡
	PURPLE_CARD( 2 );
	
	private final byte 				number;
	
	MatchingType( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, MatchingType> numToEnum = new HashMap<Byte, MatchingType>();
	static{
		for( MatchingType a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static MatchingType fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
}
