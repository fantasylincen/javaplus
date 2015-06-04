package config.skill.captain;

import game.fighter.FighterBase;

/**
 * 队长技能特效
 * @author DXF
 */
public class CaptainEffect {
	
	// 效果类型
	private EffectType		type;
	
	// 数值
	private float			arguments;
	
	
	public CaptainEffect( EffectType type, float arguments ){
		this.type 		= type;
		this.arguments 	= arguments;
	}
	
	public EffectType getType(){
		return this.type;
	}
	
	public float getArguments(){
		return this.arguments;
	}

	public void run( FighterBase fighter ) {
		if( type == null )
			return;
		
		type.run( fighter, arguments );
	}

	public float run( EffectType type ) {
		
		if( this.type != type )
			return 0;
		
		return arguments;
	}
	
}
