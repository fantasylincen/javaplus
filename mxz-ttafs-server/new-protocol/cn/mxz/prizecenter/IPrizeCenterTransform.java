package cn.mxz.prizecenter;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

@Communication
public interface IPrizeCenterTransform {

	IPrizeCenterUI getData();

//	void getPrize( int id );

	void setUser(City user);

	/**
	 * 从蓝港获取奖品
	 * @param id
	 * @param count
	 */
	public void getPrizeFromLinekong(String prizeStr,int activityId);
	
	/**
	 * 获得非蓝港的奖励
	 * @param activityId
	 */
	void getPrize( int activityId );

}
