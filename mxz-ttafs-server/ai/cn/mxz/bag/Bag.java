package cn.mxz.bag;

import java.util.List;

import cn.mxz.city.City;

/**
 * 背包
 * @author 	林岑
 * @since	2012年10月12日 15:43:58
 */
public interface Bag<T extends Grid> extends Iterable<T>{

	/**
	 * 背包容量, 格子开启数
	 */
	int getCapacity();

	/**
	 * 获取背包中typeId的数量
	 * @param typeId
	 */
	int getCount(int typeId);

	/**
	 * 获取所有装有东西的格子
	 * @return
	 */
	List<T> getAll();

	/**
	 * 移除指定数量的道具
	 * @param typeId
	 * @param porpNumber
	 */
	void remove(int typeId, int count);

	/**
	 * 增加指定的道具
	 * @param id
	 * @param count
	 */
	void addProp(int id, int count);

	/**
	 * 根据位置获得格子
	 * @param position
	 * @return
	 */
	T get(int position);

	/**
	 * 背包是否满了
	 * @return
	 */
	boolean isFull();

//	/**
//	 * 移除所有的指定根据识别ID的道具
//
//	 * @param spotId	物品识别ID
//	 */
//	void removeAllBySpotId(int spotId);

	/**
	 * 背包MD5值
	 * @return
	 */
	String getMD5();

	City getCity();

	/**
	 * 增加道具
	 * @param props 
	 */
	void addProp(String props);
}
