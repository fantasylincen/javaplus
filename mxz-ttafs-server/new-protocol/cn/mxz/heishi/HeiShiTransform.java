package cn.mxz.heishi;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

/**
 *  神秘商店
 * @author 林岑
 *
 */
@Communication
public interface HeiShiTransform {

	/**
	 * 神秘商店界面
	 * @return
	 */
	HeiShiUI getUI();

	/**
	 * 刷新
	 */
	HeiShiUI refresh();

	/**
	 * 兑换
	 * @param id 物品ID
	 * @return
	 */
	HeiShiUI exchange(int id);

	 
	/**
	 * 兑换
	 * @param id 物品ID
	 * @return
	 */
	HeiShiSingleUI exchange2(int id);
	
	void setUser(City user);
}
