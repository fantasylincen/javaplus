package cn.javaplus.crazy.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

public class Closer {

	public static void close(Closeable b) {
		if (b != null)
			try {
				b.close();
			} catch (IOException e) {
			}
	}

	public static void close(OutputStream b) {
		if (b != null)
			try {
				b.close();
			} catch (IOException e) {
			}
	}

}
