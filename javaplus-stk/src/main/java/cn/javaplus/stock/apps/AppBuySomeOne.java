package cn.javaplus.stock.apps;

import cn.javaplus.stock.fastbuy.TongHuaShunRobot;

public class AppBuySomeOne {
	public static void main(String[] args) {
		new TongHuaShunRobot().buy("000415", "15.95", "200");
	}
}
