package config.fetter;

import game.fighter.FighterBase;

/**
 * 羁绊 效果
 * @author deng
 *
 */
public enum FetterEffect {

	/** 攻击力相关 （ 物理攻击 魔法攻击 一起 ） */
	ATTACK {
		@Override
		public void run(FighterBase attacker, float arguments) {
			attacker.setAttack( (int) (attacker.getAttack() * arguments) );
		}
	},
	
	/** 血量相关 */
	HP {
		@Override
		public void run(FighterBase attacker, float arguments) {
			if( attacker.getHp() != 0 ){
				attacker.setHpMax( (int) (attacker.getHpMax() * arguments) );
				attacker.setHp( attacker.getHpMax() );
			}
		}
	};
	
	
	public abstract void run( FighterBase attacker, float arguments );
}
