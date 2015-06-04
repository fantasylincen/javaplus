package cn.mxz.mission.old;

import message.S;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.user.Player;
import cn.mxz.util.needs.INeeds;
import define.D;

public class GoldNeed implements INeeds {

	private int	count;

	public GoldNeed(int count) {
		this.count = count;
	}

	@Override
	public void checkEnouph(Player player) {
		int g = player.getGold();
		if (g < count) {
			throw new OperationFaildException(S.S10131);
		}
	}

	@Override
	public void deduct(Player player) {
		player.reduceGold(count);
	}

	@Override
	public void discount(float discount) {
		count *= discount;
	}

	@Override
	public int getDeductTimesMax(City city) {
		return city.getPlayer().getGold() / count;
	}

	@Override
	public int getHaveNow(Player player) {
		return player.getGold();
	}

	@Override
	public int getNeed(Player player) {
		return count;
	}

	@Override
	public int getId() {
		return D.ID_GOLD;
	}

	@Override
	public int getCount() {
		return count;
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
