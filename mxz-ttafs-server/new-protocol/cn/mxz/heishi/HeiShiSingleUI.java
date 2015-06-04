package cn.mxz.heishi;


public interface HeiShiSingleUI {

	GoodsUI getGoods();

	/**
	 * 美酒数量
	 * @return
	 */
	int getWineCount();

	/**
	 * 元宝数量
	 * @return
	 */
	int getGold();

	/**
	 * 剩余刷新时间
	 * @return
	 */
	int getRemainSec();
}
