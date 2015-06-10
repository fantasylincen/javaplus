package datalogging;

public class ConsumelogF {

	public static final String PLAYER_NICKNAME 			= "玩家昵称";
	public static final int MAX 						= 16;
	
	
	/** 单抽 */ 
	public static final String SINGLE_PUMP 				= "单抽";//1
	/** 10连抽 */
	public static final String TENEVEN_SMOKE 			= "10连抽";//1
	/** 购买体力 */
	public static final String PURCHASE_PHYSICAL 		= "购买体力";//1
	/** 购买金币 */
	public static final String PURCHASE_GOLD 			= "购买金币";//1
	/** 购买PVP */
	public static final String PURCHASE_PVP 			= "购买PVP";//1
	/** 购买RANK */
	public static final String PURCHASE_RANK 			= "购买RANK";//1
	/** 刷新队长技能 */
	public static final String REFRESH_CAPTAINSKILLS 	= "刷新队长技能";//1
	/** 购买英雄背包上限 */
	public static final String PURCHASE_HEROBACKPACK  	= "购买英雄背包上限";//1
	/** 购买装备背包上限 */
	public static final String PURCHASE_EQUIPBACKPACK  	= "购买装备背包上限";//1
	/** 购买好友上限 */
	public static final String PURCHASE_FRIENDPACK  	= "购买好友上限";//1
	/** 充值获得 */
	public static final String PREPAID_PHONE  			= "充值获得";//1
	/** 大龙排行榜 */
	public static final String DRAGON_LIST  			= "大龙排行榜";//1
	/** RANK排行榜 */
	public static final String RANK_LIST  				= "RANK排行榜";//1
	/** 等级奖励 */
	public static final String LEVEL_REWARD  			= "等级奖励";//1
	/** 副本通关 */
	public static final String CUSTOMS_CLEARANCE  		= "副本通关";//1
	/** 系统发放 */
	public static final String DISTRIBUTED_SYSTEM  		= "系统发放";
	
	
	public static String get( int index ){
		switch( index ){
		case 0: return SINGLE_PUMP;
		case 1: return TENEVEN_SMOKE;
		case 2: return PURCHASE_PHYSICAL;
		case 3: return PURCHASE_GOLD;
		case 4: return PURCHASE_PVP;
		case 5: return PURCHASE_RANK;
		case 6: return REFRESH_CAPTAINSKILLS;
		case 7: return PURCHASE_HEROBACKPACK;
		case 8: return PURCHASE_EQUIPBACKPACK;
		case 9: return PURCHASE_FRIENDPACK;
		case 10: return PREPAID_PHONE;
		case 11: return DRAGON_LIST;
		case 12: return RANK_LIST;
		case 13: return LEVEL_REWARD;
		case 14: return CUSTOMS_CLEARANCE;
		case 15: return DISTRIBUTED_SYSTEM;
		}
		return "";
	}
	
	public static int getToIndex( String type ){
		switch( type ){
		case SINGLE_PUMP: return 0;
		case TENEVEN_SMOKE: return 1;
		case PURCHASE_PHYSICAL: return 2;
		case PURCHASE_GOLD: return 3;
		case PURCHASE_PVP: return 4;
		case PURCHASE_RANK: return 5;
		case REFRESH_CAPTAINSKILLS: return 6;
		case PURCHASE_HEROBACKPACK: return 7;
		case PURCHASE_EQUIPBACKPACK: return 8;
		case PURCHASE_FRIENDPACK: return 9;
		case PREPAID_PHONE: return 10;
		case DRAGON_LIST: return 11;
		case RANK_LIST: return 12;
		case LEVEL_REWARD: return 13;
		case CUSTOMS_CLEARANCE: return 14;
		case DISTRIBUTED_SYSTEM: return 15;
		}
		return 16;
	}
}
