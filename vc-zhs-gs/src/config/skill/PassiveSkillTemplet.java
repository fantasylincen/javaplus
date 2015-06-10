package config.skill;


import org.jdom2.Element;


/**
 * 被动技能 模板
 * @author DXF
 *
 */
public class PassiveSkillTemplet {
	
	private final int					id;
	private final String				name;
	private final String				desc;
	
	// 被动技能效果
	private final PassiveEffect			effect;
	
	private final float[]				arguments;
	
	public PassiveSkillTemplet( Element element ){
		
		id 					= Integer.parseInt( element.getChildText( "id" ) );
		name 				= element.getChildText( "name" );
		desc 				= element.getChildText( "desc" );
		
		String	content[]	= element.getChildText( "effect" ).split( "," );
		
		effect 			= PassiveEffect.valueOf( content[0] );
		
		arguments		= new float[ content.length - 1 ];
		
		for( int i = 0; i < content.length - 1; i++ ){
			arguments[i] = Float.parseFloat( content[ i + 1 ] );
		}
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDesc() {
		return desc;
	}
	public PassiveEffect getEffect(){
		return this.effect;
	}
	public float[] getArguments(){
		return this.arguments;
	}
	
	/**
	 * 执行 被动技能
	 * @param damage
	 * @param attackType
	 * @return
	 */
	public float run( float damage, byte attackType )
	{
		return effect != null ? effect.run( damage, attackType, arguments ) : damage;
	}

	@Override
	public String toString() {
		return "SkillEffect [name=" + name
				+ ", effect=" + effect
				+ "]";
	}
	
}
