package cn.mxz.market;

/**
 * 交易戰士信息
 * @author Administrator
 *
 */
interface TradeFighter {

	/**
	 * 戰士昵稱
	 * @return
	 */
	String getNick();

	/**
	 * 類型ID
	 * @return
	 */
	int getTypeId();

	/**
	 * 戰士唯一ID
	 * @return
	 */
	int getFighterId();

	/**
	 * 交易提出的時間
	 * @return
	 */
	int getTradeTime();

	/**
	 * 该神将属于哪个玩家
	 * @return
	 */
	String getBelongPlayer();
}
