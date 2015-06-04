package cn.mxz.bag;

import cn.mxz.city.City;

/**
 * 快照工具
 *
 * @author 林岑
 */
public interface Snapsort {

	/**
	 * 第一次快照存储数据
	 * 第二次快照如果数据变化, 会主动通知客户端
	 * @param city
	 */
	void snapsort(City city);

}