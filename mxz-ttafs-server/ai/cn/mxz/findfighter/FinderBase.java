package cn.mxz.findfighter;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;

import com.google.common.collect.Lists;

abstract class FinderBase implements FinderBean{

	private int	probability;

	protected int	type;

	protected City	city;




	FinderBase(City city, String string) {

		this.city = city;

		String[] split = string.split(":");

		type = new Integer(split[1]);

		probability = new Integer(split[2]);
	}

	@Override
	public int getProbability() {

		return probability;
	}

	@Override
	public final List<Hero> find(int times) {

		List<Hero> list = Lists.newArrayList();

//		if (isSingle()) {
//
//			times = new Integer(temp.getTimes().trim());
//		}

		for (int i = 0; i < times; i++) {

			list.add(findOneTime());
		}

		return list;

	}

	protected abstract Hero findOneTime();

//	/**
//	 * 是否是单一次数的寻仙方式
//	 * @return
//	 */
//	private boolean isSingle() {
//
//		return !temp.getTimes().contains(",");
//	}
}