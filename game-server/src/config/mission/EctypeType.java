package config.mission;

import java.util.HashMap;
import java.util.Map;


/**
 * 副本  类型
 * @author DXF
 *
 */
public enum EctypeType {

	/**
	 * 普通
	 */
	COMMON(1) {
		@Override
		public String getDBTypeToStr() {
			return "common";
		}
	},
	
	/**
	 * 精英
	 */
	ELITE(2) {
		@Override
		public String getDBTypeToStr() {
			return "elite";
		}
	},
	
	/**
	 * 活动
	 */
	ACTIVITY(3) {
		@Override
		public String getDBTypeToStr() {
			return "activity";
		}
	},
	
	/**
	 * 挑战
	 */
	CHALLENGE(4) {
		@Override
		public String getDBTypeToStr() {
			return "challenge";
		}
	};
	
	private final byte number;
	
	EctypeType( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, EctypeType> numToEnum = new HashMap<Byte, EctypeType>();
	static{
		for( EctypeType a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static EctypeType fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	/**
	 * 获得在DB的字段名字 
	 * @return
	 */
	public abstract String getDBTypeToStr();
	
	
}
