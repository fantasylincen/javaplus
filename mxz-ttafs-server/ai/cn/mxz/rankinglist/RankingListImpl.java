package cn.mxz.rankinglist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.user.Player;

/**
 * 排行榜
 *
 * @author 林岑
 *
 */
public class RankingListImpl implements RankingList {

	private class IndexComparator implements PlayerComparator {

		private PlayerProperty	property;

		private IndexComparator(PlayerProperty property) {
			this.property = property;
		}

		@Override
		public int compare(City o1, City o2) {

			Player p1 = o1.getPlayer();

			Player p2 = o2.getPlayer();

			return p2.get(property) - p1.get(property);
		}

	}

	private static RankingListImpl	instance;

	private RankingListImpl() {
	}

	public static final RankingListImpl getInstance() {

		if (instance == null) {

			instance = new RankingListImpl();
		}

		return instance;
	}

	@Override
	public int getRank(City city) {

		return getLevelRank(city.getId());
	}

	@Override
	public int getRefreshSec() {
		return (int) (System.currentTimeMillis() % 10000);
	}

	@Override
	public int getLevelRank(String id) {

		return getRank(id, new PlayerComparator() {

			@Override
			public int compare(City o1, City o2) {

				return o2.getLevel() - o1.getLevel();
			}
		});
	}

	/**
	 * 获得玩家某个属性的排名
	 *
	 * @param uname
	 * @param property
	 *            来自#PlayerProperty
	 * @return
	 */
	private int getRank(String uname, PlayerComparator p) {

		Collection<City> values = WorldFactory.getWorld().getNearests().values();

		List<City> list = new ArrayList<City>(values);

		Collections.sort(list, p);

		list = cn.javaplus.util.Util.Collection.sub(list, 100);

		City city = getCity(uname);

		if (city == null || !list.contains(city)) {

			return Integer.MAX_VALUE;
		}

		return list.indexOf(city) + 1;
	}

	/**
	 * 获得玩家某个属性的排名
	 *
	 * @param uname
	 * @param property
	 *            来自#PlayerProperty
	 * @return
	 */

	public int getRank(String uname, PlayerProperty property) {

		return getRank(uname, new IndexComparator(property));
	}

	private City getCity(String uname) {

		return WorldFactory.getWorld().get(uname);
	}
}
