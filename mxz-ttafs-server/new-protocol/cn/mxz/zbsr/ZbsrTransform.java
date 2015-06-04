package cn.mxz.zbsr;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

/**
 *  装备商人
 * @author 林岑
 *
 */
@Communication
public interface ZbsrTransform {

	/**
	 * 界面
	 * @return
	 */
	ZbsrUI getUI();

	/**
	 * 刷新
	 */
	ZbsrUI refresh();

	/**
	 * 兑换
	 * @param id 物品ID
	 * @return
	 */
	ZbsrUI exchange(int id);

	void setUser(City user);
}
