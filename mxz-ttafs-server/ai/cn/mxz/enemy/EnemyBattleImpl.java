package cn.mxz.enemy;

import cn.mxz.FighterExpPrize;
import cn.mxz.battle.AbstractBattle;
import cn.mxz.battle.Camp;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.fighter.Fighter;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.pvp.SuperCamp;
import cn.mxz.user.team.Formation;

public class EnemyBattleImpl extends AbstractBattle implements EnemyBattle {

	protected EnemyBattleImpl(PlayerCamp under, String userId) {
		super(new SuperCamp(under, 1), getCamp(userId));

	}

	private static Camp<? extends Fighter> getCamp(String userId) {
		City city = CityFactory.getCity(userId);
		Formation formation = city.getFormation();
		PlayerCamp selected = formation.getSelected();
		return new SuperCamp(selected, 1);
	}

	@Override
	protected FighterExpPrize getMapTemplet() {
		return new EmptyExpPrize();
	}

}
