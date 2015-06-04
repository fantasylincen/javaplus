package cn.mxz.base.prize;

import java.util.List;

import cn.mxz.VioletGoldBoxTemplet;
import cn.mxz.VioletGoldBoxTempletConfig;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class ZiJinBaoXiang {

	private City city;

	public ZiJinBaoXiang(City city) {
		this.city = city;
	}

	public List<Prize> open() {
		UserCounter his = city.getUserCounterHistory();
		int count = his.get(CounterKey.ZI_JIN_BAO_XIANG_OPEN_TIMES) + 1;
		
		VioletGoldBoxTemplet gb = find(count);
		BoxOpenManager bm = new BoxOpenManager(city);
		List<Prize> open = bm.open(new BoxAdaptor(gb));
		
//		Debuger.debug("ZiJinBaoXiang.open() count = " + count  + "   " + open + "   type = " + bm.getKu());
		
		his.add(CounterKey.ZI_JIN_BAO_XIANG_OPEN_TIMES, 1);
		if(bm.isFindWell()) {
			his.set(CounterKey.ZI_JIN_BAO_XIANG_OPEN_TIMES, 0);
		}
		return open;
	}

	private VioletGoldBoxTemplet find(int count) {
		List<VioletGoldBoxTemplet> all = VioletGoldBoxTempletConfig.getAll();
		for (VioletGoldBoxTemplet g : all) {
			if(contains(g, count)) {
				return g;
			}
		}
		return null;
	}

	private boolean contains(VioletGoldBoxTemplet g, int count) {
		String number = g.getNumber();
		String[] split = number.split("\\-");
		int min = new Integer(split[0]);
		int max = new Integer(split[1]);
		return min <= count && count <= max;
	}

}
