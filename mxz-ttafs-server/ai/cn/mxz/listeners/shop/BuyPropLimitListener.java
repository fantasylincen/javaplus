package cn.mxz.listeners.shop;

import message.S;
import cn.mxz.MarketPlaceTemplet;
import cn.mxz.MarketPlaceTempletConfig;
import cn.mxz.VipPrivilegeTemplet;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.shop.BeforeBuyEvent;
import cn.mxz.prop.PropIds;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

/**
 * 购买限购
 * 
 * @author 林岑
 * 
 */
public class BuyPropLimitListener implements Listener<BeforeBuyEvent> {

	@Override
	public void onEvent(BeforeBuyEvent e) {

		// 永久购买上限限制
		checkForeverLimit(e);

		int level = e.getCity().getVipPlayer().getLevel();
//		VipPrivilegeTemplet temp = VipPrivilegeTempletConfig.get((byte) level);

		checkVipLevel(level, e.getTypeId());

		
//		2014年7月15日 14:00:58 黄毕生屏蔽
//		// 金宝箱上限
//		check(e, PropIds.JinBaoXiang_140029, temp.getGoldenBox());
//
//		// 银宝箱上限
//		check(e, PropIds.YinBaoXiang_140028, temp.getSilverBox());

	}

	private void checkVipLevel(int level, int typeId) {
		MarketPlaceTemplet temp = MarketPlaceTempletConfig.get(typeId);
		if (level < temp.getVipLevelNeed()) {
			throw new OperationFaildException(S.S10235);
		}
	}

	private void checkForeverLimit(BeforeBuyEvent e) {
		City city = e.getCity();
		int id = e.getTypeId();
		if (!new ShopperChecker().canBuy(city, id)) {
			throw new SureIllegalOperationException("不能购买此商品");
		}
	}

	private void check(BeforeBuyEvent e, int id, int max) {
		if(id != e.getTypeId()) {
			return;
		}
		City city = e.getCity();
		UserCounter c = city.getUserCounter();
		int times = c.get(CounterKey.MARKET_BUY_COUNT, id);
		if (times + e.getCount() > max) {
			throw new OperationFaildException(S.S60137);
		}
	}

}
