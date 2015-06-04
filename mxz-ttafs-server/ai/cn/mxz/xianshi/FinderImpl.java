package cn.mxz.xianshi;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.util.Util.Random;
import cn.mxz.GodTypeTemplet;
import cn.mxz.GodTypeTempletConfig;
import cn.mxz.city.City;

class FinderImpl implements Finder {

	private GodTypeTemplet temp;

	private City city;

	FinderImpl(City city, int type) {

		this.city = city;

		this.temp = GodTypeTempletConfig.get(type);
	}

	@Override
	public int find(boolean noJia) {

		List<FinderBean> ps = build(noJia);

		FinderBean bean = random(ps);

		return bean.findOneTime();
	}

	private List<FinderBean> build(boolean noJia) {

		List<FinderBean> list = new ArrayList<FinderBean>();

		String pty = temp.getProbability();

		String[] split = pty.split("\\|");

		for (String string : split) {
			
			list.add(new QFinder(city, string, temp, noJia));
		}

		return list;
	}

	private FinderBean random(List<FinderBean> ps) {

		int index = Random.getRandomIndex(buildPs(ps));

		return ps.get(index);
	}

	private int[] buildPs(List<FinderBean> ps) {

		int[] a = new int[ps.size()];

		for (int i = 0; i < a.length; i++) {

			a[i] = ps.get(i).getProbability();
		}

		return a;
	}
}
