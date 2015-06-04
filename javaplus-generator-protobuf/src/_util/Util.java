package _util;


import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * 工具类
 *
 * @author 林岑
 * @since 2012年1月7日 09:55:08
 */
public class Util {

	public static final Random	R	= new Random();

	/**
	 * Thread.sleep
	 *
	 * @param m
	 */
	public static void sleep(long m) {

		try {

			Thread.sleep(m);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 在列表中截取元素, 如果数量大于c, 那么取前c个元素, 如果不足, 返回全部元素
	 *
	 * @param all
	 * @param c
	 * @return
	 */
	public static <T> List<T> sub(List<T> all, int c) {

		if (all.size() < c) {

			return new ArrayList<T>(all);

		} else {

			return new ArrayList<T>(all.subList(0, c));
		}
	}

	/**
	 * 打乱列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void upset(List all) {

		List ls = new ArrayList(all);

		all.clear();

		while (ls.size() > 0) {

			Object o = ls.remove(R.nextInt(ls.size()));

			all.add(o);
		}
	}

	/**
	 * 随机产生 min - max中的数值
	 *
	 * 比如 (min = -1, max = 3) 那么就会产生 {-1, 0, 1, 2, 3}
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomInt(int min, int max) {

		int n = max - min + 1;

		return R.nextInt(n) + min;
	}

	/**
	 * 返回 min - max 之间的一个随机数
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	public static double getRandomDouble(double min, double max) {
		return R.nextDouble() * (max - min) + min;
	}

	/**
	 * 获得最大值
	 */
	public static int getMax(Collection<Integer> allKey) {

		int max = Integer.MIN_VALUE;

		for (Integer value : allKey) {

			if (value > max) {

				max = value;
			}
		}

		return max;
	}

	/**
	 * 获得指定路径的配置
	 *
	 * @param fileName
	 *            配置文件路径
	 * @return
	 */
	public static Properties getProperties(String fileName) {

		Properties p = new Properties();

		try {

			p.load(new FileInputStream(new File(fileName)));

		} catch (Exception e) {

			throw new RuntimeException(e);
		}

		return p;

	}

	/**
	 * 合并两个列表
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> List<T> merge(List<T> a, List<T> b) {

		List<T> ls = new ArrayList<T>();

		ls.addAll(a);

		ls.addAll(b);

		return ls;
	}

	/**
	 *
	 * 将except中的元素, 从all中剔除后返回
	 *
	 * @param except
	 *            被剔除的
	 * @param all
	 *            所有的
	 * @return
	 */
	public static <T> List<T> reject(Collection<T> except, Collection<T> all1) {

		List<T> all = new ArrayList<T>(all1);

		Iterator<T> it = all.iterator();

		while (it.hasNext()) {

			T hero = it.next();

			if (except.contains(hero)) {

				it.remove();
			}
		}

		return all;
	}

	/**
	 * 找出两个列表当中相同的元素
	 *
	 * @param all1
	 * @param all2
	 * @return
	 */
	public static <T> List<T> getSame(List<T> all1, List<T> all2) {

		List<T> list = new ArrayList<T>(all1);

		Iterator<T> it = list.iterator();

		while (it.hasNext()) {

			T next = it.next();

			if (!all2.contains(next)) {

				it.remove();
			}
		}

		return list;
	}

	/**
	 * 随机返回列表中的一个对象
	 *
	 * @param ls
	 * @return
	 */
	public static <T> T getRandomOne(Collection<T> ls) {

		ArrayList<T> arrayList = new ArrayList<T>(ls);
		return arrayList.get(R.nextInt(arrayList.size()));
	}

	public static int getMin(Collection<Integer> allKey) {

		int min = Integer.MAX_VALUE;

		for (Integer value : allKey) {

			if (value < min) {

				min = value;
			}
		}

		return min;
	}

	public static String md5(String text) {
		StringBuffer md5 = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte[] digest = md.digest();
			for (int i = 0; i < digest.length; i++) {
				md5.append(Character.forDigit((digest[i] & 0xF0) >> 4, 16));
				md5.append(Character.forDigit((digest[i] & 0xF), 16));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5.toString();
	}

	public static String MD5Encrypt(String inStr) {
		MessageDigest md = null;
		String outStr = null;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(inStr.getBytes());
			outStr = bytetoString(digest);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		return outStr;
	}

	private static String bytetoString(byte[] digest) {
		String str = "";
		String tempStr = "";
		for (int i = 1; i < digest.length; i++) {
			tempStr = (Integer.toHexString(digest[i] & 0xff));

			if (tempStr.length() == 1) {
				str = str + "0" + tempStr;
			} else {
				str = str + tempStr;
			}
		}
		return str.toLowerCase();
	}

	/**
	 * 以一定几率发生某件事发生 前置条件: 0 <= percent <= 1 如果 percent >= 1, 那么事件就始终发生 如果
	 * percent <= 0, 那么时间就从不发生
	 *
	 * @param percent
	 *            发生的几率
	 * @return true: 发生, false: 未发生
	 */
	public static boolean isHappen(float percent) {
		synchronized (R) {
			return percent > R.nextFloat();
		}
	}


	public static RuntimeException toRuntimeException(Exception e) {
		return new RuntimeException(e);
	}
	
	public static RuntimeException toRuntimeException(String message, Exception e) {
		return new RuntimeException(message, e);
	}
}
