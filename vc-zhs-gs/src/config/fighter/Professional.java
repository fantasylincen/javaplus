package config.fighter;

import java.util.HashMap;
import java.util.Map;

/**
 * NPC 职业：
 * 	0-坦克
	1-战士
	2-刺客
	3-法师
	4-弓箭手
	5-火枪手
	6-治疗
	7-圣骑士
	8-神
 * @author DXF
 * 
 */
public enum Professional {
	
	/** 坦克 */
	TANKS(0) {
		@Override
		public int getCaptainSkillOdds() {
			return 11;
		}
	},

	/** 战士 */
	FIGHTER(1) {
		@Override
		public int getCaptainSkillOdds() {
			return 11;
		}
	},
	
	/** 刺客 */
	ASSASSIN(2) {
		@Override
		public int getCaptainSkillOdds() {
			return 11;
		}
	},
	
	/** 法师 */
	MASTER(3) {
		@Override
		public int getCaptainSkillOdds() {
			return 11;
		}
	},
	
	/** 弓箭手 */
	ARCHER(4) {
		@Override
		public int getCaptainSkillOdds() {
			return 11;
		}
	},
	
	/** 火枪手 */
	MUSKETEERS(5) {
		@Override
		public int getCaptainSkillOdds() {
			return 11;
		}
	},
	
	/** 治疗 */
	TREATMENT(6) {
		@Override
		public int getCaptainSkillOdds() {
			return 11;
		}
	},
	
	/** 圣骑士 */
	PALADIN(7) {
		@Override
		public int getCaptainSkillOdds() {
			return 11;
		}
	},
	
	/** 神 */
	GOD(8) {
		@Override
		public int getCaptainSkillOdds() {
			return 0;
		}
	}, 
	
	/** 所有 */
	ALL(9) {
		@Override
		public int getCaptainSkillOdds() {
			return 12;
		}
	};
	
	private final byte number;
	
	Professional( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, Professional> numToEnum = new HashMap<Byte, Professional>();
	static{
		for( Professional a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static Professional fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}

	
	/**
	 * 获得该职业的队长技能的 学习几率
	 * @return
	 */
	public abstract int getCaptainSkillOdds();
}
