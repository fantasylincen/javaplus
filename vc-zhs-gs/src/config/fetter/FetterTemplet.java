package config.fetter;


import game.fighter.FighterBase;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;


import lombok.Data;

public class FetterTemplet {
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public List<Integer> getHeroId() {
		return heroId;
	}

	public List<Effect> getEffects() {
		return effects;
	}

	private final int id;
	private final String name;
	private final String desc;
	private final List<Integer> heroId;
	private final List<Effect> effects;
	
	public FetterTemplet( Element element ){
		id 				= Integer.parseInt( element.getChildText( "id" ) );    
		name			= element.getChildText( "name" );
		desc 			= element.getChildText( "desc" );
		heroId			= analysis( element.getChildText( "heroId" ) );
		effects			= analysisEffect( element.getChildText( "effect" ) );

	}

	private List<Effect> analysisEffect( String childText ) {
		if( childText == null || childText.isEmpty() ) return null;
		
		List<Effect> ret = new ArrayList<Effect>();
		String[] content = childText.split( "\\|" );
		for( int i = 0; i < content.length; i++ ){
			Effect effect		= new Effect();
			String[] x 			= content[i].split(",");
			effect.fe			= FetterEffect.valueOf( x[0] );
			effect.arguments	= Float.parseFloat( x[1] );
			ret.add( effect );
		}
		
		return ret;
	}

	private List<Integer> analysis( String childText ) {
		if( childText == null || childText.isEmpty() ) return null;
		
		List<Integer> ret 	= new ArrayList<Integer>();
		String[] content 	= childText.split( "," );
		for( int i = 0; i < content.length; i++ )
			ret.add( Integer.parseInt( content[i] ) );
		return ret;
	}

	public boolean isActivate( List<FighterBase> attackers ) {
		
		for( int i : heroId ){
			
			boolean b = false;
			for( FighterBase f : attackers ){
				if( f.getId() == i ){
					b = true;
					break;
				}
			}
			if( !b ) return false;
		}
		
		return true;
	}

	public void run( FighterBase attacker ) {
		for( Effect e : effects )
			e.run( attacker );
	}
	
}

class Effect{
	FetterEffect fe;
	float arguments;
	public void run( FighterBase attacker ){
		fe.run( attacker, arguments );
	}
}
