package cn.mxz.equipment;

import cn.mxz.FighterExpPrize;
import cn.mxz.battle.AbstractBattle;
import cn.mxz.battle.Battle;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.enemy.EmptyExpPrize;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.pvp.SuperCamp;
import cn.mxz.user.team.Formation;

/**
 * 夺宝战场
 *
 * @author 林岑
 * @since 2013年9月9日 14:17:02
 *
 */
class SnatchBattle extends AbstractBattle implements Battle {

	SnatchBattle(City city, FightingUser u, boolean isRobot) {

		super(getUserCamp(city.getId()), u.getCamp());
	}


	private static PlayerCamp getUserCamp(String userId2) {
		City city = CityFactory.getCity(userId2);
		Formation formation = city.getFormation();
		PlayerCamp selected = formation.getSelected();
		return new SuperCamp(selected, 1);
	}

	@Override
	protected FighterExpPrize getMapTemplet() {
		return new EmptyExpPrize();
	}
}
