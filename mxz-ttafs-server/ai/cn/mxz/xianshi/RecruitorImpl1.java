package cn.mxz.xianshi;

import cn.javaplus.time.colddown.ColdDown;
import cn.mxz.city.City;
import cn.mxz.util.cd.CDKey;
import cn.mxz.util.cd.CDManager;

class RecruitorImpl1 extends AbstractRecruitor implements Recruitor {

	RecruitorImpl1(City city, boolean isUseFreeTimes) {
		super(city, 1, isUseFreeTimes);
	}

	@Override
	protected int find(boolean noJia) {

		Finder finder = new FinderImpl(city, 1);
		return finder.find(noJia);
	}

	@Override
	public int getRemainingSec() {
		CDManager m = city.getCDManager();
		ColdDown cd = m.get(CDKey.RECRUIT1);
		return cd.getRemainingSec();
	}

	@Override
	public int getFreeTimes() {
		int rem = templet.getFreeNumber() - getRecruitTimesToday();
		if(rem < 0) {
			return 0;
		}
		return rem;
	}
}
