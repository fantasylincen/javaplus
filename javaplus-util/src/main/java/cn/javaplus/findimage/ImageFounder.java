package cn.javaplus.findimage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.findimage.ImageFounder.Rect;

public class ImageFounder {

	public static class BaseDigitalsRect {

		private BufferedImage image;
		private String value;

		public BufferedImage getImage() {
			return image;
		}

		public void setImage(BufferedImage image) {
			this.image = image;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	private static final class ComparatorImplementation implements
			Comparator<DigitalSingle> {
		@Override
		public int compare(DigitalSingle o1, DigitalSingle o2) {
			return o1.getX() - o2.getX();
		}
	}

	public static class DigitalSingle {
		int x;
		int y;
		private String value;

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

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public static class RectImpl implements Rect {

		int x;
		int y;
		int w;
		int h;

		public RectImpl(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}

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

		public int getW() {
			return w;
		}

		public void setW(int w) {
			this.w = w;
		}

		public int getH() {
			return h;
		}

		public void setH(int h) {
			this.h = h;
		}

	}

	public interface Rect {
		int getX();

		int getY();

		int getW();

		int getH();
	}

	public static void main(String[] args) {
		String path = "D:/workspace/javaplus-stk/src/main/resources/head.bmp";
		Rect r = ImageFounder.findOnScreen(path, 0, 0, 1440, 900);
		int x = r.getX();
		int y = r.getY();
		System.out.println(x + ", " + y);

		int dx = 280;
		int dy = 297;
		int w = 100;
		int h = 8;

		String digital = ImageFounder.findDigitalOnScreen(x + dx, y + dy, w, h);
		System.out.println(digital);
	}

	/**
	 * 在屏幕上找这个区域的数字, 数字只能包含 "0-9" 以及 "." 号
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return
	 */
	public static String findDigitalOnScreen(int x, int y, int w, int h) {
		try {
			Robot r = new Robot();
			Rectangle screenRect = new Rectangle(x, y, w, h);
			BufferedImage src = r.createScreenCapture(screenRect);

			toBlack(src);

			return findDigital(src, x, y);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void toBlack(BufferedImage src) {
		int h = src.getHeight();
		int w = src.getWidth();
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int rgb = src.getRGB(x, y);
				Color c = new Color(rgb);

				int b = c.getBlue();
				int g = c.getGreen();
				int r = c.getRed();
				
				boolean isBlack = b < 127 || g < 127 || r < 127;

				if(isBlack) {
					b = 0xFF;
					g = 0xFF;
					r = 0xFF;
				} else {
					b = 0x00;
					g = 0x00;
					r = 0x00;
				}

				src.setRGB(x, y, new Color(r, g, b).getRGB());
			}
		}
	}

	static BufferedImage digitalsImage;
	private static ArrayList<BaseDigitalsRect> baseDigitals;

	/**
	 * 在图片中找寻数字
	 * 
	 * @param src
	 * @param xStart
	 * @param yStart
	 * @return
	 */
	public static String findDigital(BufferedImage src, int xStart, int yStart) {
		try {
			if (digitalsImage == null) {
				URL resource = ImageFounder.class.getResource("digitals.bmp");
				digitalsImage = ImageIO.read(resource);
				toBlack(digitalsImage);
			}
			List<DigitalSingle> digitals = findDigitals(src, xStart, yStart);
			Comparator<DigitalSingle> c = new ComparatorImplementation();
			Collections.sort(digitals, c);
			StringBuffer sb = new StringBuffer();
			for (DigitalSingle d : digitals) {
				sb.append(d.getValue());
			}
			return sb.toString();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<DigitalSingle> findDigitals(BufferedImage src,
			int xStart, int yStart) {
		List<BaseDigitalsRect> digitals = getBaseDigitals();
		ArrayList<DigitalSingle> ls = Lists.newArrayList();
		for (BaseDigitalsRect d : digitals) {
			ls.addAll(findDigitals(d, src, xStart, yStart));
		}
		return ls;
	}

	private static List<BaseDigitalsRect> getBaseDigitals() {
		if(baseDigitals != null)
			return baseDigitals;
		
		baseDigitals = Lists.newArrayList();
		add(baseDigitals, "0", 6 * 0);
		add(baseDigitals, "1", 6 * 1);
		add(baseDigitals, "2", 6 * 2);
		add(baseDigitals, "3", 6 * 3);
		add(baseDigitals, "4", 6 * 4);
		add(baseDigitals, "5", 6 * 5);
		add(baseDigitals, "6", 6 * 6);
		add(baseDigitals, "7", 6 * 7);
		add(baseDigitals, "8", 6 * 8);
		add(baseDigitals, "9", 6 * 9);
		add(baseDigitals, ".", 6 * 10);
		return baseDigitals;
	}

	private static void add(ArrayList<BaseDigitalsRect> ls, String value,
			int xStart) {
		BufferedImage image = getImageFromDigitals(xStart);
		BaseDigitalsRect r = new BaseDigitalsRect();
		r.setImage(image);
		r.setValue(value);
		ls.add(r);
//		
//		try {
//			ImageIO.write(image, "bmp", new File(
//					"C:\\Users\\Administrator\\Desktop\\" + value + ".bmp"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	private static BufferedImage getImageFromDigitals(int xStart) {

		try {

			Iterator<ImageReader> it = ImageIO
					.getImageReadersByFormatName("bmp");
			ImageReader reader = it.next();

			ByteArrayInputStream input = new ByteArrayInputStream(
					bufferedImageToByteArray());
			ImageInputStream iis = ImageIO.createImageInputStream(input);

			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(xStart, 0, 6, 8);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);

			iis.close();

			return bi;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private static byte[] bufferedImageToByteArray() {
		ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
		try {
			ImageIO.write(digitalsImage, "bmp", imageStream);
			imageStream.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
		}
		return imageStream.toByteArray();

	}

	private static List<DigitalSingle> findDigitals(BaseDigitalsRect d,
			BufferedImage src, int xStart, int yStart) {
		BufferedImage dst = d.getImage();
		int w = src.getWidth();
		int h = src.getHeight();

		int dw = dst.getWidth();
		int dh = dst.getHeight();

		ArrayList<DigitalSingle> ls = Lists.newArrayList();
		for (int x = 0; x <= w - dw; x++) {
			for (int y = 0; y <= h - dh; y++) {
//				write(src, x +"-" +y + "-src.bmp");
//				write(dst, x +"-" +y + "-dst.bmp");
				if (matches(x, y, src, dst)) {
					String v = d.getValue();
					int xx = x + xStart;
					int yy = y + yStart;
					DigitalSingle sg = createDigitalSingle(xx, yy, v);
					ls.add(sg);
				}
			}
		}
		return ls;
	}

	 static void write(BufferedImage src,
			String name) {

		try {
			ImageIO.write(src, "bmp", new File(
					"C:\\Users\\Administrator\\Desktop\\aaa\\" + name + ""));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	private static DigitalSingle createDigitalSingle(int xOnScreen,
			int yOnScreen, String value) {
		DigitalSingle single = new DigitalSingle();
		single.setX(xOnScreen);
		single.setY(yOnScreen);
		single.setValue(value);
		return single;
	}

	/**
	 * 找图, 返回该图在屏幕上的区域
	 * 
	 * @param imageFilePath
	 * @param xStart
	 * @param yStart
	 * @param w
	 * @param h
	 * @return
	 */
	public final static Rect findOnScreen(String imageFilePath, int xStart,
			int yStart, int w, int h) {
		try {
			Robot r = new Robot();
			Rectangle screenRect = new Rectangle(xStart, yStart, w, h);
			BufferedImage src = r.createScreenCapture(screenRect);

			File input = new File(imageFilePath);

			BufferedImage dst = ImageIO.read(input);
			return find(src, dst, xStart, yStart);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 找图, 返回该图在屏幕上的区域
	 * 
	 * @param imageFilePath
	 * @param xStart
	 * @param yStart
	 * @param w
	 * @param h
	 * @return
	 */
	public final static Rect findOnScreen(URL file, int xStart, int yStart,
			int w, int h) {
		try {
			Robot r = new Robot();
			Rectangle screenRect = new Rectangle(xStart, yStart, w, h);
			BufferedImage src = r.createScreenCapture(screenRect);

			BufferedImage dst = ImageIO.read(file);
			return find(src, dst, xStart, yStart);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 全屏找图, 返回该图在屏幕上的区域
	 * 
	 * @param imageFilePath
	 */
	public final static Rect findOnScreen(String imageFilePath) {
		Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
		int h = (int) s.getHeight();
		int w = (int) s.getWidth();
		return findOnScreen(imageFilePath, 0, 0, w, h);
	}

	/**
	 * 全屏找图, 返回该图在屏幕上的区域
	 * 
	 * @param imageFilePath
	 */
	public final static Rect findOnScreen(URL file) {
		Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
		int h = (int) s.getHeight();
		int w = (int) s.getWidth();
		return findOnScreen(file, 0, 0, w, h);
	}

	private static Rect find(BufferedImage src, BufferedImage dst, int xStart,
			int yStart) {
		int w = src.getWidth();
		int h = src.getHeight();

		int dw = dst.getWidth();
		int dh = dst.getHeight();

		for (int x = 0; x <= w - dw; x++) {
			for (int y = 0; y <= h - dh; y++) {
				if (matches(x, y, src, dst)) {
					return new RectImpl(x + xStart, y + yStart, dw, dh);
				}
			}
		}
		return null;
	}

	private static boolean matches(int xStart, int yStart, BufferedImage src,
			BufferedImage dst) {
		int dw = dst.getWidth();
		int dh = dst.getHeight();

		for (int x = 0; x < dw; x++) {
			for (int y = 0; y < dh; y++) {
				int dx = x + xStart;
				int dy = y + yStart;

				int rgb = src.getRGB(dx, dy);
				int rgb2 = dst.getRGB(x, y);
				if (rgb2 != rgb)
					return false;
			}
		}

		return true;

	}

	public static Rect findOnScreen(URL resource, URL resource2, int xOnScreen,
			int yOnScreen, int findW, int findH) {
		Rect f = findOnScreen(resource, xOnScreen, yOnScreen, findW, findH);
		if(f != null)
			return f;
		return findOnScreen(resource2, xOnScreen, yOnScreen, findW, findH);
	}
}
