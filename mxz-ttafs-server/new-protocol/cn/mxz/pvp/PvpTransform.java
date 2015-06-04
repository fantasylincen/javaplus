package cn.mxz.pvp;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

@Communication
public interface PvpTransform {
	
	/**
	 * Pvp界面
	 * @return
	 */
	PvpDuiHuanUI getDuiHuanUI();

	/**
	 * 兑换
	 * @param typeId
	 */
	PvpDuiHuanResultPro duiHuan(int typeId);
	
	void setUser(City user);
}
