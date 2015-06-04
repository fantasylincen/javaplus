package cn.mxz.mission.old.map;

import java.util.Collection;
import java.util.List;

import cn.javaplus.util.Util.Random;
import cn.mxz.DemonParameter;
import cn.mxz.RandomEventTemplet;
import cn.mxz.RandomEventTempletConfig;

import com.google.common.collect.Lists;

/**
 * 随机事件生成器
 * @author 林岑
 *
 */

public class RandomEventGenerator {


	public RandomEventTemplet generate(DemonParameter t) {

		int type = 1;
//		出现的副本类型（1主线副本2boss活动3爬塔活动）

//		
		
		

		List<RandomEventTemplet> ls = get(type);

		int[] w = getW(ls);

		int index = Random.getRandomIndex(w);

		RandomEventTemplet temp = ls.get(index);

		return temp;
	}

	private List<RandomEventTemplet> get(int type) {
		List<RandomEventTemplet> ls = Lists.newArrayList();
		Collection<RandomEventTemplet> vs = RandomEventTempletConfig.getAll();
		for (RandomEventTemplet rt : vs) {
			if(contains(rt, type)) {
				ls.add(rt);
			}
		}
		return ls;
	}

	private boolean contains(RandomEventTemplet rt, int type) {
		String[] split = rt.getAppear().split(",");
		for (String string : split) {
			if(string.equals(type + "")) {
				return true;
			}
		}
		return false;
	}

	private int[] getW(List<RandomEventTemplet> ls) {

		int [] ws = new int[ls.size()];
		for (int i = 0; i < ws.length; i++) {
			ws[i] = ls.get(i).getEventWeight();
		}
		return ws;
	}

}
