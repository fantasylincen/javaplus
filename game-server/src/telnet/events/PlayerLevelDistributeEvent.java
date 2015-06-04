package telnet.events;

import game.util.fighting.FightingFormula;

import java.io.PrintWriter;
import java.util.List;

import telnet.CommandBase;
import user.UserInfo;
import user.UserManager;

/**
 * 生成玩家实力
 * @author DXF
 */
public class PlayerLevelDistributeEvent extends CommandBase{

	private static final LevelDistribute[] levelTitle = { 
														new LevelDistribute( 1,35 ),
														new LevelDistribute( 36,50 ),
														new LevelDistribute( 51,60 ),
														new LevelDistribute( 61,70 ),
														new LevelDistribute( 71,80 ),
														new LevelDistribute( 81,90 ),
														new LevelDistribute( 91,100 ),
														new LevelDistribute( 101,110 ),
														new LevelDistribute( 111,120 ),
	};
	
//	2000以下
//	2000~5000
//	5001~10000
//	10001~20000
//	20001~30000
//	30001~40000
//	40001~50000
//	50001~60000
//	60001~70000
//	70001~80000
//	80001~100000
//	100001~120000
//	120001~140000
//	140001~160000
//	160001~180000
//	180001~200000
//	200000+
	private static final FightingDistribute[] fightingTitle = { 
															new FightingDistribute( 0,1999 ),
															new FightingDistribute( 2000,5000 ),
															new FightingDistribute( 5001,10000 ),
															new FightingDistribute( 10001,20000 ),
															new FightingDistribute( 20001,30000 ),
															new FightingDistribute( 30001,40000 ),
															new FightingDistribute( 40001,50000 ),
															new FightingDistribute( 50001,60000 ),
															new FightingDistribute( 60001,70000 ),
															new FightingDistribute( 70001,80000 ),
															new FightingDistribute( 80001,100000 ),
															new FightingDistribute( 100001,120000 ),
															new FightingDistribute( 120001,140000 ),
															new FightingDistribute( 140001,160000 ),
															new FightingDistribute( 160001,180000 ),
															new FightingDistribute( 180001,200000 ),
															new FightingDistribute( 200001,999999999 ),
	};
	
	@Override
	public void run(PrintWriter out, byte jurisdiction, String... args) throws Exception {
		
		final int level_len		= levelTitle.length;
		int[] level_data 		= new int[level_len];
		for( int i = 0; i < level_len; i++ )
			level_data[i]	= 0;
		
		final int fighting_len	= fightingTitle.length;
		int[]  fighting_data 	= new int[fighting_len];
		for( int i = 0; i < fighting_len; i++ )
			fighting_data[i]	= 0;
		
		List<UserInfo> ls			= UserManager.getInstance().getMemoryAllUser();
		for( UserInfo ui : ls ){
			
			// 等级
			for( int i = 0; i < level_len; i++ ){
				if( levelTitle[i].isMeet( ui.getLevel() ) ){
					++level_data[i];
				}
			}
			
			// 战斗力
			for( int i = 0; i < fighting_len; i++ ){
				if( fightingTitle[i].isMeet( FightingFormula.run(ui) ) )
					++fighting_data[i];
			}
		}
		
		
		String content 	= "";
		out.print( "\r\n" );
		out.print( "召唤师等级" );
		out.print( "\r\n" );
		for( int i = 0; i < level_len; i++ ){
			content 	= levelTitle[i].toString() + "\t" + level_data[i];
			out.print( content );
			out.print( "\r\n" );
		}
		
		out.print( "\r\n" );
		out.print( "团队实力" );
		out.print( "\r\n" );
		for( int i = 0; i < fighting_len; i++ ){
			content 	= fightingTitle[i].toString() + "\t" + fighting_data[i];
			out.print( content );
			out.print( "\r\n" );
		}
		
		out.print( "\r\n" );
		out.print( "生成完毕！\r\n" );

	}

	@Override
	public void clear(PrintWriter out) {
		
	}

}

// 等级
class LevelDistribute{
	
	public short 	minLv;
	
	public short 	maxLv;
	
	public LevelDistribute( int i, int j ){
		minLv 	= (short) i;
		maxLv	= (short) j;
	}
	
	public boolean isMeet( short level ){
		return level <= maxLv && level >= minLv;
	}
	
	public String toString(){
		return minLv + "-" + maxLv + "：" + (maxLv < 100 ? "\t" : "");
	}
}

// 战斗力
class FightingDistribute{
	
	public int 	minFig;
	
	public int 	maxFig;
	
	public FightingDistribute( int i, int j ){
		minFig 	= i;
		maxFig	= j;
	}
	
	public boolean isMeet( int level ){
		return level <= maxFig && level >= minFig;
	}
	
	public String toString(){
		if( minFig == 0 )
			return "2000以下：";
		if( maxFig == 999999999 )
			return "200000+：";
		return minFig + "-" + maxFig + "：";
	}
}
