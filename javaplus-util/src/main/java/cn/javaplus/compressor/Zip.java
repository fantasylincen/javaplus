package cn.javaplus.compressor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Zip implements Compressor {

	@Override
	public byte[] compress(byte[] data) {

		byte[] b = null;

		try {

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			ZipOutputStream zip = new ZipOutputStream(bos);

			ZipEntry entry = new ZipEntry("zip");

			entry.setSize(data.length);

			zip.putNextEntry(entry);

			zip.write(data);

			zip.closeEntry();

			zip.close();

			b = bos.toByteArray();

			bos.close();

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return b;
	}

	@Override
	public byte[] unCompress(byte[] data) {

		byte[] b = null;

		try {

			ByteArrayInputStream bis = new ByteArrayInputStream(data);

			ZipInputStream zip = new ZipInputStream(bis);

			while (zip.getNextEntry() != null) {

				byte[] buf = new byte[1024];

				int num = -1;

				ByteArrayOutputStream baos = new ByteArrayOutputStream();

				while ((num = zip.read(buf, 0, buf.length)) != -1) {

					baos.write(buf, 0, num);
				}

				b = baos.toByteArray();

				baos.flush();

				baos.close();
			}

			zip.close();

			bis.close();

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return b;
	}
}
