package cn.javaplus.plugins.generator.protocol.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import cn.javaplus.plugins.console.Debuger;
import _util.Closer;
import _util.Util;

public class FileUtilImpl implements FileUtil {

	private File	file;

	public FileUtilImpl(String filePath) {
		this.file = new File(filePath);

		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
	}

	@Override
	public void writeToFile(String content) {
		OutputStreamWriter ow = null;

		try {

			if (!file.exists()) {
				file.createNewFile();
			}
			ow = new OutputStreamWriter(new FileOutputStream(file), "utf8");
			ow.write(content);
			ow.flush();

		} catch (Exception e) {
			Debuger.err(content);
			throw Util.toRuntimeException(e);
		} finally {
			Closer.close(ow);
		}
	}

}
