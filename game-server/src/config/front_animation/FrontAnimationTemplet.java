package config.front_animation;

import game.fighter.FighterBase;
import game.fighter.Hero;
import game.fighter.NpcFighter;
import game.growup.Colour;
import game.growup.Quality;
import game.skill.CaptainSkill;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import config.fighter.HeroTemplet;
import config.fighter.HeroTempletCfg;
import config.fighter.NpcFighterTemplet;
import config.fighter.NpcFighterTempletCfg;
import config.skill.captain.CaptainSkillTempletCfg;

public class FrontAnimationTemplet {

	private List<Hero>  			data 	= new ArrayList<Hero>();
	
	private List<FighterBase>  		enemy 	= new ArrayList<FighterBase>();
	
	public FrontAnimationTemplet( Element element ) {
		
		
		for( int i = 0; i < 6; i++ ){
			
			String content 	= element.getChildText( "hero_" + i );
			
			if( content.isEmpty() )
				continue;
			
			String[] list 	= content.split(",");
			
			Hero h			= null;
			
			try {
				int id			= Integer.parseInt( list[0] );
				short level		= Short.parseShort( list[1] );
				Colour colour	= Colour.fromNumber( Integer.parseInt( list[2] ) );
				byte enLv		= Byte.parseByte( list[3] );
				int cSkill		= Integer.parseInt( list[4] );
				byte pos		= Byte.parseByte( list[5] );
				
				HeroTemplet ht	= HeroTempletCfg.getById(id);
				Quality quality	= new Quality( colour, ht.qualityMax, enLv );
				h				= new Hero( ht, -1, level, quality );
				h.setPosition( pos );
				if( i == 0 )
					h.setIsCaptain( true );
				if( cSkill > 0 )
					h.setCaptainSkill( new CaptainSkill( CaptainSkillTempletCfg.getById(cSkill) ) );
			} catch (Exception e) {
				continue;
			}
			
			
			data.add( h );
		}
		
		
		String content 	= element.getChildText( "enemy" );
		String[] list 	= content.split("\\|");
		for( int i = 0; i < list.length; i++ ){
			
			String[] xx	= list[i].split(",");
		
			int nid		= Integer.parseInt( xx[0] );
			byte pos	= Byte.parseByte( xx[1] );
			NpcFighterTemplet npcfighter 	= NpcFighterTempletCfg.getNpcById( nid );
			NpcFighter f 					= new NpcFighter( npcfighter );
			f.setPosition( pos );
			enemy.add( f );
		}
		
	}

	public List<Hero> getData() {
		return data;
	}

	public List<FighterBase> getEnemy() {
		return enemy;
	}

}
