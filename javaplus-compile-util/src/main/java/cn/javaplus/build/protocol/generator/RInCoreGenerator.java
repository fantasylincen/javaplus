package cn.javaplus.build.protocol.generator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import cn.javaplus.file.Templet;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;

import com.google.common.io.Resources;

public class RInCoreGenerator {

	private static final String CLASS_TEMP = "CoreRConfigClass.temp";
	private static final String R_PATH = "../../client/core/src/cn/javaplus/crazy/R.java";
	private static final String ROOT = "../../client/ui/Resources/";

	public void generate() {
		List<File> files = Util.File.getFiles(ROOT);
		Properties pr = getProperties("RConfig.properties");
		Templet temp = new Templet("CoreRConfig.temp");
		for (File file : files) {
			String className = getClassName(file);
			String tail = getTail(file);
			String type = pr.getProperty(tail);
			if (type == null) {
				type = pr.getProperty("default");
			}
			temp.append("CLASSS", buildClass(className, type, file));
		}
		temp.set("UI_DEFINE", new UiDefineBuilder().generate());
		temp.set("ALTAS", new AltasBuilder().generate());

		temp.writeToFile(R_PATH);
	}

	protected String buildClass(String className, String type, File file) {
		Templet temp = new Templet(CLASS_TEMP);
		temp.set("CLASS_NAME", className);
		temp.set("TYPE", type);
		temp.set("METHOD_NAME", getMethodName(type));
		String fileName = getFileName(file);
		temp.set("FILE_NAME", fileName);
		return temp.toString();
	}

	private String getMethodName(String type) {
		if (type.equals("String")) {
			return "getFileContent";
		}
		if (type.equals("Properties")) {
			return "getProp";
		}
		if (type.equals("Texture")) {
			return "Assets.getTexture";
		}
		if (type.equals("Plist")) {
			return "Assets.getPlist";
		}
		return "getFileContent";
	}

	protected Properties getProperties(String string) {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = Resources.getResource(string).openStream();
			p.load(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(is);
		}
		return p;
	}

	protected String getTail(File file) {
		String path = file.getPath();
		int lastIndexOf = path.lastIndexOf(".");
		if (lastIndexOf == -1) {
			return "";
		}
		return path.substring(lastIndexOf, path.length());
	}

	protected String getClassName(File file) {
		String fileName = getFileName(file);
		return new JavaNameConvent().getJavaName(fileName);
	}

	protected String getFileName(File file) {
		String className = file.getPath();
		className = className.replaceAll("\\\\", "/");
		className = className.replaceFirst(ROOT, "");
		return className;
	}

}
