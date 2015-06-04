package cn.mxz.xianshi;

import cn.javaplus.time.colddown.ColdDown;
import cn.mxz.city.City;
import cn.mxz.util.cd.CDKey;
import cn.mxz.util.cd.CDManager;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.debuger.Debuger;

class RecruitorImpl2 extends AbstractRecruitor implements Recruitor {


	RecruitorImpl2(City city, boolean isUseFreeTimes) {
		super(city, 2, isUseFreeTimes);
	}

	@Override
	protected int find(boolean noJia) {

		UserCounter c = city.getUserCounterHistory();
		int times = c.get(CounterKey.RECRUIT_TIMES, 2);

		if(times == 1) {
			Debuger.debug("RecruitorImpl2.find() 必出乙魂魄");
			return getRandomFighter(3);	//第一次必出乙魂
		}


		Finder finder = new FinderImpl(city, 2);
		return finder.find(noJia);
	}

	@Override
	public int getRemainingSec() {
		CDManager m = city.getCDManager();
		ColdDown cd = m.get(CDKey.RECRUIT2);
		return cd.getRemainingSec();
	}

	@Override
	public int getFreeTimes() {
		int s = getRemainingSec();
		if(s == 0) {
			return 1;
		} else {
			return 0;
		}
	}
}
