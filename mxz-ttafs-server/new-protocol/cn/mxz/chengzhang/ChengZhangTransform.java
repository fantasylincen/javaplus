package cn.mxz.chengzhang;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

/**
 * 成长计划
 * @author 林岑
 */
@Communication
public interface ChengZhangTransform {

	/**
	 * 获取成长计划界面数据
	 */
	ChengZhangUI getData();
	
	/**
	 * 购买成长计划特权
	 */
	ChengZhangUI buy();
	
	/**
	 * 领取成长计划奖励
	 * @param id
	 */
	ChengZhangUI receive(int id);
	
	void setUser(City user);
}
