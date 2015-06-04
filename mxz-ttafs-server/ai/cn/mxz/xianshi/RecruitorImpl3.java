package cn.mxz.xianshi;

import cn.javaplus.time.colddown.ColdDown;
import cn.mxz.city.City;
import cn.mxz.script.ScriptOld;
import cn.mxz.util.cd.CDKey;
import cn.mxz.util.cd.CDManager;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

class RecruitorImpl3 extends AbstractRecruitor implements Recruitor {

	protected RecruitorImpl3(City city, boolean isUseFreeTimes) {
		super(city, 3, isUseFreeTimes);
	}

	@Override
	protected int find(boolean noJia) {

		UserCounter c = city.getUserCounterHistory();
		int times = c.get(CounterKey.RECRUIT_TIMES, 3);

		int step = ScriptOld.findfighter.getStep(times);
		
		if(noJia) {//必不出甲
			if(step == 4 || step == 5) {
				step = 3;
			}
		}

		if(step != -1) {
			return getRandomFighter(step);
		}

		Finder finder = new FinderImpl(city, 3);
		
		int find = finder.find(noJia);
		
		return find;
	}

	@Override
	public int getRemainingSec() {
		CDManager m = city.getCDManager();
		ColdDown cd = m.get(CDKey.RECRUIT3);
		return cd.getRemainingSec();
	}

	@Override
	public int getFreeTimes() {
		int s = getRemainingSec();
		if (s == 0) {
			return 1;
		}
		return 0;
	}
}
