package config.robot;

import java.util.ArrayList;
import java.util.List;

import game.fighter.Hero;
import game.growup.Colour;
import game.growup.Quality;
import game.pvp.MatchingType;
import game.skill.CaptainSkill;

import org.jdom2.Element;

import config.fighter.HeroTemplet;
import config.fighter.HeroTempletCfg;
import config.skill.captain.CaptainSkillTemplet;
import config.skill.captain.CaptainSkillTempletCfg;

public class MateRobotTemplet {

	public final MatchingType 	type;
	
	public final String 		name;
	
	public final List<Hero>  	list = new ArrayList<Hero>();

	public final short 			level;
	
	public MateRobotTemplet(Element element) {
		
		type				= MatchingType.fromNumber( Integer.parseInt( element.getChildText( "type" ) ) );
		name				= element.getChildText( "name" );
		level				= Short.parseShort( element.getChildText( "level" ) );
		
		for( int i = 0; i < 5; i++ ){
			Hero h			= maping( element.getChildText( "hero_" + i ) );
			if( h == null ) continue;
			if( i == 0 )
				h.setIsCaptain( true );
			list.add(h);
		}
		
	}

	private Hero maping( String childText ) {
		
		if( childText.isEmpty() )
			return null;
		
		String[] content 	= childText.split(",");
		Hero h				= null;
		
		try {
			//表格ID,等级,品质颜色,品质等级,位置,队长技能ID
			int nid				= Integer.parseInt( content[0] );
			HeroTemplet ht		= HeroTempletCfg.getById(nid);
			if( ht == null ) return null;
			short level			= Short.parseShort( content[1] );
			Colour c			= Colour.fromNumber( Integer.parseInt(content[2] ));
			if( c.toNumber() != type.toNumber() )
				throw new RuntimeException( childText + " 英雄的 品质不对应 at=" + name );
			
			byte ql				= Byte.parseByte( content[3] );
			Quality q			= new Quality( c, ht.qualityMax, ql );
			byte pos			= Byte.parseByte( content[4] );
			if( pos > 5 || pos < 0 )
				throw new RuntimeException( childText + " 英雄位置错误 pos=" + pos );
			
			int cSkillId		= Integer.parseInt( content[5] );
			
			h					= new Hero( ht, -1, level, q );
			h.setPosition( pos );
			
			CaptainSkillTemplet cs = CaptainSkillTempletCfg.getById( cSkillId );
			if( cs != null ){
				h.setCaptainSkill( new CaptainSkill( cs ) );
			}
		} catch (Exception e) {
			return null;
		}
		
		
		return h;
	}

	/**
	 * 拷贝一份
	 * @return
	 */
	public List<Hero> getList() {
		
		List<Hero> x = new ArrayList<Hero>();
		
		for( Hero h : list )
			x.add( new Hero( h ) );
		
		return x;
	}


}
