package cn.mxz.dogz;

import java.util.Collections;
import java.util.List;

import cn.mxz.DogzOpenTemplet;
import cn.mxz.DogzOpenTempletConfig;

public class DogzConfig {

	/**
	 * 获得最大拥有神兽的数量
	 * @param maxDogzLevel
	 * @param level
	 * @return
	 */
	public static int getCountMax(int maxDogzLevel, int level) {
		List<DogzOpenTemplet> all = DogzOpenTempletConfig.getAll();
		Collections.reverse(all);
		for (DogzOpenTemplet t : all) {
			if (level >= t.getUserLevel() && maxDogzLevel >= t.getDogzLevel()) {
				return t.getCount();
			}
		}
		return 0;
	}
}
