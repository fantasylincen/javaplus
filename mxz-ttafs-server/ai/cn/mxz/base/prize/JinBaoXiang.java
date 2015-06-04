package cn.mxz.base.prize;

import java.util.List;

import cn.mxz.GoldBoxTemplet;
import cn.mxz.GoldBoxTempletConfig;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class JinBaoXiang {

	private City city;

	public JinBaoXiang(City city) {
		this.city = city;
	}

	public List<Prize> open() {
		UserCounter his = city.getUserCounterHistory();
		int count = his.get(CounterKey.JIN_BAO_XIANG_OPEN_TIMES) + 1;

		GoldBoxTemplet gb = find(count);
		BoxOpenManager bm = new BoxOpenManager(city);
		List<Prize> open = bm.open(new GoldBoxAdatpor(gb));
		his.add(CounterKey.JIN_BAO_XIANG_OPEN_TIMES, 1);
		if (bm.isFindWell()) {
			his.set(CounterKey.JIN_BAO_XIANG_OPEN_TIMES, 0);
		}
		return open;
	}

	private GoldBoxTemplet find(int count) {
		List<GoldBoxTemplet> all = GoldBoxTempletConfig.getAll();
		for (GoldBoxTemplet g : all) {
			if (contains(g, count)) {
				return g;
			}
		}
		return null;
	}

	private boolean contains(GoldBoxTemplet g, int count) {
		String number = g.getNumber();
		String[] split = number.split("\\-");
		int min = new Integer(split[0]);
		int max = new Integer(split[1]);
		return min <= count && count <= max;
	}

}
