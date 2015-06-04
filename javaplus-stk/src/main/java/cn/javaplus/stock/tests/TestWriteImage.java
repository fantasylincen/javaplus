package cn.javaplus.stock.tests;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cn.javaplus.stock.util.Market;
import cn.javaplus.stock.util.MyImage;

public class TestWriteImage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String file = Market.WORKSPACE_DIR + "buy-sell-000001.png";
		
		MyImage img = new MyImage(800, 400);
		img.savePng(file);
	}

}
