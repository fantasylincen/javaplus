package cn.mxz.yunyou;

import java.util.Collection;

/**
 * @author 刘昆
 *
 */
public interface YunYouPlayer {

	/**
	 * 在遇到云游先人事件时调用, 有可能在遇到云游先人的情况下调用(这个时候该方法什么都不做)
	 */
	void onEvent();

//	/**
//	 * 获取该商品打折  情况, 如果不打折, 返回1
//	 * @param toolId
//	 * @return 0f-1f
//	 */
//
//	float getDiscount(int toolId);

	/**
	 * 当前云游先人数量
	 * @return
	 */

	int getXianRenCount();

//	boolean encounterXianRen();

	Collection<YunyouData> getList();

	YunyouData getDataById(int id);

	void getExp(int index, Boolean isDouble);

	void buy(int index);

	void getExpForceDouble(int index);
}
