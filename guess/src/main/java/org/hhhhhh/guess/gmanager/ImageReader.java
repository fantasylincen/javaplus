package org.hhhhhh.guess.gmanager;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

import cn.javaplus.util.Closer;

public class ImageReader {

	public byte[] read(File img) {

		FileInputStream fis = null;
		try {

			fis = new FileInputStream(img);
			ByteBuffer bf = ByteBuffer.allocate(10240000);

			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {

				bf.put(Arrays.copyOf(buffer, len));
			}
			bf.flip();

			byte[] copyOf = Arrays.copyOf(bf.array(), bf.limit());

			return copyOf;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(fis);
		}

	}

}
