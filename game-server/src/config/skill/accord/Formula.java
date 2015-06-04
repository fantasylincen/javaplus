package config.skill.accord;

import define.DefaultCfg;
import game.fighter.FighterBase;

public enum Formula {
	
	/**
	 * 直接按   最大HP的比例<br>
	 * 参数意义:
	 * 		arguments[0]==1则按照攻击者的hpMax进行计算，否则按照防守者的hpMax进行计算<br>
	 * 		arguments[1] 计算比例
	 */
	MaxHpDirectFormula{
		@Override
		public float run( FighterBase attacker, FighterBase defender, byte level, float arguments ) {
			float la	= arguments;
			if( level - 1 >= 0 && level - 1 < DefaultCfg.LEVE_LADDITION.length )
				la		*= DefaultCfg.LEVE_LADDITION[ level - 1 ];
			
			return defender.getHpMax() * la;
		}		
	},
	
	/**
	 * 直接按  攻击力的比例<br>
	 * 参数意义:
	 * 		arguments[0]==1则按照攻击者的攻击力进行计算，否则按照防守者的攻击力进行计算<br>
	 * 		arguments[1] 计算比例
	 */
	AttackDirectFormula{
		@Override
		public float run( FighterBase attacker, FighterBase defender, byte level, float arguments ) {
			float la	= arguments;
			if( level - 1 >= 0 && level - 1 < DefaultCfg.LEVE_LADDITION.length )
				la		*= DefaultCfg.LEVE_LADDITION[ level - 1 ];
			return attacker.getAttack() * la ;
		}		
	},
	
	/**
	 * 输入多少返回多少 (真实伤害)<br> 
	 * 参数意义：arguments[0]为输入的数值
	 */
	DirectOutputFormula{
		@Override
		public float run(FighterBase attacker, FighterBase defender, byte level,float arguments) {
			return arguments;
		}
	};
	
	
	public abstract float run( FighterBase attacker, FighterBase defender, byte level, float arguments );
	
	
}



