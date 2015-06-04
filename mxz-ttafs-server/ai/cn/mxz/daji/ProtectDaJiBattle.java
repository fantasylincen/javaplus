package cn.mxz.daji;

import cn.mxz.CustodianMapTemplet;
import cn.mxz.FighterExpPrize;
import cn.mxz.battle.AbstractBattle;
import cn.mxz.battle.Battle;
import cn.mxz.city.City;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.pvp.SuperCamp;

class ProtectDaJiBattle extends AbstractBattle implements Battle {


	ProtectDaJiBattle(PlayerCamp selected, CustodianMapTemplet temp, int monsterCount, City city) {

		super(new SuperCamp(selected, 1), new CustodianMapCamp(temp,monsterCount, city));
	}

	@Override
	protected FighterExpPrize getMapTemplet() {
		return new EmptyFighterExpPrize();
	}

}
