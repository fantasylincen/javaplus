package cn.javaplus.stock.t;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.stock.t.BufferedFrame.Paintable;

public class WuDangPanel implements Paintable {

	private List<IPrice> prices;

	public WuDangPanel(List<IPrice> prices) {
		this.prices = prices;
	}

	int d = 20;

	@Override
	public void paint(Graphics gg) {
		if (prices.isEmpty())
			return;

		IPrice last = prices.get(prices.size() - 1);

		int x = 0;
		SuperGraphics g = new SuperGraphics(gg);
		long max = getMax(last);
		paint(g, last.getSell5(), Color.BLUE, x += d, max);
		paint(g, last.getSell4(), Color.BLUE, x += d, max);
		paint(g, last.getSell3(), Color.BLUE, x += d, max);
		paint(g, last.getSell2(), Color.BLUE, x += d, max);
		paint(g, last.getSell1(), Color.BLUE, x += d, max);

		x += d;

		paint(g, last.getBuy1(), Color.RED, x += d, max);
		paint(g, last.getBuy2(), Color.RED, x += d, max);
		paint(g, last.getBuy3(), Color.RED, x += d, max);
		paint(g, last.getBuy4(), Color.RED, x += d, max);
		paint(g, last.getBuy5(), Color.RED, x += d, max);
	}

	private long getMax(IPrice last) {
		List<Long> ls = Lists.newArrayList();
		ls.add(last.getSell5());
		ls.add(last.getSell4());
		ls.add(last.getSell3());
		ls.add(last.getSell2());
		ls.add(last.getSell1());

		ls.add(last.getBuy1());
		ls.add(last.getBuy2());
		ls.add(last.getBuy3());
		ls.add(last.getBuy4());
		ls.add(last.getBuy5());

		return getMax(ls);
	}

	public static long getMax(java.util.Collection<Long> allKey) {
		if (allKey.isEmpty()) {
			throw new IllegalArgumentException("列表不能为空!");
		}

		long max = Long.MIN_VALUE;

		for (long value : allKey) {

			if (value > max) {

				max = value;
			}
		}

		return max;
	}

	private void paint(SuperGraphics g, long sell2, Color c, int y, long max) {

		g.fillRect(20, y + 20, getWidth(max, sell2), d - 2, c);
		g.drawString(sell2 + "", 20, y + 33, Color.WHITE);
	}

	private int getWidth(long max, long sell2) {

		int w = 100;

		long a;

		a = 10000000000L;
		if (max > a) {
			return cac(sell2, w, a);
		}
		a = 1000000000L;
		if (max > a) {
			return cac(sell2, w, a);
		}
		a = 100000000L;
		if (max > a) {
			return cac(sell2, w, a);
		}
		a = 10000000L;
		if (max > a) {
			return cac(sell2, w, a);
		}
		a = 1000000L;
		if (max > a) {
			return cac(sell2, w, a);
		}
		a = 100000L;
		return cac(sell2, w, a);

	}

	private int cac(long sell2, int w, long a) {
		return (int) (w * ((sell2 + 0f) / a));
	}

}
