package cn.mxz.friend;

import cn.mxz.FighterExpPrize;
import cn.mxz.battle.AbstractBattle;
import cn.mxz.battle.Camp;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.enemy.EmptyExpPrize;
import cn.mxz.fighter.Fighter;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.user.team.Formation;
import cn.mxz.user.team.god.Hero;

public class FriendBattleImpl extends AbstractBattle implements FriendBattle {

	public FriendBattleImpl(PlayerCamp s, String userId) {
		super(s, getCamp(userId));
	}

	private static Camp<? extends Fighter> getCamp(String userId) {
		City city = CityFactory.getCity(userId);
		Formation formation = city.getFormation();
		PlayerCamp selected = formation.getSelected();
		for (Hero f : selected.getFighters()) {
			f.addHp(f.getHpMax() - f.getHpNow());
		}
		return selected;
	}

	@Override
	protected FighterExpPrize getMapTemplet() {
		return new EmptyExpPrize();
	}

}
