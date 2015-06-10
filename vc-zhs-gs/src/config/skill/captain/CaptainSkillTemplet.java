package config.skill.captain;

import game.fighter.FighterBase;
import game.log.Logs;

import org.jdom2.Element;

import config.fighter.Professional;

/**
 * 队长技能模板
 * @author DXF
 *
 */
public class CaptainSkillTemplet {

	private final int 				id;
	
	// 对应职业
	private final Professional		profession;
	
	// 特效
	private final CaptainEffect 	effect;
	
	public CaptainSkillTemplet( Element element ){
		
		id 					= Integer.parseInt( element.getChildText( "id" ) );
		profession 			= Professional.fromNumber( Integer.parseInt( element.getChildText( "profession" ) ) );
		effect				= analysis( element.getChildText( "effect" ) );
	}
	
	private CaptainEffect analysis( String content ) {
		
		if( content.isEmpty() )
			return null;
		
		String[] list = content.split(",");
		if( list.length != 2 ){
			Logs.error( "读取队长技能出错 特效长度不等于2!" );
			return null;
		}
		
		EffectType type = EffectType.valueOf( list[0] );
		if( type == null ){
			Logs.error( "读取队长技能出错 没找到 " + list[0] );
			return null;
		}
		float value		= Float.parseFloat( list[1] );
		
		return new CaptainEffect( type, value );
	}

	public int getId(){
		return this.id;
	}
	
	public Professional getProfession(){
		return this.profession;
	}
	
	public CaptainEffect getEffect(){
		return this.effect;
	}
	
	/**
	 * 执行效果
	 * @param fighterBase
	 */
	public void run( FighterBase fighter ) {
		
		if( effect == null )
			return;
		
		// 这里是对应职业才加  如果是全职业 也可以啊
		if( profession != Professional.ALL && profession != fighter.getProfessional() )
			return;
		
		effect.run( fighter );
	}
	
	/**
	 * 执行结果
	 * @param fighter
	 * @param type
	 * @return
	 */
	public float run( FighterBase fighter, EffectType type ) {
		
		if( effect == null )
			return 0;
		
		// 这里是对应职业才加  如果是全职业 也可以啊
		if( profession != Professional.ALL && profession != fighter.getProfessional() )
			return 0;
		
		return effect.run( type );
	}
	
	public String toString(){
		return "[ID：" + id +
				" 职业：" + profession.toNumber() + 
				"]";
	}

}
