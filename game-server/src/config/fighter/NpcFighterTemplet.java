package config.fighter;

import game.award.AwardType;
import game.award.AwardInfo;
import game.growup.Colour;
import game.growup.Quality;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

/**
 * 战士模版
 * @author Administrator
 * 2013-5-24 上午10:49:26
 */
public class NpcFighterTemplet extends FighterTempletBase {
	
	/** 怪物掉落 */
	public List<AwardInfo>			content;
	
	/** 怪物类型  （0，普通怪  1，BOSS怪） */
	public byte						type;
	
	/** 基础品质 */
	public final Quality			qualityBase;
	
	/** 试炼掉落-普通 */
	public List<AwardInfo>			ccontent;
	
	/** 试炼掉落-英雄 */
	public List<AwardInfo>			hcontent;
	
	public NpcFighterTemplet( Element element ) {
		super(element);
		
		content 		= parsing( element.getChildText( "dropItem" ) );
		ccontent 		= parsing( element.getChildText( "cdropItem" ) );
		hcontent 		= parsing( element.getChildText( "hdropItem" ) );
		type			= Byte.parseByte( element.getChildText( "type" ) );
		Colour colour	= Colour.fromNumber( Byte.parseByte( element.getChildText( "color" ) ) );
		byte intensify	= Byte.parseByte( element.getChildText( "intensify" ) );
		qualityBase		= new Quality( colour, Colour.PURPLE, intensify );
	}	

	private List<AwardInfo> parsing( String content )
	{
		List<AwardInfo> awardList 	= new ArrayList<AwardInfo>();
		
		if( content.isEmpty() ) 
			return awardList;
		
		String[] awardArr			= content.split("\\|");
		
		try {
			for( String s : awardArr ){
				String[] award 						= s.split(",");
				
				AwardType 			atype 			= AwardType.valueOf( award[0] );
				int					propId			= Integer.parseInt( award[1] );
				short				number			= Short.parseShort( award[2] );
				float[]				rand			= { Float.parseFloat( award[3] ),Float.parseFloat( award[4] )};
				
				AwardInfo 			awardInfo		= new AwardInfo(atype, propId, number, rand);
				
				int size 							= award.length - 5;
				if( size > 0 ){
					int[] 			arguments		= new int[size];
					for( int i = 0; i < size; i++ ){
						arguments[i]				= Integer.parseInt( award[5+i] );
					}
					
					awardInfo.setArguments( arguments );
				}
				
				awardList.add( awardInfo );
			}
		} catch (Exception e) {
			return awardList;
		}
		
		
		return awardList;
	}

	/**
	 * 设置试炼掉落
	 * @param odds
	 */
	public List<AwardInfo> getRAward( float odds, int type ) {
		
		List<AwardInfo> list 	= cloneList( type );
		
		for( AwardInfo a : list ){
			float[] rand = { odds, 0 };
			a.setRand( rand );
		}
		
		return list;
	}
	
	private List<AwardInfo> cloneList( int type ){
		
		List<AwardInfo> list 	= new ArrayList<AwardInfo>();
		List<AwardInfo> x		= type == 0 ? ccontent : hcontent;
		
		for( AwardInfo a : x ){
			AwardInfo xx		= new AwardInfo( a );
			list.add( xx );
		}
		return list;
	}
}
