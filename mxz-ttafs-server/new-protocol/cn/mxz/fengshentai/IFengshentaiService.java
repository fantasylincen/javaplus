package cn.mxz.fengshentai;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

@Communication
public interface IFengshentaiService  {
	/**
	 * 获取主界面
	 * @return
	 */
	IFengshenMainUI getdata();
	
	/**
	 * 兑换封神令
	 */
	void exchange();
	
	/**
	 * 封神
	 * @return
	 */
	void fengshen(int fighterId);
	
	void setUser(City user);
	
	IExchangePropUI getExchangePropUI();
	
	/**
	 * 用声望兑换道具
	 * @param propId
	 * @return
	 */
	IPropTransform buy( int propId );
}
