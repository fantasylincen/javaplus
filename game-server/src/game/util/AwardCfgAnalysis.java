package game.util;

import java.util.ArrayList;
import java.util.List;

import game.award.AwardInfo;
import game.award.AwardType;

/**
 * 系统奖励 配置表 解析
 * @author DXF
 *
 */
public class AwardCfgAnalysis {

	/**
	 * 获取系统奖励的
	 * @param content
	 * @return
	 */
	public static List<AwardInfo> getAward( String content ){
		
		List<AwardInfo> awardList 	= new ArrayList<AwardInfo>();
		
		if( content.isEmpty() ) 
			return awardList;
		
		String[] awardArr		= content.split("\\|");
		
		try {
			for( String s : awardArr ){
				String[] award 						= s.split(",");
				
				AwardType 			atype 			= AwardType.valueOf( award[0] );
				int					propId			= Integer.parseInt( award[1] );
				int					number			= Integer.parseInt( award[2] );
				
				AwardInfo 			awardInfo		= new AwardInfo( atype, propId, number );
				
				int size 							= award.length - 3;
				if( size > 0 ){
					int[] 			arguments		= new int[size];
					for( int i = 0; i < size; i++ ){
						arguments[i]				= Integer.parseInt( award[3+i] );
					}
					
					awardInfo.setArguments( arguments );
				}
				
				awardList.add( awardInfo );
			}
		} catch (Exception e) {
			awardList.clear();
		}
		
		return awardList;
	}
	
}
