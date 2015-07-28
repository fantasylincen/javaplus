package lincen.javame.util;


/**
 * ͵ת
 * @author 
 */
class TypeConvert {
	
	/**
	 * 㵽ֽת
	 * @param d
	 * @return
	 */
	static byte[] floatToByte(float d) {
		byte[] b = new byte[8];
		int l = Float.floatToIntBits(d);
		for (int i = 0; i < b.length; i++) {
			b[i] = new Integer(l).byteValue();
			l = l >> 8;
		}
		return b;
	}

	/**
	 * ֽڵת
	 * @param b
	 * @return
	 */
	static float byteToFloat(byte[] b) {
		int l;

		l = b[0];
		l &= 0xff;
		l |= ((int) b[1] << 8);
		l &= 0xffff;
		l |= ((int) b[2] << 16);
		l &= 0xffffff;
		l |= ((int) b[3] << 24);
		l &= 0xffffffffl;
		l |= ((int) b[4] << 32);
		l &= 0xffffffffffl;

		l |= ((int) b[5] << 40);
		l &= 0xffffffffffffl;
		l |= ((int) b[6] << 48);

		l |= ((int) b[7] << 56);
		return Float.intBitsToFloat(l);
	}
}
