package cn.mxz.shop;

import java.util.Iterator;
import java.util.List;

import cn.mxz.MarketPlaceTemplet;
import cn.mxz.MarketPlaceTempletConfig;
import cn.mxz.city.City;
import cn.mxz.protocols.user.shops.ShopsAllP.ShopToolsPro;
import cn.mxz.protocols.user.shops.ShopsAllP.ShopsAllPro;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

public class ShopsAllBuilder {

	/**
	 * 构造商城信息
	 * 
	 * @param city
	 *            城池信息
	 * 
	 * @return 商城信息
	 */
	public ShopsAllPro build(City city) {
		ShopsAllPro.Builder shopPro = ShopsAllPro.newBuilder();
		shopPro.setPlayerMoney(city.getPlayer().getGold());
		shopPro.addAllShopTools(getTools(city));
		return shopPro.build();
	}

	/**
	 * 设置反馈商品道具信息
	 * 
	 * @return 商品道具信息
	 */
	private List<ShopToolsPro> getTools(City city) {

		List<ShopToolsPro> toolsAll = getToolsAll(city);
		// Iterator<ShopToolsPro> it = toolsAll.iterator();
		// while (it.hasNext()) {
		//
		// ShopToolsPro next = it.next();
		//
		// // 购买上限限制
		// if (!new ShopperChecker().canBuy(city, next.getToolId())) {
		// it.remove();
		// }
		// }
		return toolsAll;
	}

	public List<ShopToolsPro> getToolsAll(City city) {
		List<ShopToolsPro> shopTools = Lists.newArrayList();

		MarketPlaceTemplet marketPlace = null;

		List<MarketPlaceTemplet> arrayList = MarketPlaceTempletConfig.getAll();

		Iterator<MarketPlaceTemplet> iterator = arrayList.iterator();

		while (iterator.hasNext()) {

			marketPlace = iterator.next();

			ShopToolsPro t = build(city, marketPlace);
			shopTools.add(t);
		}

		return shopTools;
	}

	private ShopToolsPro build(City city, MarketPlaceTemplet m) {

		ShopToolsPro.Builder tool;

		tool = ShopToolsPro.newBuilder();

		Shopper shopper = new Shopper(city);

		Price price = shopper.getPrice(m.getTypeId(), 1);

		tool.setCashNew(price.getCashNew());

		tool.setCashOld(price.getCashOld());

		tool.setCouponsNew(price.getGoldNew());

		tool.setCouponsOld(price.getGoldOld());

		tool.setDescription(m.getDescription());

		tool.setDressLevel(m.getDressLevel());

		tool.setToolId(m.getTypeId());

		tool.setToolName(m.getName());

		tool.setToolType(m.getType());

		tool.setRoad(m.getRoad());

		int today = city.getUserCounter().get(CounterKey.MARKET_BUY_COUNT,
				m.getTypeId());
		int his = city.getUserCounterHistory().get(CounterKey.MARKET_BUY_COUNT,
				m.getTypeId());
		
		
		tool.setBuyCountToday(today);
		tool.setBuyCountHistory(his);

		tool.setQuality(m.getQuality());

		tool.setTab(m.getTab());

		tool.setHasCount(getHasCount(city, m.getTypeId()));

		int canBuyCount = shopper.getCanBuyCountToday(m.getTypeId());
		int canBuyCountHis = shopper.getCanBuyCountForever(m.getTypeId());

		canBuyCount = Math.min(canBuyCount, canBuyCountHis);

		tool.setCanBuyCount(canBuyCount);

		tool.setCountMax(shopper.getBuyCountMaxToday(m.getTypeId()));

		tool.setNowCoupons(city.getPlayer().getGold());

		MarketPlaceTemplet temp = MarketPlaceTempletConfig.get(m.getTypeId());

		tool.setIsSpecial(temp.getIsSpecial() == 1);
		ShopToolsPro t = tool.build();
		// Debuger.debug("ShopServiceImpl.getShopTools()  " + m.getName() +
		// " -- " + getHasCount(city, m.getTypeId()));
		return t;
	}

	/**
	 * 获取背包中持有数量
	 * 
	 * @param city
	 *            城池信息
	 * @param toolId
	 *            装备ID
	 * 
	 * @return
	 */
	private int getHasCount(City city, int toolId) {

		return city.getBagAuto().getCount(toolId)
		/* + city.getTempBag().getCount(toolId) */;
	}

	// /**
	// * 获取最大可购买数
	// *
	// * @param city
	// * 城池信息
	// * @param minKey
	// * ID
	// * @param canBuyMax
	// * 最大购买数（基数）
	// *
	// * @return 当前可购买数
	// */
	// private int getCanBuyCount(City city, int minKey, int canBuyMax) {
	//
	// return canBuyMax
	// - city.getUserCounter()
	// .get(CounterKey.MARKET_BUY_COUNT, minKey);
	// }
}
