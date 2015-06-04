package cn.mxz.util.needs;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.user.Player;

class NeedsCheckerImpl implements NeedsChecker {

	private List<INeeds> needs;

	NeedsCheckerImpl(List<INeeds> needs) {

		this.needs = needs;
	}

	@Override
	public void deduct(Player user) {

		for (INeeds n : needs) {

			n.deduct(user);
		}
	}

	@Override
	public void check(Player player) {

		for (INeeds n : needs) {

			n.checkEnouph(player);
		}
	}

	@Override
	public void discount(float discount) {

		for (INeeds n : needs) {

			n.discount(discount);
		}
	}

	@Override
	public int getDeductTimesMax(City city) {

		int count = 0;

		for (INeeds n : needs) {

			count += n.getDeductTimesMax(city);
		}

		return count;
	}

	@Override
	public List<INeeds> getNeeds() {
		return needs;
	}

	@Override
	public void deduct(City user) {
		deduct(user.getPlayer());

	}

	@Override
	public void check(City city) {
		check(city.getPlayer());
	}
}
