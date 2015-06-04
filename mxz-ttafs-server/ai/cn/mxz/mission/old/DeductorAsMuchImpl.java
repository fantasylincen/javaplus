package cn.mxz.mission.old;

import java.util.List;

import cn.mxz.INeedsInExcel;
import cn.mxz.city.City;
import cn.mxz.user.Player;
import cn.mxz.util.needs.Deductor;
import cn.mxz.util.needs.INeeds;
import cn.mxz.util.needs.NeedsChecker;
import cn.mxz.util.needs.NeedsFactory;

/**
 * 尽量多的扣除玩家身上的物品, 如果不够的, 扣除所有
 *
 * @author 林岑
 *
 */
class DeductorAsMuchImpl implements Deductor {

	private INeedsInExcel	temp;

	DeductorAsMuchImpl(INeedsInExcel temp) {
		this.temp = temp;
	}

	@Override
	public void deduct(Player player) {

		NeedsChecker n = NeedsFactory.getNeedsCheckerImpl2(temp);

		List<INeeds> ns = n.getNeeds();

		for (INeeds i : ns) {

			int count = i.getHaveNow(player);

			if (i.getNeed(player) > count) {

				i = new NeedAsMuchImpl(i);
			}

			i.deduct(player);
		}
	}

	@Override
	public List<INeeds> getNeeds() {

		NeedsChecker n = NeedsFactory.getNeedsCheckerImpl2(temp);

		return n.getNeeds();
	}

	@Override
	public void deduct(City user) {
		deduct(user.getPlayer());
	}

}
