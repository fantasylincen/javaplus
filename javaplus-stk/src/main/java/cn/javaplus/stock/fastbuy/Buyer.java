package cn.javaplus.stock.fastbuy;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.log.Log;
import cn.javaplus.stock.fastbuy.GpCouldBuyMonitor.Gp;
import cn.javaplus.util.Util;

public class Buyer extends Thread {

	private Collection<Gp> gpCouldBuy;

	public Buyer(Collection<Gp> gpCouldBuy) {
		this.gpCouldBuy = gpCouldBuy;
	}

	public void run() {
		while (true) {
			Gp gp = getChipest();
			if(gp == null) {
				Thread.yield();
				continue;
			}
				buy(gp);
				Util.Thread.sleep(1000);
			gpCouldBuy.remove(gp);
			System.exit(0);
		}
	}

	private Gp getChipest() {
		if(gpCouldBuy == null || gpCouldBuy.isEmpty())
			return null;
		List<Gp> ls = Lists.newArrayList(gpCouldBuy);
		Comparator< Gp> c = new Comparator<Gp>() {

			public int compare(Gp o1, Gp o2) {
				String p = o1.getPriceClose();
				String p2 = o2.getPriceClose();
				int a = (int) (new Double(p) * 1000);
				int b = (int) (new Double(p2) * 1000);
				return a - b;
			}
		};
		Collections.sort(ls, c);
		
		return ls.get(0);
	}

	private void buy(Gp gp) {
		String code = getCodeTrim(gp);
		String price = getBuyPrice(gp);
		if(gp.getCode().startsWith("300") || new Double(price) > 39.5) {
			Log.d("跳过购买", code, price);
			return ;
		}
		
		int count = getCount(price); 

		Log.d("购买", gp.getCode(), "price" , price, count);
				
		new TongHuaShunRobot().buy(code, price, count + "");
		
		Log.d("购买完毕", gp.getCode(), "price" , price, count);
	}

	private int getCount(String price) {
		return (int) (4000 / new Double(price)) / 100 * 100;
	}

	private String getBuyPrice(Gp gp) {
		
		String close = gp.getPriceClose();
		Double price = new Double(close);
		price = price *= 1.1;
		return toPriceString(price);
	}
	private String toPriceString(double close) {
		return String.format("%.2f", close);
	}

	private String getCodeTrim(Gp gp) {
		return gp.getCode().replaceAll("[a-z]", "");
	}
}
