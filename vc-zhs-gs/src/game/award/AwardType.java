package game.award;

import game.log.L;

import java.util.HashMap;
import java.util.Map;

/**
 * 游戏内各种奖励类型
 * 
 * @author admin
 * 
 */
public enum AwardType {

	/**
	 * 金币
	 */
	CASH( 1 , "金币" ) {
		@Override
		public L getLogType() {
			return L.L_000;
		}
	},

	/**
	 * 体力，控制玩家的战斗时间
	 */
	STRENGTH( 2 , "体力") {
		@Override
		public L getLogType() {
			return L.L_000;
		}
	},
	
	/**
	 * 经验
	 */
	EXP( 3, "经验" ) {
		@Override
		public L getLogType() {
			return L.L_000;
		}
	},
	
	/**
	 * 装备
	 */
	PROP( 4, "装备" ) {
		@Override
		public L getLogType() {
			return L.L_000;
		}
	},
	
	/**
	 * 水晶
	 */
	GOLD( 5, "水晶" ) {
		@Override
		public L getLogType() {
			return L.L_004;
		}
	},
	
	/**
	 * 英雄
	 */
	HERO( 6, "英雄" ) {
		@Override
		public L getLogType() {
			return L.L_000;
		}
	},
	
	/**
	 * 英雄经验
	 */
	HERO_EXP( 7, "英雄经验" ) {
		@Override
		public L getLogType() {
			return L.L_000;
		}
	},
	
	/**
	 * 友情点
	 */
	FD_VALUE( 8, "友情点" ) {
		@Override
		public L getLogType() {
			return L.L_000;
		}
	},
	
	/**
	 * 奖杯数
	 */
	TROPHY( 9, "奖杯" ) {
		@Override
		public L getLogType() {
			return L.L_000;
		}
	};
	

	private final byte number;

	private final String name;
	
	AwardType( int n, String name ) {
		
		number 		= (byte) n;
		this.name 	= name;
	}

	private static final Map<Byte, AwardType> intToEnum = new HashMap<Byte, AwardType>();
	static {
		for (AwardType a : values()) {
			intToEnum.put(a.number, a);
		}
	}

	public static AwardType fromNumber(int n) {
		return intToEnum.get((byte)n);
	}

	public byte toNumber() {
		return number;
	}
	
	public String toName(){
		return name;
	}
	
	abstract public L getLogType();
	
	public static void main(String[] args) {
		
	}

	

	
}