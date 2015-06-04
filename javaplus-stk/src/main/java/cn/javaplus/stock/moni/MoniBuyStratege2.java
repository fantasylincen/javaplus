package cn.javaplus.stock.moni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.management.RuntimeErrorException;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.stock.apps.AppPrintBuyStratege6;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

public class MoniBuyStratege2 implements IBuyStratege {

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
			
			player.sell(s, getSellPrice(s));
		}
	}

	@Override
	public int getBuyCountThisTime(Player player, DayData data) {
		
		double buyUnit = player.getBuyUnit(); // 钱足够的情况下, 每个购买单元金额
		buyUnit -= player.getAsset(data.getId()); //减去已经持仓额度
		double rmb = player.getRmb();
	
		DayData yes = data.getYestoday();
		
//		double buyPrice = AppPrintBuyStratege6.getBuyPrice(data, yes.getClose());
		if(true)throw new RuntimeException();
		double buyPrice = yes.getClose() * 0.971;
		if(buyPrice == 0)
			return 0;
		
		int canBuyCount = (int) ((rmb / buyPrice)) / 100 * 100;
		int wantBuyCount = (int) ((buyUnit / buyPrice)) / 100 * 100;
	
		int min = Util.Math.min(canBuyCount, wantBuyCount);
		return Math.max(0, min);
	}

	@Override
	public List<IStockRecommend> findBestSorted(List<Stock2> stocks) {
		ArrayList<IStockRecommend> ls = Lists.newArrayList();
		for (Stock2 s : stocks) {
			IStockRecommend e = new StockRecommend2(s);
			int priority = e.getPriority();
			if (priority > 0)
				ls.add(e);
		}
		Collections.sort(ls);
		return Util.Collection.sub(ls, 50);
	}

	private boolean canSell(MyStock s) {
		DayData day = s.getToday();
		
		if(day == null)return false;
		
		double high = day.getHigh();

		double buyPrice = s.getSinglePrice();
		
		if(day.isDieTing() || day.isZhangTing()) {
			return false;
		}
		
		if(isTooManyDays(s)) {
			return true;
		}
		
//		return high / buyPrice > AppPrintBuyStratege6.SELL_CONDITION;
		return true;
	}


	private double getSellPrice(MyStock s) {
		
//		DayData day = s.getCurrentDay();
//		double open = day.getOpen();
//		
//		if(isTooManyDays(s))
//			return open;
//		
//		if(open / s.getSinglePrice() > AppPrintBuyStratege6.SELL_CONDITION) {
//			return open;
//		}
//		
//		return s.getSinglePrice() * AppPrintBuyStratege6.SELL_CONDITION;
		return Util.Random.get(s.getToday().getLow(), s.getToday().getHigh());
	}

	private boolean isTooManyDays(MyStock s) {
		DayData cd = s.getToday();
		DayData bd = s.getBuyDay();
		int space = Market.getSpace(cd, bd);
		return space >= AppPrintBuyStratege6.MAX_SELL_DAY;
	}

	
}
