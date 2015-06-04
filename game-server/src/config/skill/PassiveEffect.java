package config.skill;


/**
 * 被动技能 公式
 * @author DXF
 *
 */
public enum PassiveEffect {
	
	/**
	 * 对物理攻击减伤 
	 */
	phyReduceInjuries{
		@Override
		public float run( float damage, byte attackType, float[] arguments ) {
			
			// 表示不是加血  并且是物理攻击
			if( damage > 0 && attackType == 0 ){
				damage = (int) (arguments[0] == 0 ? damage - arguments[1] : damage * (1 - arguments[1]));
				damage = damage <= 0 ? 1 : damage;
			}
			
			return damage ;
		}
	},
	
	/**
	 * 对魔法攻击减伤 
	 */
	magicReduceInjuries{
		@Override
		public float run( float damage, byte attackType, float[] arguments ) {
			
			// 表示不是加血  并且是物理攻击
			if( damage > 0 && attackType == 1 ){
				damage = (int) (arguments[0] == 0 ? damage - arguments[1] : damage *  (1 - arguments[1]));
				damage = damage <= 0 ? 1 : damage;
			}
			
			return damage ;
		}
	}
	;

	/**
	 * 被动技能公式  
	 * @param damage (原伤害)
	 * @param attackType （攻击类型）
	 * @param arguments （参数）
	 * @return 
	 */
	public abstract float run( float damage, byte attackType, float[] arguments );
}
