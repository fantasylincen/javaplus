package config.tefineith;


import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import util.RandomUtil;

import config.fighter.NpcFighterTemplet;
import config.fighter.NpcFighterTempletCfg;


public class RefineithFireTemplet {

	public final int		 				m_nId;

	// 普通
	public final List<List<Integer>>   		clists;
	
	// 精英 
	public final List<List<Integer>>   		hlists;
	
	public RefineithFireTemplet( Element element ) {
		
		m_nId			= Integer.parseInt( element.getChildText( "id" ) );
		clists			= maping( element.getChildText( "content" ) );
		hlists			= maping( element.getChildText( "elite" ) );
	}

	private List<List<Integer>> maping( String childText ) {
		
		if( childText.isEmpty() )
			throw new RuntimeException( "解析试炼表格出错 没有英雄如何是好 at=" + m_nId );
			
		List<List<Integer>> list 	= new ArrayList<List<Integer>>();
		
		String[] content 			= childText.split( "\\|" );
	
		try {
			for( String x : content ){
				List<Integer> xx 		= new ArrayList<Integer>();
				String[] xxx			= x.split( "," );
				for( String s : xxx )
					xx.add( Integer.parseInt( s ) );
				list.add( xx );
			}
		} catch (Exception e) {
			throw new RuntimeException( "解析试炼表格出错 没有英雄如何是好 at=" + m_nId );	
		}
		
		return list;
	}

	public NpcFighterTemplet get( int type, int boss ) {
		List<Integer> list	= type == 0 ? clists.get(boss) : hlists.get(boss);
		int rand 			= RandomUtil.getRandomInt( 0, list.size() - 1 );
		int id				= list.get(rand);
		return NpcFighterTempletCfg.getNpcById( id );
	}

}
