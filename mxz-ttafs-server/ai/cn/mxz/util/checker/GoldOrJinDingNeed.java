package cn.mxz.util.checker;

import message.S;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.user.Player;
import cn.mxz.util.needs.INeeds;
import define.D;

public class GoldOrJinDingNeed implements INeeds {

	private int	count;

	public GoldOrJinDingNeed(int count) {
		this.count = count;
	}

	@Override
	public void checkEnouph(Player player) {
		int g = player.getGoldAndJinDing();
		if (g < count) {
			throw new OperationFaildException(S.S10131);
		}
	}

	@Override
	public void deduct(Player player) {
		player.reduceGoldOrJinDing(count);
	}

	@Override
	public void discount(float discount) {
		count *= discount;
	}

	@Override
	public int getDeductTimesMax(City city) {
		return city.getPlayer().getGoldAndJinDing() / count;
	}

	@Override
	public int getHaveNow(Player player) {
		return player.getGoldAndJinDing();
	}

	@Override
	public int getNeed(Player player) {
		return count;
	}

	@Override
	public int getId() {
		return D.ID_NEW_GOLD;
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
