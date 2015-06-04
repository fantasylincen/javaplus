package cn.mxz.util.needs;

import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.user.Player;
import cn.mxz.util.checker.PlayerChecker;
import define.D;

/**
 *
 * 需要金币数 * 角色等级
 *
 * @author 林岑
 *
 */
class CashBallNeed implements INeeds {

	private Integer	need;

	CashBallNeed(Integer count) {
		this.need = count;
	}

	@Override
	public void checkEnouph(Player player) {

		final PlayerChecker c = new PlayerChecker(player);

		final int need = getNeed(player);

		c.checkPlayerProperty(PlayerProperty.CASH, need);
	}

	@Override
	public int getNeed(Player p) {
		return need * p.getLevel();
	}

	@Override
	public void deduct(Player player) {

		final int need = getNeed(player);

		player.reduce(PlayerProperty.CASH, need);
	}

	@Override
	public void discount(float discount) {

		need = (int) (need * discount);
	}

	@Override
	public int getDeductTimesMax(City city) {

		if (need == 0) {

			return 0;
		}
		return city.getPlayer().get(PlayerProperty.CASH) / need;
	}

	@Override
	public int getHaveNow(Player player) {

		return player.get(PlayerProperty.CASH);
	}

	@Override
	public int getId() {
		return D.ID_CASH_BALL;
	}

	@Override
	public int getCount() {
		return need;
	}

	@Override
	public void checkEnouph(City user) {
		checkEnouph(user.getPlayer());
	}

	@Override
	public void deduct(City user) {
		deduct(user.getPlayer());

	}

	@Override
	public int getHaveNow(City user) {
		return getHaveNow(user.getPlayer());
	}

	@Override
	public int getNeed(City user) {
		return getNeed(user.getPlayer());
	}
}
