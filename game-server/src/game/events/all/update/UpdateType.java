package game.events.all.update;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新包 类型 ( 没有其他作用 )
 * @author DXF
 */
public enum UpdateType {
	
	/** 更新等级和经验 */
	U_1		(1) {
		@Override
		public int PACK_LEN() {
			return 16;
		}
	},
	
	/** 更新体力  */
	U_2		(2) {
		@Override
		public int PACK_LEN() {
			return 18;
		}
	},
	
	/** 更新金币 */
	U_3		(3) {
		@Override
		public int PACK_LEN() {
			return 14;
		}
	},
	
	/** 更新水晶 */
	U_4		(4) {
		@Override
		public int PACK_LEN() {
			return 14;
		}
	},
	
	/** 更新战斗力 */
	U_5		(5) {
		@Override
		public int PACK_LEN() {
			return 14;
		}
	},
	
	/** 更新友情点 */
	U_6		(6) {
		@Override
		public int PACK_LEN() {
			return 14;
		}
	},
	
	/** 更新是否有未读邮件 */
	U_7		(7) {
		@Override
		public int PACK_LEN() {
			return 11;
		}
	},
	
	/** 更新匹配段位 */
	U_8		(8) {
		@Override
		public int PACK_LEN() {
			return 11;
		}
	},
	
	/** 更新匹配积分 */
	U_9 	(9) {
		@Override
		public int PACK_LEN() {
			return 14;
		}
	},
	
	/** 更新英雄上限 */
	U_10 	(10) {
		@Override
		public int PACK_LEN() {
			return 12;
		}
	},
	
	/** 更新好友上限 */
	U_11 	(11) {
		@Override
		public int PACK_LEN() {
			return 12;
		}
	},
	
	/** 更新今日已匹配次数 */
	U_12 	(12) {
		@Override
		public int PACK_LEN() {
			return 11;
		}
	},
	
	/** 更新今日匹配次数上限 */
	U_13  	(13) {
		@Override
		public int PACK_LEN() {
			return 11;
		}
	},
	
	/** 更新今日购买体力次数 */
	U_14  	(14) {
		@Override
		public int PACK_LEN() {
			return 11;
		}
	},
	
	/** 更新今日购买体力上限 */
	U_15  	(15) {
		@Override
		public int PACK_LEN() {
			return 11;
		}
	},
	
	/** 更新购买PVP次数 */
	U_16  	(16) {
		@Override
		public int PACK_LEN() {
			return 11;
		}
	},
	
	/** 更新购买PVP上限 */
	U_17  	(17) {
		@Override
		public int PACK_LEN() {
			return 11;
		}
	},
	
	/** 更新是否有天赋可升级 */
	U_20  	(20) {
		@Override
		public int PACK_LEN() {
			return 11;
		}
	},
	
	/** 更新是否有系统奖励可领取 */
	U_21  	(21) {
		@Override
		public int PACK_LEN() {
			return 11;
		}
	},
	
	/** 更新匹配奖励是否可领取 */
	U_22  	(22) {
		@Override
		public int PACK_LEN() {
			return 11;
		}
	},
	
	/** 更新级位是否可提升 */
	U_23  	(23) {
		@Override
		public int PACK_LEN() {
			return 11;
		}
	},
	
	/** 更新是否有新的匹配录像 */
	U_24  	(24) {
		@Override
		public int PACK_LEN() {
			return 10;
		}
	},
	
	/** 更新每日队长技能免费次数 */
	U_25  	(25) {
		@Override
		public int PACK_LEN() {
			return 11;
		}
	}, 
	
	/** 更新vip等级 */
	U_26	(26) {
		@Override
		public int PACK_LEN() {
			return 11;
		}
	},
	
	/** 更新装备背包上限 */
	U_27	(27) {
		@Override
		public int PACK_LEN() {
			return 12;
		}
	},
	
	/** 更新月卡次数 */
	U_28	(28) {
		@Override
		public int PACK_LEN() {
			return 12;
		}
	},
	
	/** 更新奖杯数量 */
	U_29	(29) {
		@Override
		public int PACK_LEN() {
			return 12;
		}
	},
	
	/** 更新是否有人添加好友*/
	U_30	(30) {
		@Override
		public int PACK_LEN() {
			return 10;
		}
	},
	
	/** 更新月卡次数*/
	U_31	(31) {
		@Override
		public int PACK_LEN() {
			return 12;
		}
	},
	
	/** 更新月卡次数*/
	U_32	(32) {
		@Override
		public int PACK_LEN() {
			return 12;
		}
	},
	
	/** 更新限购*/
	U_33	(33) {
		@Override
		public int PACK_LEN() {
			return 12;
		}
	},
	
	
	/** 更新排位次数*/
	U_34	(34) {
		@Override
		public int PACK_LEN() {
			return 12;
		}
	},
	
	/** 更新各种购买所需要水晶*/
	U_35	(35) {
		@Override
		public int PACK_LEN() {
			return 125;
		}
	},
	
	/** 乐巴充值*/
	U_36	(36) {
		@Override
		public int PACK_LEN() {
			return 125;
		}
	},
	
	/** 添加装备 */
	U_90	(90) {
		@Override
		public int PACK_LEN() {
			return 10240;
		}
	},
	
	/** 删除装备 */
	U_91	(91) {
		@Override
		public int PACK_LEN() {
			return 4096;
		}
	},
	
	/** 更新装备 */
	U_92	(92) {
		@Override
		public int PACK_LEN() {
			return 4096;
		}
	},
	
	/** 添加英雄 */
	U_100  	(100) {
		@Override
		public int PACK_LEN() {
			return 40960;
		}
	},
	
	/** 删除英雄 */
	U_101 	(101) {
		@Override
		public int PACK_LEN() {
			return 10240;
		}
	},
	
	/** 更新英雄 */
	U_102  	(102) {
		@Override
		public int PACK_LEN() {
			return 40960;
		}
	},
	
	/** 添加好友 */
	U_110  	(110) {
		@Override
		public int PACK_LEN() {
			return 10240;
		}
	},
	
	/** 删除好友 */
	U_111  (111) {
		@Override
		public int PACK_LEN() {
			return 2048;
		}
	},
	
	/** 更新好友 */
	U_112  (112) {
		@Override
		public int PACK_LEN() {
			return 4096;
		}
	},
	
	/** 添加好友添加列表 */
	U_115  (115) {
		@Override
		public int PACK_LEN() {
			return 10240;
		}
	},
	
	/** 添加邀请好友添加列表 */
	U_116  (116) {
		@Override
		public int PACK_LEN() {
			return 4096;
		}
	},
	
	/** 更新邀请好友信息 */
	U_117  (117) {
		@Override
		public int PACK_LEN() {
			return 10240;
		}
	},
	
	/** 我邀请的好友信息 */
	U_118  (118) {
		@Override
		public int PACK_LEN() {
			return 4096;
		}
	},
	
	/** 公告 */
	U_120  (120) {
		@Override
		public int PACK_LEN() {
			return 1024;
		}
	}
	;
	
	private final byte number;
	UpdateType( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, UpdateType> numToEnum = new HashMap<Byte, UpdateType>();
	static{
		for( UpdateType a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static UpdateType fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	public abstract int PACK_LEN();
	
	public static void main( String[] args ){
		
		for( UpdateType code : values() ){
			
			System.out.println( "case " + code + ":" );
			System.out.println( "\t" );
			System.out.println( "\tbreak;" );
		}
		
	}
	
}
