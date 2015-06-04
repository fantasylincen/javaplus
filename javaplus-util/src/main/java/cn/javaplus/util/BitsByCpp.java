package cn.javaplus.util;
/**
 * 
 * C++ 平台写入的字节流  用这个类读取
 * 如果是用Java平台写入的 字节流, 用BitsByJava读取
 */
public class BitsByCpp {

	public static boolean getBoolean(byte[] b, int off) {
		return b[off] != 0;
	}

	public static char getChar(byte[] b, int off) {
		return (char) ((b[off ] & 0xFF) + (b[off + 1] << 8));
	}

	public static short getShort(byte[] b, int off) {
		return (short) ((b[off] & 0xFF) + (b[off + 1] << 8));
	}

	public static int getInt(byte[] b, int off) {
		return ((b[off ] & 0xFF)) + ((b[off + 1] & 0xFF) << 8)
				+ ((b[off + 2] & 0xFF) << 16) + ((b[off + 3]) << 24);
	}

	public static float getFloat(byte[] b, int off) {
		return Float.intBitsToFloat(getInt(b, off));
	}

	public static long getLong(byte[] b, int off) {
		return ((b[off + 0] & 0xFFL)) + ((b[off + 1] & 0xFFL) << 8)
				+ ((b[off + 2] & 0xFFL) << 16) + ((b[off + 3] & 0xFFL) << 24)
				+ ((b[off + 4] & 0xFFL) << 32) + ((b[off + 5] & 0xFFL) << 40)
				+ ((b[off + 6] & 0xFFL) << 48) + (((long) b[off + 7]) << 56);
	}

	public static double getDouble(byte[] b, int off) {
		return Double.longBitsToDouble(getLong(b, off));
	}
}
