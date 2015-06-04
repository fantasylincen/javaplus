package cn.mxz.listeners.shop;

import cn.mxz.MarketPlaceTemplet;
import cn.mxz.MarketPlaceTempletConfig;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class ShopperChecker {

	/**
	 * 是否没有达到历史购买上限
	 * @param city
	 * @param id
	 * @return
	 */
	public boolean canBuy(City city, int id) {
		MarketPlaceTemplet temp = MarketPlaceTempletConfig.get(id);
		int forevermax = temp.getForevermax();
		
		if(forevermax < 0) { //跳过检查
			return true;
		}
		
		UserCounter c = city.getUserCounterHistory();
		int times = c.get(CounterKey.MARKET_BUY_COUNT, id);
		
		return times < forevermax;
	}

}
