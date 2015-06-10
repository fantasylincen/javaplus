package config.skill.captain;

import game.fighter.FighterBase;


/**
 * 队长技能 效果类型
 * @author DXF
 */
public enum EffectType {
	
	/** 物理攻击 */
	PHY_ATTACK {
		@Override
		public void run( FighterBase fighter, float arguments ) {
			if( fighter.getAttackType() == 0 ){
				fighter.setAttack( (int) (fighter.getAttack() * (1f + arguments / 100f)) );
			}
		}
	},
	
	/** 物理穿透 */
	PHY_PENETRATION {
		@Override
		public void run( FighterBase fighter, float arguments ) {
			
		}
	},
	
	/** 物理抗性 */
	PHY_RESISTANCE {
		@Override
		public void run(FighterBase fighter, float arguments) {
			
		}
	},
	
	/** 法术攻击 */
	MAGIC_ATTACK {
		@Override
		public void run(FighterBase fighter, float arguments) {
			if( fighter.getAttackType() == 1 ){
				fighter.setAttack( (int) (fighter.getAttack() * (1f + arguments / 100f)) );
			}
		}
	},
	
	/** 法术穿透 */
	MAGIC_PENETRATION {
		@Override
		public void run(FighterBase fighter, float arguments) {
			
		}
	},
	
	/** 法术抗性 */
	MAGIC_RESISTANCE {
		@Override
		public void run(FighterBase fighter, float arguments) {
			
		}
	},
	
	/** 生命 */
	HP {
		@Override
		public void run( FighterBase fighter, float arguments ) {
			fighter.setHpMax( (int) (fighter.getHpMax() * (1f + arguments / 100f)) );
			fighter.setHp( fighter.getHpMax() );
		}
	},
	
	/** 暴击 */
	CRIT {
		@Override
		public void run(FighterBase fighter, float arguments) {
			fighter.setCommonCrit( fighter.getCommonCrit() * (1f + arguments / 100f) );
			fighter.setSkillCrit( fighter.getSkillCrit() * (1f + arguments / 100f) );
		}
	},
	
	/** 命中 */
	HIT {
		@Override
		public void run( FighterBase fighter, float arguments ) {
		}
	},
	
	/** 韧性 */
	TOUGHNESS {
		@Override
		public void run( FighterBase fighter, float arguments ) {
			
		}
	},
	
	/** 闪避 */
	DODGE {
		@Override
		public void run( FighterBase fighter, float arguments ) {
			fighter.setDodge( fighter.getDodge() * (1f + arguments / 100f) );
		}
	},
	
	/** 治疗量 */
	CURATIVE_DOSE {
		@Override
		public void run( FighterBase fighter, float arguments ) {
			
		}
	}
	
	;
	
	
	public abstract void run( FighterBase fighter, float arguments );
}


