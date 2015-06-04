package cn.mxz.chuangzhen;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

@Communication
public interface ChuangZhenTransform {

	/**
	 * 闯阵 第一个界面
	 * @return
	 */
	ChuangZhenPro getData();

	/**
	 * @return	排行榜信息
	 * @param count 上阵人数: 三人阵 四人阵 五人阵
	 */
	RankingListPro getRankingList(int count);

	/**
	 * 渡劫奖励奖励(包含了 "渡劫奖励" 和 "加成属性" 的数据)
	 * @return
	 */
	ChuangZhenReward getBattleReward();

	/**
	 * @param index	选择一种加成 0 选第一个  1 选第二个 2 选第三个
	 */
	void select(int index);

	/**
	 * 领取渡劫奖励
	 */
	void receive();

	/**
	 * 闯阵头像列表
	 * @return
	 */
	ChuangZhenHeads getHeads();
	
	/**
	 * 下次再说
	 */
	void skip();

	void setUser(City user);
}
