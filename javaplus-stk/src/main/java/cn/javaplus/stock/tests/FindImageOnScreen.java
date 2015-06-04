package cn.javaplus.stock.tests;

import cn.javaplus.findimage.ImageFounder;
import cn.javaplus.findimage.ImageFounder.Rect;
import cn.javaplus.log.Log;
import cn.javaplus.util.Resources;

public class FindImageOnScreen {
	public static void main(String[] args) {
		Rect find = ImageFounder.findOnScreen(Resources.getResource("maichuzhengquandaima.bmp"), 0, 0, 1200, 800);
		Log.d(find.getX(), find.getY(), find.getW(), find.getH());
	}
}
