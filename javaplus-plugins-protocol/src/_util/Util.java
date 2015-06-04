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

public class Util {

	public static final Random	R	= new Random();

	public static void sleep(long m) {

		try {

			Thread.sleep(m);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public static <T> List<T> sub(List<T> all, int c) {

		if (all.size() < c) {

			return new ArrayList<T>(all);

		} else {

			return new ArrayList<T>(all.subList(0, c));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void upset(List all) {

		List ls = new ArrayList(all);

		all.clear();

		while (ls.size() > 0) {

			Object o = ls.remove(R.nextInt(ls.size()));

			all.add(o);
		}
	}

	public static int getRandomInt(int min, int max) {

		int n = max - min + 1;

		return R.nextInt(n) + min;
	}

	public static double getRandomDouble(double min, double max) {
		return R.nextDouble() * (max - min) + min;
	}

	public static int getMax(Collection<Integer> allKey) {

		int max = Integer.MIN_VALUE;

		for (Integer value : allKey) {

			if (value > max) {

				max = value;
			}
		}

		return max;
	}

	public static Properties getProperties(String fileName) {

		Properties p = new Properties();

		try {

			p.load(new FileInputStream(new File(fileName)));

		} catch (Exception e) {

			throw new RuntimeException(e);
		}

		return p;

	}

	public static <T> List<T> merge(List<T> a, List<T> b) {

		List<T> ls = new ArrayList<T>();

		ls.addAll(a);

		ls.addAll(b);

		return ls;
	}

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
