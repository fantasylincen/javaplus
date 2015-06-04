package game.invitingfriends;

/**
 * 邀请好友 的奖励  工具
 * @author DXF
 */
public class InvitingRecortToAward {

	public static int[][] value = { 
		{ 50, 	100 }, 
		{ 60, 	200 },
		{ 70, 	300 },
		{ 80, 	400 },
		{ 90, 	500 },
		{ 100, 	600 },
		{ 110, 	700 },
		{ 120, 	1000 },
	};
	
	public static int get( short level, short isUpgrade ) {
		
		// 先取得 升级前 的等级
		short tolevel	= (short) (level - isUpgrade);
		// 算出整  然后再加10 因为当前等级的 一定是加过水晶了
		tolevel			= (short) ((tolevel / 10) * 10 + 10);
		// 算出整
		level 			= (short) ((level / 10) * 10);
		
		int gold 		= 0;
		
		while( tolevel <= level ){
			
			for( int i = 0; i < value.length; i++ ){
				if( value[i][0] == tolevel ) {
					gold += value[i][1];
					break;
				}
			}
			
			tolevel 	+= 10;
		}
		
		return gold;
	}

	
	
	
	
	
	
}
