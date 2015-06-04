package cn.javaplus.smonitor.client;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.javaplus.findimage.ImageFounder;
import cn.javaplus.findimage.ImageFounder.Rect;
import cn.javaplus.log.Log;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;

public class HengTaiClient implements GuPiaoClient {

	public class Position {
		int x;
		int y;

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

	}

	public class MyStockImpl implements IMyStock {

		String id;
		int count;
		double priceNow;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public double getPriceNow() {
			return priceNow;
		}

		public void setPriceNow(double priceNow) {
			this.priceNow = priceNow;
		}
	}

	public static void main(String[] args) {
		HengTaiClient client = new HengTaiClient();
		List<IMyStock> stocks = client.getStocks();
		
		double all = 0;
		for (IMyStock s : stocks) {
			double priceNow = s.getPriceNow();
			int count = s.getCount();
			all += priceNow * count;
			Log.d(s.getId(), priceNow, count);
		}
		Log.d("all", all);
		
		client.buy("002240", "1.00", 100);
		client.refresh();
		
		client.sell("002400", "1.20", 100);

		double rmb = client.getRmb();
		client.refresh();
		Log.d("rmb", rmb);
	}

	@Override
	public List<IMyStock> getStocks() {
		closeTipsWindow();
		ArrayList<IMyStock> ls = Lists.newArrayList();
		Rect windowPos = getWindowPos();
		int x = windowPos.getX();
		int y = windowPos.getY();
		Log.d(x, y);
		while (true) {
			int xOnScreen = x + 194;
			int yOnScreen = y + 366;
			IMyStock s = findStock(xOnScreen, yOnScreen);
			if (s == null)
				break;
			ls.add(s);
			y += 16;
		}

		return ls;
	}

	private IMyStock findStock(int xOnScreen, int yOnScreen) {
		String code = findString(0, xOnScreen, yOnScreen);
		String count = findString(140, xOnScreen, yOnScreen);
		String priceNow = findString(510, xOnScreen, yOnScreen);
		MyStockImpl s = new MyStockImpl();
		if (code == null || code.isEmpty()) {
			return null;
		}
		s.setId(code);
		s.setCount(new Integer(count));
		s.setPriceNow(new Double(priceNow));

		return s;
	}

	String findString(int d, int xOnScreen, int yOnScreen) {
		int w = 72;
		return findString(d, xOnScreen, yOnScreen, w);
	}

	/**
	 * 
	 * @param d
	 * @param xOnScreen
	 * @param yOnScreen
	 * @param w
	 *            查找宽度
	 * @return
	 */
	private String findString(int d, int xOnScreen, int yOnScreen, int w) {
		int x = d + xOnScreen;
		return ImageFounder.findDigitalOnScreen(x, yOnScreen, w, 8);
	}

	@Override
	public double getRmb() {
		closeTipsWindow();

		Rect p = getWindowPos();
		int x = p.getX();
		int y = p.getY();
		int xOnScreen = x + 277;
		int yOnScreen = y + 297;
		String findString = findString(0, xOnScreen, yOnScreen, 100);
		return new Double(findString);
	}

	@Override
	public void buy(String code, String price, int count) {
		code = code.toLowerCase();
		code = code.replaceAll("[a-z]", "");
		closeTipsWindow();
		Rect p = getWindowPos();
		int x = p.getX();
		int y = p.getY();
		int xOnScreen = x + 240;
		int yOnScreen = y + 297 - 143;
		HengTaiTradeRobot r = new HengTaiTradeRobot();
		r.mouseClick(xOnScreen, yOnScreen);
		r.typeDatas(code, price, count + "");
	}

	private Rect getWindowPos() {
		URL resource = Resources.getResource("head.bmp");
		URL resource2 = Resources.getResource("head2.bmp");
		Rect r = ImageFounder.findOnScreen(resource, resource2, 0, 0, 800, 800);
		return r;
	}

	@Override
	public void sell(String code, String price, int count) {
		code = code.toLowerCase();
		code = code.replaceAll("[a-z]", "");
		closeTipsWindow();
		Rect p = getWindowPos();
		int x = p.getX();
		int y = p.getY();
		int xOnScreen = x + 650;
		int yOnScreen = y + 297 - 143;
		HengTaiTradeRobot r = new HengTaiTradeRobot();
		r.mouseClick(xOnScreen, yOnScreen);
		r.typeDatas(code, price, count + "");
	}

	public class HengTaiTradeRobot {

		Robot robot;

		public HengTaiTradeRobot() {
			try {
				robot = new Robot();
			} catch (AWTException e) {
				throw new RuntimeException(e);
			}
		}

		public void pressKey(int keyCode) {
			robot.keyPress(keyCode);
			robot.keyRelease(keyCode);
		}

		public void type(String string) {
			String[] cs = string.split("");
			for (String s : cs) {
				if (!s.isEmpty())
					pressKey(s);
			}
		}

		private void pressKey(String s) {
			s = s.toLowerCase();
			if ("0123456789".contains(s)) {
				char c = s.toCharArray()[0];
				char zero = '0';
				int d = c - zero;
				pressKey(KeyEvent.VK_0 + d);
			} else if ("qwertyuiopasdfghjklzxcvbnm".contains(s)) {
				char c = s.toCharArray()[0];
				char a = 'a';
				int d = c - a;
				pressKey(KeyEvent.VK_A + d);
			} else if (".".equals(s)) {
				pressKey(KeyEvent.VK_PERIOD);
			}

		}

		public void typeDatas(String code, String price, String count) {

			pressKey(KeyEvent.VK_RIGHT);
			pressKey(KeyEvent.VK_RIGHT);
			pressKey(KeyEvent.VK_RIGHT);
			pressKey(KeyEvent.VK_RIGHT);
			pressKey(KeyEvent.VK_RIGHT);
			pressKey(KeyEvent.VK_RIGHT);
			pressKey(KeyEvent.VK_RIGHT);

			pressKey(KeyEvent.VK_BACK_SPACE);
			pressKey(KeyEvent.VK_BACK_SPACE);
			pressKey(KeyEvent.VK_BACK_SPACE);
			pressKey(KeyEvent.VK_BACK_SPACE);
			pressKey(KeyEvent.VK_BACK_SPACE);
			pressKey(KeyEvent.VK_BACK_SPACE);
			pressKey(KeyEvent.VK_BACK_SPACE);

			type(code);
			pressKey(KeyEvent.VK_TAB);
			pressKey(KeyEvent.VK_TAB);

			type(price);
			pressKey(KeyEvent.VK_TAB);
			type(count);

			pressKey(KeyEvent.VK_ENTER);
			pressKey(KeyEvent.VK_ENTER);
			Util.Thread.sleep(600);
			pressKey(KeyEvent.VK_ENTER);
		}

		public void mouseClick(int x, int y) {
			robot.mouseMove(x, y);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}

	}

	@Override
	public void refresh() {
		closeTipsWindow();
		Rect p = getWindowPos();
		int x = p.getX();
		int y = p.getY();
		int xOnScreen = x + 451;
		int yOnScreen = y + 331;
		new HengTaiTradeRobot().mouseClick(xOnScreen, yOnScreen);
		Util.Thread.sleep(600);
	}

	private void closeTipsWindow() {
		Position pp = getTipsWindowPositionOnScreen();
		if (pp == null)
			return;

		int x = pp.getX() + 156;
		int y = pp.getY() + 153;
		new HengTaiTradeRobot().mouseClick(x, y);
		Util.Thread.sleep(600);
	}

	private Position getTipsWindowPositionOnScreen() {

		Rect p = getWindowPos();
		int x = p.getX();
		int y = p.getY();
		int xOnScreen = x + 205;
		int yOnScreen = y + 169;

		int findW = 200;
		int findH = 200;

		URL resource = Resources.getResource("tipswindow.bmp");
		URL resource2 = Resources.getResource("tipswindow2.bmp");
		Rect r = ImageFounder.findOnScreen(resource, resource2, xOnScreen,
				yOnScreen, findW, findH);

		if (r == null)
			return null;

		Position p3 = new Position();
		p3.setX(r.getX());
		p3.setY(r.getY());
		return p3;
	}
}
