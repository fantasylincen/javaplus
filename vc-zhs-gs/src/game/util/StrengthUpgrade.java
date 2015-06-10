package game.util;

/**
 * 升级 体力回复公式
 * @author DXF
 *
 */
public class StrengthUpgrade {

	
//	1-10： 升级回复3点体力
//	11-20：升级回复7点体力
//	21-25：升级回复12点体力
//  26-30：升级回复22点体力
//	31-35：升级回复33点体力
//	36-40：升级回复44点体力
//	41-45：升级回复55点体力
//	46-50：升级回复63点体力
//	51-55：升级回复70点体力
//	56-60：升级回复78点体力
//	61-70：升级回复85点体力
//	71-100：升级回复93点体力
//	101-120：升级回满
	private static final xxoo[] xo = {
		
		new xxoo( 1, 	10, 	3 	),
		new xxoo( 11, 	20, 	7 	),
		new xxoo( 21, 	25, 	12 	),
		new xxoo( 26, 	30, 	22 	),
		new xxoo( 31, 	35, 	33 	),
		new xxoo( 36, 	40, 	44 	),
		new xxoo( 41, 	45, 	55 	),
		new xxoo( 46, 	50, 	63 	),
		new xxoo( 51, 	55, 	70	),
		new xxoo( 56, 	65, 	78	),
		new xxoo( 61, 	70, 	85	),
		new xxoo( 71, 	100, 	93	),
		new xxoo( 101, 	120, 	200	)
	};
	
	
	
	/**
	 * 每次升级回复体力算法
	 * @param isUpgrade
	 * @param level
	 * @param strengthMax
	 * @return
	 */
	public static int run( short isUpgrade, short level, short strengthMax ) {
		
		int size 		= xo.length;
		for( int i = 0; i < size; i++ ){
			
			xxoo x		= xo[i];
			if( level <= x.maxLv )
				return x.value;
		}
		
		return 0;
	}

}

class xxoo{
	public int minLv;
	
	public int maxLv;
	
	public int value;
	
	public xxoo( int minlv, int maxlv, int value ){
		this.maxLv = maxlv;
		this.minLv = minlv;
		this.value = value;
	}
}

