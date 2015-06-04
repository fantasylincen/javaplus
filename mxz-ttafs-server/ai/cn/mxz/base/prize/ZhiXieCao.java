package cn.mxz.base.prize;

import message.S;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.fighter.HeroImpl;
import cn.mxz.user.Player;
import db.dao.impl.DaoFactory;

/**
 *
 * 止血草, 使用后全部恢复满血
 *
 * @author 林岑
 *
 */
class ZhiXieCao implements AwardAble {


	private int	fighterId;

	ZhiXieCao(int fighterId) {

		this.fighterId = fighterId;
	}

	@Override
	public void award(Player player) {

		World world = WorldFactory.getWorld();

		City city = world.get(player.getId());

		HeroImpl hero = (HeroImpl) city.getTeam().get(fighterId);

		if(hero.isDeath()) {

			throw new OperationFaildException(S.S10163);
		}

		int add = hero.getAttribute().getHp() - hero.getHpNow();

		if(add == 0) {
			throw new OperationFaildException(S.S10241);
		}

		hero.addHp(add);

		DaoFactory.getNewFighterDao().update(hero.getDto());
	}

	public void award(City city) {
		award(city.getPlayer());
	}
}
