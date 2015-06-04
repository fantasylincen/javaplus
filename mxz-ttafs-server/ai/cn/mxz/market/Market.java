package cn.mxz.market;

import java.util.List;

import cn.mxz.city.City;

/**
 * 交易中心
 * @author 	李山
 * @since	2013年8月28日 16:57:56
 *
 */
interface Market {

	/**
	 * 判断某个人的某个神将是否正在交易
	 *
	 * @param id		某个人的帐号
	 * @param fighterId 某个神将的唯一ID
	 * @return
	 */
	
	boolean isTrading(String id, int fighterId);

	/**
	 * 獲得某個玩家所有神將的交易記錄
	 * @param id
	 * @return
	 */
	List<TradeItem> getAllTraceItem(String id);
	
	/**
	 * 获得某个神将在市场上的交易信息
	 * @param fighterId
	 * @param city 
	 * @return
	 */
	List<TradeItem> getAllFighterTradeMes(int fighterId, City city);

}