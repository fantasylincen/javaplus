package cn.mxz.server;

import cn.javaplus.web.WebContentFethcer;


public class AppTest {
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			WebContentFethcer.get("utf8", "http://localhost:31520/oneKeyRegist");
		}
	}
}
