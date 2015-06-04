package define;

import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import user.UserInfo;
import user.UserManager;

/**
 * 游戏一些 默认值
 * 只打开文件读取 并不解析 调用者自己去解析
 * @author DXF
 */
public class DefaultCfg {

	private static final String FILE 			= "config/Default.xml";
	private static Element root					= null;
	
	/** 每页数据量 */
	public static final int 		PAGE_MAX 	= 20;
	
	/** 玩家最高等级 */
	public static final short 		USER_FULL_LEVEL;
	/** 英雄最高等级 */
	public static final short 		HERO_FULL_LEVEL;
	/** 每个玩家最大存储战报个数*/
	public static final int 		MAX_FIGHT_INFO_NUM;
	
	/** 玩家体力值上限*/
	public static final short 		MAX_STRENGTH;
	/** 玩家体力值每次回复时间*/
	public static final int			STRENGTH_REPLYTIME;
	/** 玩家体力值每次回复量*/
	public static final int			STRENGTH_REPLY_NUM;

	
	/** 好友赠送最大次数 */
	public static final short 		FRIEND_GV_TIMES;
	/** 好友领取体力最大次数 */
	public static final short 		FRIEND_GET_TIMES;
	/** 好友赠送初始值 */
	public static final int 		FRIEND_INIT_GV;

	/** 技能最高等级  */
	public static final byte 		SKILL_MAX_LEVEL;
	// 技能等级 加成  1.2倍，1.3倍，1.4倍，1.5倍，1.6倍，1.75倍。
	public static final float[] 	LEVE_LADDITION = { 1.2f, 1.3f, 1.4f, 1.5f, 1.6f, 1.75f };

	/** 最大吞噬个数 */
	public static final byte		MAX_DEVOUR_NUM;	
	/** 吞噬经验系数 */
	public static final short		DEVOUR_EXP_RATIO;	
	/** 吞噬经验经验消耗系数 */
	public static final float		DEVOUR_EXP_NEED_GOLD;	
	/** 学习队长技能每天免费次数 */
	public static final byte		DEVOUR_SKILL_TIME;	
	/** 学习队长刷新每次消耗水晶数 */
	public static final int			DEVOUR_SKILL_GOLD;

	/** 英雄出售 系数 */
	public static final short 		SELL_HERO_CASH;

	/** 英雄副本每日可用次数 */
	public static final byte 		ELITE_TODAY_COUNT 	= 2;
	/** 英雄副本可购买次数 */
	public static final byte 		ELITE_BUY_COUNT 	= 2;
	/** 英雄副本购买的元宝数 */
	public static final int 		ELITE_BUYCOUNT_GOLD = 20;

	/** 邀请好友列表最大个数 */
	public static final short 		BEG_FRIEND_MAX_NUM 	= 100;

	
	/** 英雄背包 最大 个数 */
	public static final short 		HERO_BAG_MAX 		= 300;	
	/** 购买背包一次 消耗水晶 */
	public static final int 		HERO_BAG_BUY_GLOD	= 30;	
	/** 购买一次背包 增加个数 */
	public static final short 		HERO_BAG_BUY_ONE	= 5;
	
	/** 装备初始上限 */
	public static final short 		EQUIP_BAG_MAX_INIT	= 100;	
	/** 装备背包 最大 个数 */
	public static final short 		EQUIP_BAG_MAX 		= 300;	
	/** 购买装备背包一次 消耗水晶 */
	public static final int 		EQUIP_BAG_BUY_GLOD	= 30;	
	/** 购买一次装备背包 增加个数 */
	public static final short 		EQUIP_BAG_BUY_ONE	= 5;	
	
	/** 好友上限 最大 个数 */
	public static final short 		FRIEND_BAG_MAX 			= 120;	
	/** 购买好友上限一次 消耗水晶 */
	public static final int 		FRIEND_BAG_BUY_GLOD		= 30;	
	/** 购买一次好友上限 增加个数 */
	public static final short 		FRIEND_BAG_BUY_ONE		= 5;
	/** PVP匹配 购买次数每次消耗水晶数 */
	public static final byte 		PVP_MATE_BUY_COUNT_GOLD	= 20;
	/** 购买金币  金币系数 10W*/
	public static final int 		BUY_CASH_VALUE			= 100000;
	/** 购买金币  水晶系数 50*/
	public static final int 		BUY_GOLD_VALUE			= 50;
	
	/** PVP匹配 胜利获得分数 */
	public static final int 		PVP_MATE_WIN_GRADE[]	= { 4, 5, 6 };	
	/** PVP匹配 失败获得分数 */
	public static final int 		PVP_MATE_DEFEAT_GRADE[] = { 2, 2, 2 };
	/** PVP匹配 每日次数 */
	public static final byte 		PVP_MATE_TODAY_COUNT 	= 5;
	/** PVP匹配 每日可购买次数基数 */
	public static final byte 		PVP_MATE_BUY_COUNT 		= 3;
	/** PVP匹配 每日可被抢夺次数 */
	public static final byte 		PVP_MATE_BY_LOOTCOUNT	= 5;
	

	/** 邀请好友最大个数 */
	public static final short 		INVITED_MAX_NUM 		= 100;
	
	/** 副本完后增加的友情点 非好友 */
	public static final short 		ECTYPE_A_NOT_FRIEND 	= 5;
	/** 副本完后增加的友情点 好友 */
	public static final short 		ECTYPE_A_FRIEND 		= 10;

	/** 副本战斗邀请协助好友 限制时间  在规定时间内只能邀请一次 (秒)  3小时 3*60*60*/
	public static final int 		FIGHT_INVITE_MAXTIME 	= 10800;
	

	/** 装备合成 立即完成消耗水晶系数*/
	public static final int 		EQUIP_SYNTHESIS_COMPLETE= 25;
	
	
	private static final int		DRAGON_INIT_HP;
	
	private static final int		DRAGON_INIT_ATTACK;

	
	/** 月卡最大上限天数 */
	public static final short 		MONTH_CARD_FATE_MAX 	= 90;
	
	/** 月卡每次添加次数 */
	public static final short 		MONTH_CARD_FATE_ONE 	= 30;
	
	/** 初始 挑战圣诞节活动副本次数 */
	public static final byte 		INIT_CHRISTMASTIMES 	= 8;
	
	/** 大龙伤害排行最多个数 */
	public static final int 		DRAGON_DAMAGE_MAXCOUNT 	= 50;
	
	/** 需要打排位的等级 */
	public static final int 		NEED_RANKING_LEVEL;
	
	public static int getDragonInitHp(){
		int numberTo_11_25 	= 0;
		int numberTo_26_45 	= 0;
		int numberTo_46_65 	= 0;
		int numberTo_66_120 = 0;
		List<UserInfo> ls			= UserManager.getInstance().getMemoryAllUser();
		for( UserInfo u : ls ){
			if( u.getLevel() >= 11 && u.getLevel() <= 25 ){
				++numberTo_11_25;
			}else if( u.getLevel() >= 26 && u.getLevel() <= 45 ){
				++numberTo_26_45;
			}else if( u.getLevel() >= 46 && u.getLevel() <= 65 ){
				++numberTo_46_65;
			}else if( u.getLevel() >= 66 ){
				++numberTo_66_120;
			}
		}
		// 当天大龙血量=[(11至25级玩家人数*5000)+(26至45级玩家人数*100000)+(46至65级玩家人数*1000000)+(66级以上玩家人数*3000000)]
		long thatDayHp = (numberTo_11_25 * 5000) + (numberTo_26_45 * 100000) + (numberTo_46_65 * 1000000) + (numberTo_66_120 * 3000000);
		if( thatDayHp > Integer.MAX_VALUE ) thatDayHp = Integer.MAX_VALUE;
		return (int) (thatDayHp > DRAGON_INIT_HP ? thatDayHp : DRAGON_INIT_HP);
	}
	
	public static int getDragonInitAttack(){
		return DRAGON_INIT_ATTACK;
	}
	
	static{
		init();
		
		USER_FULL_LEVEL 		= Short.parseShort( getByCfg( "max" , "user_lv" ) );
		HERO_FULL_LEVEL 		= Short.parseShort( getByCfg( "max" , "hero_lv" ) );
		MAX_FIGHT_INFO_NUM 		= Integer.parseInt( getByCfg( "max" , "fight_info" ) );
		MAX_STRENGTH 			= Short.parseShort( getByCfg( "strength" , "max" ) );
		STRENGTH_REPLYTIME 		= Integer.parseInt( getByCfg( "strength" , "replytime" ) );
		STRENGTH_REPLY_NUM 		= Integer.parseInt( getByCfg( "strength" , "replyt_num" ) );
		FRIEND_GV_TIMES 		= Short.parseShort( getByCfg( "friend" , "gv_times" ) );
		FRIEND_GET_TIMES 		= Short.parseShort( getByCfg( "friend" , "get_times" ) );
		FRIEND_INIT_GV 			= Integer.parseInt( getByCfg( "friend" , "init_gv" ) );
		SKILL_MAX_LEVEL 		= Byte.parseByte( getByCfg( "max" , "skill_max_level" ) );
		MAX_DEVOUR_NUM 			= Byte.parseByte( getByCfg("hero_growup", "max_devour_num") );
		DEVOUR_EXP_RATIO 		= Short.parseShort( getByCfg("hero_growup", "devour_exp_ratio") );
		DEVOUR_EXP_NEED_GOLD 	= Byte.parseByte( getByCfg("hero_growup", "devour_exp_need_gold") );
		DEVOUR_SKILL_TIME 		= Byte.parseByte( getByCfg("hero_growup", "devour_skill_time") );
		DEVOUR_SKILL_GOLD 		= Byte.parseByte( getByCfg("hero_growup", "devour_skill_gold") );
		SELL_HERO_CASH 			= Short.parseShort( getByCfg( "hero" , "sell_cash" ) );
		DRAGON_INIT_HP			= Integer.parseInt( getByCfg( "dragon" , "hp" ) );
		DRAGON_INIT_ATTACK		= Integer.parseInt( getByCfg( "dragon" , "attack" ) );
		NEED_RANKING_LEVEL		= Integer.parseInt( getByCfg( "create" , "need_ranking_level" ) );
	}	
	
	private static void init()
	{
		SAXBuilder builder 	= new SAXBuilder();    
		Document document	= null;
		try {
			document 		= builder.build( FILE );
			root 			= document.getRootElement();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * 获得默认数值
	 * @param cname(主节点名)
	 * @param name(副节点名)
	 * @return
	 */
	public static String getByCfg( String cname, String name )
	{
		if( name == "" ){
			return root.getChildText( cname );
		}
		
		Element ret = root.getChild( cname );
		
		if( ret == null ) return "";
		
		return ret.getChildText( name );
	}
	
	/**
	 * 获得所有类型的副本ID
	 * @return
	 */
	public static short[] getEctypFirstID() {
		
		short[] id 	= new short[4];
		
		id[0]		= Short.parseShort( getByCfg( "init_ectype", "common_id" ) );
		id[1]		= Short.parseShort( getByCfg( "init_ectype", "elite_id" ) );
		id[2]		= Short.parseShort( getByCfg( "init_ectype", "activity_id" ) );
		id[3]		= Short.parseShort( getByCfg( "init_ectype", "challenge_id" ) );
		
		return id;
	}
	
	public static void main(String[] args) {
		System.out.println( DefaultCfg.getByCfg( "maxlv" , "user" ) );
	}


}
