package cn.javaplus.stock.fastbuy;

import java.sql.Date;
import java.text.SimpleDateFormat;

import cn.javaplus.log.Log;
import cn.javaplus.time.Time;
import cn.javaplus.util.Util;

public class Refresher extends Thread {

	SimpleDateFormat sf = new SimpleDateFormat("HHmmss");

	@Override
	public void run() {
		while (true) {
			sleep();
			refresh();
		}
	}

	private void refresh() {
		if (!inJiaoYiTime()) {
			TongHuaShunRobot r = new TongHuaShunRobot();
			int x = GpCouldBuyMonitor.REFRESH_BUTTON_X;
			int y = GpCouldBuyMonitor.REFRESH_BUTTON_Y;
			r.mouseClick(x, y);
			
			Log.d("refresh", x, y);
		}
	}

	public static void main(String[] args) {
		new Refresher().refresh();
	}

	private boolean inJiaoYiTime() {
		Date date = new Date(System.currentTimeMillis());
		String time = sf.format(date);
		int now = new Integer(time);
		return 91000 < now && now < 94000;
	}

	private void sleep() {
		long min = 30 * Time.MILES_ONE_MIN;
		long max = 60 * Time.MILES_ONE_MIN;
		double random = Util.Random.get(min, max);
		Util.Thread.sleep((long) random);
	}
}
