package cn.javaplus.game.shhz.assets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import cn.javaplus.game.shhz.Templet;
import cn.javaplus.game.shhz.components.ButtonsListsBuilder;
import cn.javaplus.shhz.util.Closer;
import cn.javaplus.shhz.util.Util;

import com.google.common.io.Resources;

public abstract class AbstractRGenerator {

	protected abstract String getRConfigTemp();

	protected abstract String getRConfigClassTemp();

	protected abstract String getPath();

	protected abstract String getRoot();

	public void generate() {
		List<File> files = Util.File.getFiles(getRoot());
		Properties pr = getProperties("RConfig.properties");
		Templet temp = new Templet(getRConfigTemp());
		for (File file : files) {
			String className = getClassName(file);
			String tail = getTail(file);
			String type = pr.getProperty(tail);
			if (type == null) {
				type = pr.getProperty("default");
			}
			temp.append("CLASSS", buildClass(className, type, file));
		}
		temp.set("TEXTURE_SHAPES", new TextureShapesBuilder().build());
		temp.set("BUTTONS_LISTS", new ButtonsListsBuilder().build());

		temp.writeToFile(getPath());
	}

	protected String buildClass(String className, String type, File file) {
		Templet temp = new Templet(getRConfigClassTemp());
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
		className = className.replaceFirst(getRoot(), "");
		return className;
	}

}