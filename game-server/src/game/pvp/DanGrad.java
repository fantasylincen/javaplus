package game.pvp;

import java.util.HashMap;
import java.util.Map;

/**
 * 段位
 * @author DXF
 */
public enum DanGrad {
	
	HEROIC_BRASS( 1, "英勇黄铜" ),
	
	SURRENDER_SILVER( 2, "不屈白银" ),
	
	GLORY_GOLD_3( 3, "荣耀黄金Ⅲ" ),
	
	GLORY_GOLD_2( 4, "荣耀黄金Ⅱ" ),
	
	GLORY_GOLD_1( 5, "荣耀黄金Ⅰ" ),
	
	LUXURY_PLATINUM_3( 6, "华贵铂金Ⅲ" ),
	
	LUXURY_PLATINUM_2( 7, "华贵铂金Ⅱ" ),
	
	LUXURY_PLATINUM_1( 8, "华贵铂金Ⅰ" ),
	
	BRIGHT_DIAMOND_3( 9, "璀璨钻石Ⅲ" ),
	
	BRIGHT_DIAMOND_2( 10, "璀璨钻石Ⅱ" ),
	
	BRIGHT_DIAMOND_1( 11, "璀璨钻石Ⅰ" ),
	
	MOST_OF_THE_KING( 12, "最强王者" )
	
	;
	
	private final byte 				number;
	private final String			name;
	
	DanGrad( int n, String a ) {
		number 		= (byte) n;
		name		= a ;
	}
	public byte toNumber() {
		return number;
	}
	public String toName(){
		return name;
	}
	private static final Map<Byte, DanGrad> numToEnum = new HashMap<Byte, DanGrad>();
	static{
		for( DanGrad a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static DanGrad fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
}
