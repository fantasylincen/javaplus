package cn.mxz.market;

/**
 * 交易道具
 * @author 李山
 * @since 2013年9月5日15:30:31
 */
interface TradePropItem {

	/**
	 * 被交易神将类型
	 * @return
	 */
	int getTypeProperty();

	/**
	 * 被交易神将数量
	 * @return
	 */
	int getNub();

	/**
	 * 数据库主键
	 * @return
	 */
	
	Integer getTradId();

}
