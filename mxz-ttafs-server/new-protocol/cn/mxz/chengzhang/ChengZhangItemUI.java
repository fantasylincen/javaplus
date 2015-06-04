package cn.mxz.chengzhang;

public interface ChengZhangItemUI {

	/**
	 * 是否领取
	 * @return
	 */
	boolean getHasReceive();

	/**
	 * 是否可以领取
	 * @return
	 */
	boolean getCanReceive();
	
	/**
	 * 领取等级
	 * @return
	 */
	int getLevel();
	
	/**
	 * id
	 * @return
	 */
	int getId();
	
	/**
	 * 返利元宝
	 * @return
	 */
	int getGold();
}
