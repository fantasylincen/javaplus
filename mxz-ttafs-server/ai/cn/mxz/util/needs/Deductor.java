package cn.mxz.util.needs;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.user.Player;

public interface Deductor {

	public abstract void deduct(Player user);
	public abstract void deduct(City user);

	List<INeeds> getNeeds();

}