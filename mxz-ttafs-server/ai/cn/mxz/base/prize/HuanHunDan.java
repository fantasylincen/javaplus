package cn.mxz.base.prize;

import message.S;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.user.Player;
import cn.mxz.user.team.god.Hero;

/**
 * 还魂丹, 复活神将之用。
 * @author 林岑
 *
 */
public class HuanHunDan implements AwardAble {

	private int	fighterId;

	public HuanHunDan(int fighterId) {

		this.fighterId = fighterId;
	}

	@Override
	public void award(Player player) {

		World world = WorldFactory.getWorld();


		City city = world.get(player.getId());

		Hero hero = city.getTeam().get(fighterId);

		if(!hero.isDeath()) {
			throw new OperationFaildException(S.S10242);
		}

		int i = hero.getAttribute().getHp() - hero.getHpNow();
		hero.addHp(i);

		hero.commit();

		city.getMission().clearDieCount();
	}
	
	public void award(City city) {
		award(city.getPlayer());
	}
}
