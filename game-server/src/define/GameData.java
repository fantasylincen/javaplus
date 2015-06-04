package define;

import game.award.AwardInfo;
import game.award.AwardType;

import java.util.ArrayList;
import java.util.List;

import deng.xxoo.utils.XORandom;

import util.SystemTimer;

/**
 * 游戏数据 
 * @author DXF
 */
public class GameData {

	public static int maxOnlineNumber		= 0;
	
	public static int maxOnlineTime			= 0;
	
	public volatile static int NUMERATIONTODAY = 0;
	
	// 排位赛  排名
	public static List<Integer> ranking		= new ArrayList<Integer>();
	
//	排行	金币	水晶
//	1	80w	600
//	2	70w	550
//	3	60w	500
//	4	50w	450
//	5	40w	400
//	6 - 10	35w	380
//	11 - 20	30w	350
//	21 - 30	25w	320
//	31 - 40	20w	300
//	41 - 50	15w	280
//	51 - 100	10w	230
//	100 - 200	—	200
//	201 - 300	—	180
//	301 - 500	—	150
//	501后	—	120
//	调整后		
//	名次			金币（w）	水晶
//	1			100		600
//	2			80		500
//	3			60		400
//	4~10		50		300
//	11~50		30		200
//	51~100		20		150
//	101~300		15		100
//	301~500		10		50
//	501~1000	5		10
//	1001~n		1		0

	// 排位奖励
	public static int[][]award			= { 
//		{ 1, 800000, 600 },
//		{ 2, 700000, 550 },
//		{ 3, 600000, 500 },
//		{ 4, 500000, 450 },
//		{ 5, 400000, 400 },
//		{ 10, 350000, 380 },
//		{ 20, 300000, 350 },
//		{ 30, 250000, 320 },
//		{ 40, 200000, 300 },
//		{ 50, 150000, 280 },
//		{ 100, 100000, 230 },
//		{ 200, 100000, 200 },
//		{ 300, 100000, 180 },
//		{ 500, 100000, 150 },
//		{ -1, 100000, 120 }
		{ 1, 1000000, 600 },
		{ 2, 800000, 500 },
		{ 3, 600000, 400 },
		{ 10, 500000, 300 },
		{ 50, 300000, 200 },
		{ 100, 200000, 150 },
		{ 300, 150000, 100 },
		{ 500, 100000, 50 },
		{ 1000, 50000, 10 },
		{ -1, 10000, 0 }
		};
	
	
	// 活跃人数
	public static List<Integer> dailyActiveUsers = new ArrayList<Integer>();
	
	
	// 是否在线 新服前三日登陆
	public static boolean isHaveMikkaLogin_3(){
		return (SystemTimer.currentTimeMillis() - SystemCfg.START_SERVER_T) < 259200000L;
	}
	
	// 是否在线 新服前四日登陆
	public static boolean isHaveMikkaLogin_4 = false;
	
	public final static int[] hthemeWeekData = { 10033,10043,10105,10109,10094,10063,10037 };
	
	public final static int[] christmasDungeonAward = { 1004, 1104, 1204, 1304, 1404, 1504, 1604, 1704, 2904, 3904, 4904, 5904, 6904 };

	/** 获取圣诞活动副本奖励  */
	public static AwardInfo getChristmasDungeonAward() {
		int index = XORandom.run( 0, GameData.christmasDungeonAward.length - 1 );
		return new AwardInfo( AwardType.PROP , GameData.christmasDungeonAward[index], 1 );
	}
	

}
