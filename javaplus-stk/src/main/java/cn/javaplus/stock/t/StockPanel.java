package cn.javaplus.stock.t;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.stock.t.BufferedFrame.Paintable;
import cn.javaplus.util.Util;

public class StockPanel implements Paintable {

	private static final int POINT_COUNT = 100;
	double hView = 0.05;// 纵轴能看见的幅度 百分比
	private List<IPrice> prices;

	int w;
	int h;
	int x;
	int y;

	public StockPanel(List<IPrice> prices) {
		this.prices = prices;
		w = 800;
		h = 1000;
		x = 0;
		y = 0;
	}

	@Override
	public void paint(Graphics g) {
		SuperGraphics gg = new SuperGraphics(g);
		gg.fillRect(x, y, w, h, Color.BLACK);
		List<IPrice> ps = getLast();

		if (ps.isEmpty())
			return;

		ps = parseVol(ps);// 转换chengjiaoliang后

		int x = this.x + w - 20;
		int firstY = this.y + h / 2;
		double firstUp = ps.get(0).getUp();

		long maxV = getMaxV(ps);
		for (IPrice p : ps) {
			paintV(gg, x, p.getV(), maxV);
			paint(p, gg, x, firstY, firstUp);
			x -= w / POINT_COUNT;
		}
	}

	private long getMaxV(List<IPrice> ps) {
		long max = 1;
		for (IPrice p : ps) {
			long pv = p.getV();
			if (pv > max)
				max = pv;
		}
		return max;
	}

	private void paintV(SuperGraphics gg, int x2, long v, long maxV) {

		int len = (int) ((v + 0d) / (maxV + 0d) * h);
		int y = h / 2 - len / 2;
		gg.fillRect(x2 + this.x, y + this.y, 4, len, Color.RED);
	}

	private List<IPrice> parseVol(List<IPrice> ps) {
		ArrayList<IPrice> ls = Lists.newArrayList();
		ls.add(new PriceAdaptor2(0, ps.get(0)));
		for (int i = 0; i < ps.size() - 1; i++) {
			IPrice p1 = ps.get(i);
			IPrice p2 = ps.get(i + 1);

			long v = p2.getV() - p1.getV();

			ls.add(new PriceAdaptor2(v, p2));
		}
		return ls;
	}

	private void paint(IPrice p, SuperGraphics gg, int x, int firstY, double firstUp) {
		double up = p.getUp();
		double d = up - firstUp;
		int y = (int) (d / hView * h + firstY);
		gg.fillRect(x, y, 4, 4, Color.CYAN);
	}

	private List<IPrice> getLast() {
		List<IPrice> ls = Lists.newArrayList(prices);

		Collections.reverse(ls);
		return Util.Collection.sub(ls, POINT_COUNT);
	}

}
