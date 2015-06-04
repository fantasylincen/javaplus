package cn.javaplus.byteutil;

/**
 * 字节处理工具
 * 
 * @author 林岑
 * @since 2012年3月23日 14:42:19
 */
public class ByteManipulation {

	/**
	 * 将一个字符串形式的16进制表, 转换为byte数组<br>
	 * 例如 "01 A1 12 D7 C3" 这种形式的数据 , 中间可以包含空格, 也可以不包含
	 */
	public static byte[] toByte(String s) {
		if (s == null || s.equals("")) {
			return new byte[0];
		}
		s = s.replaceAll(" ", "");
		int l = s.length();
		int byteLen = l / 2;
		byte[] b = new byte[l / 2];
		for (int i = 0; i < byteLen; i++) {
			int charIndex = i * 2;
			char c1 = s.charAt(charIndex);
			char c2 = s.charAt(charIndex + 1);
			b[i] = (byte) (parse(c1) * 16 + parse(c2));
		}

		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i] + ", ");
		}
		System.out.println();
		return b;
	}

	private static byte parse(char c) {
		if (c == '0')
			return 0;
		if (c == '1')
			return 1;
		if (c == '2')
			return 2;
		if (c == '3')
			return 3;
		if (c == '4')
			return 4;
		if (c == '5')
			return 5;
		if (c == '6')
			return 6;
		if (c == '7')
			return 7;
		if (c == '8')
			return 8;
		if (c == '9')
			return 9;
		if (c == 'a' || c == 'A')
			return 10;
		if (c == 'b' || c == 'B')
			return 11;
		if (c == 'c' || c == 'C')
			return 12;
		if (c == 'd' || c == 'D')
			return 13;
		if (c == 'e' || c == 'E')
			return 14;
		if (c == 'f' || c == 'F')
			return 15;
		throw new RuntimeException("ַа޷ʶַ" + c);
	}

	/**
	 * 将status的第pos位置0
	 */
	public static byte zeroSetting(byte status, int pos) {
		byte s = (byte) ~createStatus(pos); // 取反
		return status &= s;
	}

	/**
	 * 将status的第pos位置1
	 * 
	 * @param status
	 * @param pos
	 * @return
	 */
	public static byte oneSetting(byte status, int pos) {
		return status |= createStatus(pos);
	}

	/**
	 * 判断status的第 pos 位是否为1(从左到右)
	 * 
	 * @param status
	 * @param pos
	 * @return
	 */
	public static boolean isOne(byte status, int pos) {
		// return ((status << pos) & 0x80) == 0x80; //经测试可行
		return (createStatus(pos) & status) != 0; // 经测试可行
	}

	/**
	 * 返回一个字节, 这个字节只有第pos位(从左到右(范围0-7))是1, 其他全部是0
	 * 
	 * @param pos
	 * @return
	 */
	public static byte createStatus(int pos) {
		if (pos < 0 || pos >= 8) {
			return 0;
		} else {
			return (byte) (0x80 >> pos);
		}
	}

	public static void main(String[] args) {
		// byte b = (byte) 0x83;
		// print(b);
		// print(oneSetting(b, 1));
		// print(zeroSetting(b, 0));

		// byte b = (byte) 0x80;
		// print((byte) b);
		// print((byte) ~createStatus(0));

		String s = "74 00 16 04 59 32 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 7F";
		byte[] byte1 = toByte(s);
		System.out.println(byte1.length);
		PacketProfiler p = new PacketProfiler(byte1);
		

		p.set(25, 1);
		p.set(25, 1);
		p.set(2, 1,1,1,1, 19, 1);
		
		
		System.out.println(p);
	}

	/**
	 * 打印状态值status.
	 * 
	 * @param status
	 */
	/*
	private static void print(byte status) {
		for (int i = 0; i < 8; i++) {
			System.out.print((isOne((byte) status, i) ? "1" : "0"));
		}
		System.out.print(",");
		System.out.println(Integer.toBinaryString(status));
	}
	 */
	
	public static String toHexString(byte[] array) {
		StringBuilder temp = new StringBuilder("");
		for (byte b : array) {
			temp.append(parseToHex(b));
		}
		return temp.toString();
	}

	/**
	 * 将b的值转换为16进制字符
	 * 
	 * @param b
	 * @return
	 */
	private static String parseToHex(byte b) {
		int left = b / 16;
		int right = b % 16;
		StringBuilder temp = new StringBuilder("");
		temp.append(parseToHex(left));
		temp.append(parseToHex(right));
		return temp.toString();
	}

	/**
	 * 将left转换为十六进制字符 , left的范围在 0 - 15;
	 * 
	 * @param left
	 * @return
	 */
	private static char parseToHex(int left) {
		if (left == 0)
			return '0';
		if (left == 1)
			return '1';
		if (left == 2)
			return '2';
		if (left == 3)
			return '3';
		if (left == 4)
			return '4';
		if (left == 5)
			return '5';
		if (left == 6)
			return '6';
		if (left == 7)
			return '7';
		if (left == 8)
			return '8';
		if (left == 9)
			return '9';
		if (left == 10)
			return 'A';
		if (left == 11)
			return 'B';
		if (left == 12)
			return 'C';
		if (left == 13)
			return 'D';
		if (left == 14)
			return 'E';
		if (left == 15)
			return 'F';
		else
			throw new RuntimeException("无法识别的值" + left);
	}
}
