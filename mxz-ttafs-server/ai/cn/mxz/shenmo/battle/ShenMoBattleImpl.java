package cn.mxz.shenmo.battle;

import cn.mxz.FighterExpPrize;
import cn.mxz.battle.AbstractBattle;
import cn.mxz.battle.Camp;
import cn.mxz.enemy.EmptyExpPrize;
import cn.mxz.fighter.Fighter;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.pvp.SuperCamp;
import cn.mxz.shenmo.ShenmoFighter;
import define.D;

public class ShenMoBattleImpl extends AbstractBattle implements ShenmoBattle {

	public ShenMoBattleImpl(PlayerCamp selected, ShenmoFighter fighter, boolean isFullFighting) {
		super(build(selected, isFullFighting), build(fighter));
	}

	private static Camp<? extends Fighter> build(ShenmoFighter fighter) {
		return new ShenMoCamp(fighter);
	}
	protected boolean isUpHp() {
		return false;
	}
	private static PlayerCamp build(PlayerCamp selected, boolean isFullFighting) {
		if(isFullFighting) {
			return new SuperCamp(selected, D.BOSS_FULL_FIGHTING_ADDITION);	//超级阵容, 加成后的
		} else {
			return new SuperCamp(selected, 1);
		}
	}

	@Override
	protected FighterExpPrize getMapTemplet() {
		return new EmptyExpPrize();
	}

	@Override
	protected int getMaxRound() {
		return D.MAX_SHEN_MO_BATTLE_ROUND;
	}
}
