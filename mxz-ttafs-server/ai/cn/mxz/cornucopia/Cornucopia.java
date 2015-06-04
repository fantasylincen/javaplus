package cn.mxz.cornucopia;

import cn.mxz.city.City;

/**
 * 聚宝盆(摇钱树)
 * @author 林岑
 *
 */
public interface Cornucopia {

	/**
	 * 摇钱
	 */
	void run();

	/**
	 * 今日摇钱次数
	 * @return
	 */
	int getRunTimesToday();

	/**
	 * 今日运势
	 * @return
	 */
	float getYunShi();

	/**
	 * 累计获得的金币
	 * @return
	 */
	int getAll();

	/**
	 * 今日最大摇钱次数
	 * @return
	 */
	int getMaxTime();

	City getCity();
}
