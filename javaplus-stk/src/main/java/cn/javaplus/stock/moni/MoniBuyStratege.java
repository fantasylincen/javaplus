package cn.javaplus.stock.moni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.stock.apps.AppPrintBuyStratege4;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

public class MoniBuyStratege implements IBuyStratege {

	@Override
	public void sell(Player player) {
		List<MyStock> stocks = player.getStocks();
		List<MyStock> sells = Lists.newArrayList();
		for (MyStock s : stocks) {
			if (canSell(s)) {
				sells.add(s);
			}
		}

		for (MyStock s : sells) {
			player.sell(s);
		}
	}

	@Override
	public int getBuyCountThisTime(Player player, DayData data) {
		
		double buyUnit = player.getBuyUnit(); // 钱足够的情况下, 每个购买单元金额
		buyUnit -= player.getAsset(data.getId()); //减去已经持仓额度
		double rmb = player.getRmb();
	
		double buyPrice = Market.getBuyPrice(data);
		int canBuyCount = (int) ((rmb / buyPrice)) / 100 * 100;
		int wantBuyCount = (int) ((buyUnit / buyPrice)) / 100 * 100;
	
		int min = Util.Math.min(canBuyCount, wantBuyCount);
		return Math.max(0, min);
	}

	@Override
	public List<IStockRecommend> findBestSorted(List<Stock2> stocks) {
		ArrayList<IStockRecommend> ls = Lists.newArrayList();
		for (Stock2 s : stocks) {
			IStockRecommend e = new StockRecommend(s);
			int priority = e.getPriority();
			if (priority > 0)
				ls.add(e);
		}
		Collections.sort(ls);
		return Util.Collection.sub(ls, 50);
	}

	private boolean canSell(MyStock s) {
		DayData day = s.getToday();
		if (day == null) {
			return false;
		}
		if (day.isDieTing() || day.isZhangTing()) {
			return false;
		}

		boolean isGreen = day.isMacdGreen();
		boolean isFirst = day.getMacdRedCountBefore() >= AppPrintBuyStratege4.WITCH_RED_MACD_SELL - 1;
		return !isGreen && isFirst; // 第1根macd红线之后卖出
	}

}
