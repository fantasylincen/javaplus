package cn.mxz.bossbattle.battle;

import cn.mxz.FighterExpPrize;
import cn.mxz.battle.AbstractBattle;
import cn.mxz.enemy.EmptyExpPrize;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.pvp.SuperCamp;
import define.D;

public class BossBattle extends AbstractBattle {

	public BossBattle(PlayerCamp under, BossFighter boss, float addition) {
		super(new SuperCamp(under, addition + 1), new BossCamp(boss));
	}

	protected boolean isUpHp() {
		return false;
	}
	
	@Override
	protected FighterExpPrize getMapTemplet() {
		return new EmptyExpPrize();
	}


}
