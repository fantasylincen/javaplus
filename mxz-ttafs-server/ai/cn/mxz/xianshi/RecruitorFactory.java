package cn.mxz.xianshi;

import cn.mxz.city.City;


public class RecruitorFactory {

	public static Recruitor create(int recruitType, City city, boolean isUseFreeTimes) {
		if(recruitType == 1) {
			return new RecruitorImpl1(city, isUseFreeTimes);
		} else if(recruitType == 2) {
			return new RecruitorImpl2(city, isUseFreeTimes);
		} else {
			return new RecruitorImpl3(city, isUseFreeTimes);
		}
	}

}
