package cn.mxz.fengshentai;

import java.util.List;

public interface IFengshentaiManager {

	/**
	 * 兑换封神令
	 */
	public abstract void exchange();

	/**
	 * 是否拥有封神令
	 */
	public abstract boolean isHasfengshenling();

	/**
	 * 封神
	 * true		封神成功
	 * false	封神失败
	 * @param fighterId
	 */
	public abstract boolean fengshen(int fighterId);
	
	
	/**
	 * 声望兑换道具
	 * @param propId		要兑换的道具id
	 * @return 
	 */
	public PropItem buy( int propId );
	
	/**
	 * 获取所有的
	 * @return
	 */
	public List<PropItem> getPropData();

}