package game.util;

import java.util.ArrayList;
import java.util.List;

import lua.Lua;
import lua.LuaProxy;

import util.RandomUtil;

import game.award.AwardInfo;
import game.award.AwardType;

/**
 * 大龙退出奖励
 * @author DXF
 */
public class DragonAwardManager {

	
	private static float odds[] 		= { 50, 35, 15 };
	
	private static int value[][]		= {
			{ 0, 2 },			// 1个1w经验小龙，随机1件白装。
			{ 0, 0, 2 },		// 2个1w经验小龙，随机1件白装。
			{ 0, 0, 1, 2, 2 }	// 2个1w经验小龙，1个5w经验小龙，随机2件白装。
	};
	
	private static AwardInfo award[]	= { 
			new AwardInfo( AwardType.HERO, 30081, 1, 1, 1, 0 ), 	// 0  1个1w经验小龙
			new AwardInfo( AwardType.HERO, 30129, 1, 1, 2, 0 ),		// 1  1个5w经验小龙
			new AwardInfo( AwardType.PROP, -1, 1 )					// 2     随机1件白装
	};
	
	public static List<Integer> prop	= new ArrayList<Integer>();
	
	
	/** 获得结果 */
	public static List<AwardInfo> getSesult(){
		
		List<AwardInfo> list = new ArrayList<AwardInfo>();
		
		int maxRand		= 0;
		for( int i = 0; i < odds.length; i++ )
			maxRand		+= odds[i];
		
		int i 			= 0;
		int rand		= RandomUtil.getRandomInt( 0, maxRand );
		for( ; i < odds.length; i++ ){
			if( rand - odds[i] < 0 ) break;
			rand		-= odds[i];
		}
		
		for( int j = 0; j < value[i].length; j++ ){
			int index 	= value[i][j];
			AwardInfo a	= new AwardInfo( award[index] );
			if( index == 2 ) // 如果是2是需要随机的
				a.setPropId( getEquID() );
			list.add( a );
		}
		
		return list;
	}
	
	private static int getEquID(){
		int rand		= RandomUtil.getRandomInt( 0, prop.size() - 1 );
		return prop.get(rand);
	}

	//////////////////////////////////////////////////大龙死亡或者结束发放奖励/////////////////////////////////////////////////////
//	调整前排名方式				调整后排名方式		
//	名次	钻石	金币（w）			名次	钻石	金币（w）
//	1~3	600	80				1		600	80
//	4~10	450	60	→→→		2		500	60
//	11~30	300	40			3		350	50
//	31~50	200	20			4~10	200	30
//	50~n	50	15			11~50	100	10
//							50~n	25	5

//	private static int axx[][] 	= {
		
		// 名次	水晶	  金币
//		{ 	3, 	600, 800000 },
//		{ 	10, 450, 600000 },
//		{ 	30, 300, 400000 },
//		{ 	50, 200, 200000 },
//		{ 	-1, 50,  150000 }
	
//		{ 	1, 	600, 800000 },
//		{ 	2, 	500, 600000 },
//		{ 	3, 	350, 500000 },
//		{ 	10, 200, 300000 },
//		{ 	50, 100, 100000 },
//		{ 	-1, 25,  50000 }
//	};
	
	/**
	 * 根据名次发放奖励
	 * @param i
	 * @return
	 */
	public static List<AwardInfo> get( int ranking ) {
		
		List<AwardInfo> list = new ArrayList<AwardInfo>();
		
//		for( int i = 0; i < axx.length; i++ ){
//			
//			if( ranking <= axx[i][0] || axx[i][0] == -1 )
//			{
//				AwardInfo a = new AwardInfo( AwardType.GOLD, axx[i][1] );
//				list.add( a );
//				a			= new AwardInfo( AwardType.CASH, axx[i][2] );
//				list.add( a );
//				break;
//			}
//		}
		
		LuaProxy lua 	= Lua.createLuaState( "gameData.lua" );
		Object[] ret 	= lua.retArray( 2, "getDragonRankingAward", ranking );
		lua.close();
		
		AwardInfo a = new AwardInfo( AwardType.GOLD, (int)(double)ret[0] );
		list.add( a );
		a			= new AwardInfo( AwardType.CASH, (int)(double)ret[1] );
		list.add( a );
		
		return list;
	}
	
}
