package cn.mxz.fixer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class a {
	public static String toUtf8String(String s) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < s.length(); i++) {

			char c = s.charAt(i);

			if (c >= 0 && c <= 255) {

				sb.append(c);

			} else {

				byte[] b;

				try {

					b = Character.toString(c).getBytes("utf-8");

				} catch (Exception ex) {

					System.out.println(ex);

					b = new byte[0];

				}
				for (int j = 0; j < b.length; j++) {

					int k = b[j];

					if (k < 0) {

						k += 256;
					}

					sb.append("\\u" + Integer.toHexString(k).toUpperCase());
				}
			}
		}

		return sb.toString();
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		File f = new File("D:\\workspace\\前后端协议生成器\\protocols\\AU.dogz.p");

		FileInputStream fi = new FileInputStream(f);

		byte [] bs = new byte[520 * 1024];

		int read = fi.read(bs);

		byte[] copyOf = Arrays.copyOf(bs, read);

		String s = new String(copyOf);

		System.out.println(toUtf8String(s));
	}

}
