package cn.mxz.battle;

import cn.mxz.city.City;
import cn.mxz.mission.old.PrizeImpl;
import cn.mxz.mission.old.PropPrize;
import cn.mxz.user.Player;

public class PropPrizeImpl implements PropPrize {

	private int	id;
	private int	count;

	public PropPrizeImpl(int id, int count) {
		this.id = id;
		this.count = count;
	}

	@Override
	public void award(Player player) {

		new PrizeImpl(id, count).award(player);
	}

	public void award(City city) {
		award(city.getPlayer());
	}
	
	@Override
	public int getCount() {
		return count;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return id + ":" + count;
	}
}
