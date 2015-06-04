package cn.mxz.mission.old;

import cn.mxz.city.City;
import cn.mxz.user.Player;
import cn.mxz.util.needs.INeeds;

/**
 *
 * 尽量多的扣除玩家身上的物品, 如果不够的, 扣除所有
 *
 * @author 林岑
 *
 */
class NeedAsMuchImpl implements INeeds {

	private INeeds	needs;

	NeedAsMuchImpl(INeeds needs) {
		this.needs = needs;
	}

	@Override
	public void checkEnouph(Player player) {
		// 永远足够
	}

	@Override
	public void deduct(Player player) {

		needs.deduct(player);
	}

	@Override
	public void discount(float discount) {

		needs.discount(discount);
	}

	@Override
	public int getDeductTimesMax(City city) {
		return Integer.MAX_VALUE;
	}

	@Override
	public int getHaveNow(Player player) {
		return needs.getHaveNow(player);
	}

	@Override
	public int getNeed(Player player) {

		int haveNow = getHaveNow(player);

		int need = needs.getNeed(player);

		return haveNow < need ? haveNow : need;
	}

	@Override
	public int getId() {
		return needs.getId();
	}

	@Override
	public int getCount() {
		return needs.getCount();
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
