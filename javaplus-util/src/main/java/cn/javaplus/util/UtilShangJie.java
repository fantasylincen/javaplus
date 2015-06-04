package cn.javaplus.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilShangJie {

	/**
	 * 将字符串oo 以f作为分隔符, 分隔为一个整型数组
	 *
	 * @param oo
	 * @param f
	 * @return
	 */
	public static int[] toIntArray(String oo, String f) {

		if (oo == null || oo.trim().isEmpty()) {

			return new int[0];
		}

		String[] split = oo.split(f);

		int[] a = new int[split.length];

		for (int i = 0; i < a.length; i++) {

			try {
				a[i] = Integer.parseInt(split[i]);

			} catch (NumberFormatException e) {

				System.err.println("Util.toIntArray() 出错的值:" + oo + " , " + f);

				throw e;
			}
		}

		return a;
	}

	public static int byteToInt(byte b1, byte b2, byte b3, byte b4) {
		int intValue = 0;

		intValue |= (b1 & 0xff) << 24;
		intValue |= (b2 & 0xff) << 16;
		intValue |= (b3 & 0xff) << 8;
		intValue |= (b4 & 0xff);

		return intValue;
	}

	public static short byteToShort(byte b1, byte b2) {
		short value = 0;

		value |= (b1 & 0xff) << 8;
		value |= (b2 & 0xff);

		return value;
	}

	public static String setStringLength(String str, int length) {
		byte[] read = new byte[length];
		for (int i = 0; i < read.length; i++) {
			read[i] = 32;
		}

		byte[] temp = str.getBytes();

		if (temp.length > length) {
			for (int i = 0; i < length; i++) {
				read[i] = temp[i];
			}
		} else {
			System.arraycopy(temp, 0, read, 0, temp.length);
		}
		return new String(read);
	}

	/**
	 * 将时间转换成字符串形式
	 *
	 * @param time
	 * @return
	 */
	public static String parseToString(long time) {

		Date date = new Date(time);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return format.format(date);
	}

	public UtilShangJie() {
		super();
	}

}