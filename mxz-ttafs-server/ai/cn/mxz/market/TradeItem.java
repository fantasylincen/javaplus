package cn.mxz.market;

import java.util.List;

/**
 * 單個神將的交易信息
 * @author 李山
 * @since	2013年9月5日15:25:52
 */
interface TradeItem {

	/**
	 * 獲得交易戰士信息
	 * @return
	 */
	TradeFighter getMes();

	/**
	 * 獲得該交易 所需要的所有道具
	 * @return
	 */
	List<TradePropItem> getAllProp();

}
