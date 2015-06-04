package cn.mxz.xianshi;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

@Communication
public interface XianShiTransform {

	/**
	 * 万里寻仙所需元宝
	 * @return
	 */
	public WanLiGold getWanLiGold();

	void setUser(City user);
}
