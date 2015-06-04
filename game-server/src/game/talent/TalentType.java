package game.talent;

import java.util.HashMap;
import java.util.Map;

/**
 * 天赋 类型
 * @author DXF
 *
 */
public enum TalentType {

	/** 物理攻击 */
	PHY_ATTACK( 1 ) {
		@Override
		public String getDBStr() {
			return "phy_attack";
		}

		@Override
		public boolean isTimelyUpdate() {
			return true;
		}
	},
	
	/** 法术攻击 */
	MAGIC_ATTACK( 2 ) {
		@Override
		public String getDBStr() {
			return "magic_attack";
		}

		@Override
		public boolean isTimelyUpdate() {
			return true;
		}
	},
	
	/** 生命 */
	HP( 3 ) {
		@Override
		public String getDBStr() {
			return "hp";
		}

		@Override
		public boolean isTimelyUpdate() {
			return true;
		}
	},
	
	/** 物理穿透 */
	PHY_PENETRATION( 4 ) {
		@Override
		public String getDBStr() {
			return "phy_penetration";
		}

		@Override
		public boolean isTimelyUpdate() {
			return false;
		}
	},
	
	/** 法术穿透 */
	MAGIC_PENETRATION( 5 ) {
		@Override
		public String getDBStr() {
			return "magic_penetration";
		}

		@Override
		public boolean isTimelyUpdate() {
			return false;
		}
	},
	
	/** 物理抗性 */
	PHY_RESIST ( 6 ) {
		@Override
		public String getDBStr() {
			return "phy_resist";
		}

		@Override
		public boolean isTimelyUpdate() {
			return false;
		}
	},
	
	/** 法术抗性*/
	MAGIC_RESIST ( 7 ) {
		@Override
		public String getDBStr() {
			return "magic_resist";
		}

		@Override
		public boolean isTimelyUpdate() {
			return false;
		}
	}, 
	
	/** 闪避*/
	DODGE( 8 ) {
		@Override
		public String getDBStr() {
			return "dodge";
		}

		@Override
		public boolean isTimelyUpdate() {
			return false;
		}
	}, 
	
	/** 命中*/
	HIT( 9 ) {
		@Override
		public String getDBStr() {
			return "hit";
		}

		@Override
		public boolean isTimelyUpdate() {
			return false;
		}
	}, 
	
	/** 暴击*/
	CRIT( 10 ) {
		@Override
		public String getDBStr() {
			return "crit";
		}

		@Override
		public boolean isTimelyUpdate() {
			return false;
		}
	}, 
	
	/** 韧性*/
	TOUGHNESS( 11 ) {
		@Override
		public String getDBStr() {
			return "toughness";
		}

		@Override
		public boolean isTimelyUpdate() {
			return false;
		}
	};
	
	private final byte 				number;
	
	TalentType( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, TalentType> numToEnum = new HashMap<Byte, TalentType>();
	static{
		for( TalentType a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static TalentType fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	/**
	 * 获取DB对应字段名
	 * @return
	 */
	public abstract String getDBStr();
	
	/**
	 * 是否及时更新
	 */
	public abstract boolean isTimelyUpdate();
}
