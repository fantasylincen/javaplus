package cn.javaplus.stock.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.map.Maps;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock1;

public class ImageSaver {

	private static final int K_LINE_HEIGHT = 300;
	private static final int MACD_HEIGHT = 100;
	private static final int CJL_HEIGHT = 100;
	private static final int INFOMATION_HEIGHT = 100;

	private static final int WIDTH = 1200;
	private static final int H_ALL = K_LINE_HEIGHT + MACD_HEIGHT + CJL_HEIGHT
			+ INFOMATION_HEIGHT;

	private static final Color GREEN = new Color(70, 116, 70);

	static final int MACD_AREA_Y = K_LINE_HEIGHT;
	static final int CJL_Y = MACD_AREA_Y + MACD_HEIGHT;
	Color COLOR_AVG5 = new Color(24, 117, 127);
	Color COLOR_AVG10 = new Color(116, 33, 124);
	Color COLOR_AVG20 = new Color(225, 128, 225);
	Color COLOR_AVG30 = new Color(57, 123, 112);
	Color COLOR_AVG60 = Color.BLACK;
	static final int ALL_SIZE = 150;
	private static final int RECT_W = 5;
	static final int INFOMATION_Y = CJL_Y + CJL_HEIGHT;

	public void saveToImage(DayData buyDay, DayData sellDay) {
		List<DayData> datas = getDatas(buyDay, sellDay);
		MyImage img = new MyImage(WIDTH, H_ALL);

		img.fillRect(0, 0, WIDTH, H_ALL, Color.WHITE);
		drawLine(img, MACD_AREA_Y);
		drawLine(img, CJL_Y);
		drawLine(img, INFOMATION_Y);

		double maxPrice = getMaxPrice(datas);
		double minPrice = getMinPrice(datas);
		long maxChengJiaoLiang = getMaxChengJiaoLiang(datas);

		double maxMacdAbs = getMaxMacdAbs(datas); // macd最大绝对值
		for (int i = 0; i < datas.size(); i++) {
			DayData d = datas.get(i);
			drawMark(img, d, buyDay, sellDay, i);
			Color color = getColor(d, buyDay, sellDay);
			drawKLine(img, minPrice, maxPrice, d, i, color);

			drawMacdLine(img, d, i, getMacdColor(d), maxMacdAbs);
			drawChengJiaoLiang(img, d, i, maxChengJiaoLiang, color);
			drawAvgLines(img, d, i, minPrice, maxPrice);
		}

		drawInfomation(img, buyDay, sellDay);

		img.savePng(getFileName(buyDay, sellDay));
	}

	private void drawAvgLines(MyImage img, DayData d, int index,
			double minPrice, double maxPrice) {
		draw(img, d, 5, index, COLOR_AVG5, minPrice, maxPrice);
		draw(img, d, 10, index, COLOR_AVG10, minPrice, maxPrice);
		draw(img, d, 20, index, COLOR_AVG20, minPrice, maxPrice);
		draw(img, d, 30, index, COLOR_AVG30, minPrice, maxPrice);
		draw(img, d, 60, index, COLOR_AVG60, minPrice, maxPrice);
	}

	private void draw(MyImage img, DayData d, int day, int index, Color color,
			double minPrice, double maxPrice) {

		DayData yes = d.getYestoday();
		if (yes == null)
			return;

		double avg0 = getAvg(yes, day);
		double avg1 = getAvg(d, day);

		int x0 = getX(index - 1) + RECT_W / 2;
		int y0 = getY(minPrice, maxPrice, avg0);

		int x1 = getX(index) + RECT_W / 2;
		int y1 = getY(minPrice, maxPrice, avg1);

		img.drawLine(x0, y0, x1, y1, color);
	}

	private void drawLine(MyImage img, int y) {
		img.drawLine(0, y, WIDTH, y, Color.BLACK);
	}

	private void drawChengJiaoLiang(MyImage img, DayData d, int index,
			long maxChengJiaoLiang, Color color) {
		int x = getX(index);
	
		double pixBiCjl = (CJL_HEIGHT + 0d) / maxChengJiaoLiang;
	
		int height = (int) (pixBiCjl * d.getVolume());
	
		img.fillRect(x, CJL_Y  + CJL_HEIGHT - height, RECT_W, height, color);
	}

	private void drawMark(MyImage img, DayData d, DayData buyDay,
			DayData sellDay, int index) {
		if (d == buyDay || d == sellDay) {
			int x = getX(index) + RECT_W / 2;
			int x0 = x + 1;
			img.drawLine(x0, 0, x0, MACD_AREA_Y, Color.GRAY);
		}
	}

	private void drawInfomation(MyImage img, DayData buyDay, DayData sellDay) {
	
		int y = 15 + INFOMATION_Y;
		int d = 15;
		int x = 15;
	
		double buyPrice = Market.getBuyPrice(buyDay);
		double sellPrice = Market.getSellPrice(sellDay);
	
		String buyInfo = "date:" + buyDay.getDate() + " price" + buyPrice;
		String sellInfo = "date:" + sellDay.getDate() + " price" + sellPrice;
		String profit = (sellPrice - buyPrice) / buyPrice * 100 + " %";
	
		img.drawString(x, y += d, buyDay.getId(), Color.BLACK);
		img.drawString(x, y += d, buyInfo, Color.BLACK);
		img.drawString(x, y += d, sellInfo, Color.BLACK);
		img.drawString(x, y += d, profit, Color.BLACK);
	
	}

	private void drawMacdLine(MyImage img, DayData d, int index,
			Color macdColor, double maxMacdAbs) {
		int x = getX(index) + RECT_W / 2;
		int macdAreaHeight = MACD_HEIGHT;
		int macdBaseLineY = MACD_AREA_Y + macdAreaHeight / 2;
		double macd = d.getMacd();
		double pixBiMacd = macdAreaHeight / 2 / maxMacdAbs;
		int y = (int) (-macd * pixBiMacd) + macdBaseLineY;
		img.drawLine(x, y, x, macdBaseLineY, macdColor);
	}

	private void drawKLine(MyImage img, double minPrice, double maxPrice,
			DayData d, int index, Color color) {
		int rectW = 6;
		int x = getX(index);
	
		int openY = getY(minPrice, maxPrice, d.getOpen());
		int closeY = getY(minPrice, maxPrice, d.getClose());
		int highY = getY(minPrice, maxPrice, d.getHigh());
		int lowY = getY(minPrice, maxPrice, d.getLow());
	
		fillRect(img, openY, closeY, x, rectW, color);
		img.drawLine(x + rectW / 2, highY, x + rectW / 2, lowY, color);
	}

	private double getAvg(DayData d, int day) {

		if (day == 5)
			return d.getAvg5();

		if (day == 10)
			return d.getAvg10();

		if (day == 20)
			return d.getAvg20();

		if (day == 30)
			return d.getAvg30();

		if (day == 60)
			return d.getAvg60();

		throw new IllegalArgumentException();
	}

	private long getMaxChengJiaoLiang(List<DayData> datas) {
		long max = Long.MIN_VALUE;
		for (DayData d : datas) {
			long v = d.getVolume();
			if (v > max)
				max = v;
		}
		if(max < 0)
			return 0;
		return max;
	}

	private double getMaxMacdAbs(List<DayData> datas) {
		double max = 0;
		for (DayData d : datas) {
			double macd = d.getMacd();
			double abs = Math.abs(macd);
			if (abs > max)
				max = abs;
		}

		return max;
	}

	private List<DayData> getDatas(DayData buyDay, DayData sellDay) {
		Map<String, DayData> all = Maps.newHashMap();
		List<DayData> mid = getBetween(buyDay, sellDay);
		int headOrTailCount = (ALL_SIZE - mid.size()) / 2;
		List<DayData> head = getYestoday(buyDay, headOrTailCount);
		List<DayData> tail = getTomorrow(sellDay, headOrTailCount);

		add(all, mid);
		add(all, head);
		add(all, tail);

		List<DayData> newArrayList = Lists.newArrayList(all.values());
		Collections.sort(newArrayList);
		return newArrayList;
	}

	private List<DayData> getTomorrow(DayData day, int headOrTailCount) {
		ArrayList<DayData> ls = Lists.newArrayList();
		for (int i = 0; i < headOrTailCount; i++) {
			day = day.getTommorrow();
			if (day == null)
				break;
			ls.add(day);
		}
		return ls;
	}

	private List<DayData> getYestoday(DayData day, int headOrTailCount) {
		ArrayList<DayData> ls = Lists.newArrayList();
		for (int i = 0; i < headOrTailCount; i++) {
			day = day.getYestoday();
			if (day == null)
				break;
			ls.add(day);
		}
		return ls;
	}

	private List<DayData> getBetween(DayData buyDay, DayData sellDay) {
		ArrayList<DayData> ls = Lists.newArrayList();
		while (true) {
			ls.add(buyDay);
			if (buyDay == null || buyDay == sellDay)
				break;
			buyDay = buyDay.getTommorrow();
		}
		return ls;
	}

	private void add(Map<String, DayData> all, List<DayData> tail) {
		for (DayData dayData : tail) {
			all.put(dayData.getDate(), dayData);
		}

	}

	private double getMaxPrice(List<DayData> datas) {
		double max = -1;
		for (DayData d : datas) {
			if (d.getHigh() > max)
				max = d.getHigh();
		}
		return max;
	}

	private double getMinPrice(List<DayData> datas) {
		double min = Integer.MAX_VALUE;
		for (DayData d : datas) {
			if (d.getLow() < min)
				min = d.getLow();
		}
		return min;
	}

	private int getX(int index) {
		return index * 8;
	}

	private void fillRect(MyImage img, int openY, int closeY, int x, int w,
			Color color) {
		int y = Math.min(openY, closeY);
		int h = Math.abs(openY - closeY);
		if (h == 0)
			h = 1;

		img.fillRect(x, y, w, h, color);
	}

	private int getY(double minPrice, double maxPrice, double price) {
		double d = maxPrice - minPrice;
		double p = price - minPrice;

		double percent = p / d;
		return (int) (MACD_AREA_Y * (1 - percent));
	}

	private Color getMacdColor(DayData d) {
		double macd = d.getMacd();
		return macd < 0 ? GREEN : Color.RED;
	}

	private Color getColor(DayData d, DayData buyDay, DayData sellDay) {
		// if (d == buyDay || d == sellDay)
		// return Color.CYAN;
		if (d.getOpen() > d.getClose())
			return GREEN;
		return Color.RED;
	}

	private String getFileName(DayData buyDay, DayData sellDay) {
		double buy = Market.getBuyPrice(buyDay);
		double sell = Market.getSellPrice(sellDay);
		String file = Market.WORKSPACE_DIR + "buy-sell-" + Stock1.MACD_LONG + "." + Stock1.MACD_SHORT + "." + Stock1.MACD_MID + "-"
				+ (buy > sell ? "x-" : "o-") + buyDay.getId() + "-"
				+ buyDay.getDate() + "-" + sellDay.getDate() + ".png";
		return file;
	}
}
