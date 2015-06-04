package cn.mxz.shop;

import java.util.List;

import message.S;
import cn.mxz.MarketPlaceAdditionTemplet;
import cn.mxz.MarketPlaceAdditionTempletConfig;
import cn.mxz.MarketPlaceTemplet;
import cn.mxz.MarketPlaceTempletConfig;
import cn.mxz.VipPrivilegeTemplet;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.events.Events;
import cn.mxz.events.shop.BeforeBuyEvent;
import cn.mxz.log.Logs;
import cn.mxz.market.Goods;
import cn.mxz.temp.TempCollection;
import cn.mxz.temp.TempKey;
import cn.mxz.user.Player;
import cn.mxz.util.checker.Checker;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.vip.VipPlayer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import define.D;

public class Shopper {

	public class Calc {

		private int toolId;
		private int count;
//		private int priceAdd;

		public Calc(int toolId, int count/*, int priceAdd*/) {
			this.toolId = toolId;
			this.count = count;
//			this.priceAdd = priceAdd;
		}

		private int m(int base) {
//			base += priceAdd;
			
			float sum = 0;
			
			for (int i = 1; i <= count; i++) {
				sum += base * getX(i);
			}
			
//			base *= count;
			return (int) sum;
		}

		private float getX(int i) {
			CounterKey key = CounterKey.MARKET_BUY_COUNT;
			int c = city.getUserCounter().get(key, toolId) + i;
			List<MarketPlaceAdditionTemplet> all = MarketPlaceAdditionTempletConfig
					.findByPropId(toolId);
			if (all.isEmpty()) {
				return 1;
			}

			for (MarketPlaceAdditionTemplet m : all) {
				if (c >= m.getMin() && m.getMax() >= c) {
					return m.getX();
				}
			}
			Debuger.error("购买次数异常:" + c);
			return 1;
		}

	}

	private City city;

	public Shopper(City city) {
		this.city = city;
	}

	public void buy(int toolId, int count) {

		Events.getInstance().dispatch(new BeforeBuyEvent(toolId, count, city));
		
		Price p = getPrice(toolId, count);

		if (count <= 0) {
			throw new IllegalArgumentException("-数量必须大于0");
		}
		MarketPlaceTemplet temp = MarketPlaceTempletConfig.get(toolId);

		boolean isJinBeiKe = temp.getGold() == 1;

		Checker c = city.getChecker();

		if (isJinBeiKe) {
			c.checkGoldOrJinDing(p.getGoldNew());
		} else {
			c.checkGold(p.getGoldNew());
		}

		c.checkPlayerProperty(PlayerProperty.CASH, p.getCashNew());

		check(toolId, count);

		Player player = city.getPlayer();

		savePropThisTimeWillBuy(toolId, count, p.getGoldNew());
		
		if (isJinBeiKe) {
			player.reduceGoldOrJinDing(p.getGoldNew());
		} else {
			player.reduceGold(p.getGoldNew());
		}

		player.reduce(PlayerProperty.CASH, p.getCashNew());

		city.getBagAuto().addProp(toolId, count);

		city.getUserCounterAuto().add(CounterKey.MARKET_BUY_COUNT, count,
				toolId);
		Logs.buyLog.addBuyLog(toolId, count, p.getGoldNew(), p.getCashNew(),
				city);

		savePropThisTime(toolId, count, p.getGoldNew());
	}

	private void savePropThisTimeWillBuy(int toolId, int count, int goldNew) {
		TempCollection c = city.getTempCollection();
		c.put(TempKey.WILL_BUY_PROP_THIS_TIME, new Goods(toolId, count, goldNew));
	}

	private void check(int toolId, int count) {
		MarketPlaceTemplet temp = MarketPlaceTempletConfig.get(toolId);
		if (temp == null) {
			throw new NullPointerException(toolId + "");
		}

		int canBuyCount = getCanBuyCountToday(toolId);

		if (count > canBuyCount) {

			int c = count - canBuyCount;
			throw new OperationFaildException(S.S10084, c);
		}
	}

	/**
	 * 获得商品价格
	 * 
	 * @param toolId
	 * @param count
	 * @return
	 */
	public Price getPrice(int toolId, int count) {

		MarketPlaceTemplet temp = MarketPlaceTempletConfig.get(toolId);
		int cashPrice;
		int price;
		int priceOld = temp.getCouponsOld();
		int cashPriceOld = temp.getCashOld();

//		int todayCount = city.getUserCounter().get(CounterKey.MARKET_BUY_COUNT,
//				toolId);

//		int priceAdd = Script.ShangCheng.getPriceAdd(temp.getCouponsPar(),
//				temp.getCouponsAdd(), todayCount);

		if (temp.getIsSpecial() == 1) {
			price = temp.getCouponsNew();
			cashPrice = temp.getCashNew();
		} else {
			price = temp.getCouponsOld();
			cashPrice = temp.getCashOld();
		}

		Calc c = new Calc(toolId, count/*, priceAdd*/);

		price = c.m(price);
		cashPrice = c.m(cashPrice);
		priceOld = c.m(priceOld);
		cashPriceOld = c.m(cashPriceOld);


		boolean isJinBeiKe = temp.getGold() == 1;
		
		return new Price(isJinBeiKe, price, cashPrice, priceOld, cashPriceOld);
	}

	private void savePropThisTime(int toolId, int count, int price) {
		TempCollection c = city.getTempCollection();
		c.put(TempKey.BUY_PROP_THIS_TIME, new Goods(toolId, count, price));
	}

	public int getCanBuyCountToday(int toolId) {

		int max = getBuyCountMaxToday(toolId);
		
		UserCounter c = city.getUserCounter();
		
		return getMax(toolId, max, c);
	}

	private int getMax(int toolId, int max, UserCounter c) {
		int count =  c.get(CounterKey.MARKET_BUY_COUNT, toolId);
		int cc = max - count;

		if (cc < 0) {
			cc = 0;
		}

		return cc;
	}
	
	public int getCanBuyCountForever(int toolId) {

		int max = getBuyCountMaxForever(toolId);
		
		UserCounter his = city.getUserCounterHistory();
		
		return getMax(toolId, max, his);
	}


	private int getVipLimit(int max, int toolId, MarketPlaceTemplet t) {
		String vipLimit = t.getVipLimit();
		JSONObject o = JSON.parseObject(vipLimit);
		int level = city.getVipPlayer().getLevel();
		Integer count = o.getInteger("vip" + level);
		if(count == null) {
			return max;
		}
		return count;
	}

	/**
	 * 最大可购买次数(今日)
	 * @param typeId
	 * @return
	 */
	public int getBuyCountMaxToday(int typeId) {
		final MarketPlaceTemplet t = MarketPlaceTempletConfig.get(typeId);

		if(t.getTypeId() == D.ID_SHEN_XING_DAN) {//神行丹
			VipPrivilegeTemplet tt = getVipTemplet();
			return tt.getAddPower();
		}
		
		if(t.getTypeId() == D.ID_HUI_QI_DAN) { //回气丹
			VipPrivilegeTemplet tt = getVipTemplet();
			return tt.getAddVigor();
		}
		
		int max = t.getMax();

		return max(typeId, t, max);
	}
	
	private VipPrivilegeTemplet getVipTemplet() {
		VipPlayer vip = city.getVipPlayer();
		int level = vip.getLevel();
		return VipPrivilegeTempletConfig.get((byte) level);
	}

	/**
	 * 最大可购买次数(永久)
	 * @param typeId
	 * @return
	 */
	public int getBuyCountMaxForever(int typeId) {
		final MarketPlaceTemplet t = MarketPlaceTempletConfig.get(typeId);

		int max = t.getForevermax();

		return max(typeId, t, max);
	}

	private int max(int typeId, final MarketPlaceTemplet t, int max) {
		if (max == -1) {
			max = Integer.MAX_VALUE;
		}

		max = getVipLimit(max, typeId, t);
		return max;
	}

}
