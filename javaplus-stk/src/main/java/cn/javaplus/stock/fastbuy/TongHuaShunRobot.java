package cn.javaplus.stock.fastbuy;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class TongHuaShunRobot {

	Robot robot;

	public TongHuaShunRobot() {
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

	public static void main(String[] args) {
		new TongHuaShunRobot().buy("002240", "6.97", "100");
		// new TongHuaShunRobot().buy("601288", "3.78", "1000");
	}

	public void buy(String code, String price, String count) {

		mouseClick(GpCouldBuyMonitor.OK_BUTTON_X, GpCouldBuyMonitor.OK_BUTTON_Y);

		mouseClick(GpCouldBuyMonitor.BUY_TEXT_FIELD_X,
				GpCouldBuyMonitor.BUY_TEXT_FIELD_Y);

		pressKey(KeyEvent.VK_BACK_SPACE);
		pressKey(KeyEvent.VK_BACK_SPACE);
		pressKey(KeyEvent.VK_BACK_SPACE);
		pressKey(KeyEvent.VK_BACK_SPACE);
		pressKey(KeyEvent.VK_BACK_SPACE);
		pressKey(KeyEvent.VK_BACK_SPACE);
		pressKey(KeyEvent.VK_BACK_SPACE);

		type(code);
		pressKey(KeyEvent.VK_TAB);

		type(price);
		pressKey(KeyEvent.VK_TAB);
		type(count);

		pressKey(KeyEvent.VK_ENTER);
		pressKey(KeyEvent.VK_ENTER);
		pressKey(KeyEvent.VK_ENTER);
	}

	public void mouseClick(int x, int y) {
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

}