package cn.javaplus.compressor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZip implements Compressor {

	@Override
	public byte[] compress(byte[] data) {

		byte[] b = null;

		try {

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			GZIPOutputStream gzip = new GZIPOutputStream(bos);

			gzip.write(data);

			gzip.finish();

			gzip.close();

			b = bos.toByteArray();

			bos.close();

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return b;
	}

	public boolean isGzipFormat(byte[] data) {

		try {

			ByteArrayInputStream bis = new ByteArrayInputStream(data);

			GZIPInputStream gzip = new GZIPInputStream(bis);

			byte[] buf = new byte[1024];

			int num = -1;

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			while ((num = gzip.read(buf, 0, buf.length)) != -1) {

				baos.write(buf, 0, num);
			}

			baos.toByteArray();

			baos.flush();

			baos.close();

			gzip.close();

			bis.close();

		} catch (Exception ex) {

			return false;
		}
		return true;
	}

	@Override
	public byte[] unCompress(byte[] data) {

		byte[] b = null;

		try {

			ByteArrayInputStream bis = new ByteArrayInputStream(data);

			GZIPInputStream gzip = new GZIPInputStream(bis);

			byte[] buf = new byte[1024];

			int num = -1;

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			while ((num = gzip.read(buf, 0, buf.length)) != -1) {

				baos.write(buf, 0, num);
			}

			b = baos.toByteArray();

			baos.flush();

			baos.close();

			gzip.close();

			bis.close();

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return b;
	}
}
