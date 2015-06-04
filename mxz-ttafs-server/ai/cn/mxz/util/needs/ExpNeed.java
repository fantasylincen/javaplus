package cn.mxz.util.needs;

import message.S;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.user.Player;
import define.D;

class ExpNeed implements INeeds {

	private Integer	count;

	ExpNeed(Integer count) {
		this.count = count;
	}

	@Override
	public void checkEnouph(Player player) {

		if (player.getExp().getNumerator() < count) {

			throw new OperationFaildException(S.S10049);
		}
	}

	@Override
	public void deduct(Player player) {
		// DONOTHING
	}

	@Override
	public void discount(float discount) {

		count = (int) (count * discount);
	}

	@Override
	public int getDeductTimesMax(City city) {

		if (count == 0) {

			return 0;
		}
		return city.getPlayer().getExp().getNumerator() / count;
	}

	@Override
	public int getHaveNow(Player player) {

		return player.getExp().getNumerator();
	}

	@Override
	public int getNeed(Player player) {

		return count;
	}

	@Override
	public int getId() {
		return D.ID_EXP;
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
