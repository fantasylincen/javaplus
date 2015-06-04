package cn.javaplus.plugins.generator.excel.generator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import cn.javaplus.plugins.generator.excel.preferences.D;
import cn.javaplus.util.Util;

public class XMLLoader {

	private static Document	read;

	private static Map<String, Long> record = new HashMap<String, Long>();;

	public XMLLoader() {

	}

	public static Document getDoc() {

		if (isTimeUp() || read == null) {

			String path = getFilePath();

			File inputXml = new File(path);

			record.put(path, inputXml.lastModified());

			SAXReader saxReader = new SAXReader();

			try {

				read = saxReader.read(inputXml);
				return read;
			} catch (Exception e) {

				if (path.isEmpty()) {

					throw new ConfigNotFoundException("Config file not found! please assign it in perferance page! (External config path) The XML example please see generator_for_plugin.xml");
				}

				throw Util.Exception.toRuntimeException("file: [" + path + "] format error!", e);
			}
		}



		return read;
	}

	private static boolean isTimeUp() {
		String filePath = getFilePath();
		File file = new File(filePath);
		long lastModified = file.lastModified();
		Long time = record.get(filePath);

		if(time == null) {
			record.put(filePath, lastModified);
			return true;
		}

		return lastModified != time;
	}

	private static String getFilePath() {

		String string = Store.getString(D.Paths.INTERFACE_CONFIG + "");
		return string;
	}

}
