package cn.mxz.city;

import java.util.List;

import cn.javaplus.user.IUserCollection;
import cn.javaplus.user.UserCollection;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.user.init.ReadyUser;
import db.GameDB;
import db.dao.impl.DaoFactory;
import db.dao.impl.UserBaseDao;
import db.dao.impl.UserBaseDao1;
import db.domain.UserBase;
import db.domain.UserBaseImpl;

/**
 * 城池工厂
 *
 * @author 林岑
 *
 */
public class CityFactory {

	private static boolean	isLoadingCitys;

	/**
	 * 构建一个全新的城池
	 *
	 * @return
	 */

	public static City createNewCity(ReadyUser ready) {

		UserBase base = new UserBaseImpl();

		base.setUname(ready.getRoleId());
		base.setNick(ready.getNick());

		DaoFactory.getUserBaseDao().save(base);

		City city = new City(base);

		World world = WorldFactory.getWorld();

		world.add(city);

		return city;

	}

	/**
	 * 创建所有玩家的城池
	 *
	 * @return
	 */
	public static IUserCollection<City> createCitys() {

		if (isLoadingCitys) {
			throw new NotInitOverException();
		}

		isLoadingCitys = true;

		final IUserCollection<City> allCity = new UserCollection<City>();

		UserBaseDao DAO = new UserBaseDao1(GameDB.getInstance());

		final List<UserBase> all = DAO.getAll();

		for (UserBase u : all) {

			City city = new City(u);

			allCity.add(city);
		}
		isLoadingCitys = false;

		return allCity;
	}

	public static City getCity(String string) {

		if (string == null) {

			return null;
		}

		World world = WorldFactory.getWorld();

		City city = world.get(string);

		return city;

	}
}
