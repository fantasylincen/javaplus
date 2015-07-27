package cn.javaplus.game.shhz.assets;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import com.google.common.io.Resources;

import cn.javaplus.game.shhz.Templet;
import cn.javaplus.shhz.string.StringPrinter;
import cn.javaplus.shhz.util.Util;

public class AssetsLoaderGenerator {

	private static final String ASSETS_MARK = ".*/assets/";

	public void generate() {
		List<File> files = getAllPngFiles();
		Templet temp = new Templet("AssetsLoader.temp");

		appendLoad(files, temp);
		appendOtherLoadMethod(temp);
		temp.writeToFile("../core/gen/cn/javaplus/game/shhz/AssetsLoader.java");
	}

	private void appendOtherLoadMethod(Templet temp) {
		List<File> files = Util.File.getFiles(Resources
				.getResource("assetsload"));
		for (File file : files) {
			temp.append("OTHER_LOAD_METHOD", buildOtherLoadMethod(file));
		}
	}

	private String buildOtherLoadMethod(File file) {
		String methodName = file.getName();
		List<String> lines = Util.File.getLines(file);
		StringPrinter sp = new StringPrinter();
		sp.println("	public static void " + methodName + " () {");
		for (String string : lines) {

			if (string.trim().isEmpty()) {
				continue;
			}
			string = parsePath(string);
			sp.println(buildLoad(string));
		}
		sp.println("	}");
		return sp.toString();
	}

	private String parsePath(String string) {
		string = string.replaceAll("\\\\", "/");
		string = string.replaceAll(ASSETS_MARK, "");
		return string;
	}

	private String buildLoad(String type) {

		String methodName;

		if (type.endsWith(".plist")) {
			methodName = "loadPlist";
		} else if (type.endsWith(".fnt")) {
			methodName = "loadBitmapFont";
		} else {
			methodName = "loadTexture";
		}
		return "		Assets." + methodName + "(\"" + type + "\");";
	}

	private void appendLoad(List<File> files, Templet temp) {
		for (File file : files) {
			try {
				String path = file.getCanonicalPath();
				path = parsePath(path);
				temp.append("LOADS", buildLoad(path));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}

	private List<File> getAllPngFiles() {
		List<File> files = Util.File.getFiles("../android/assets");
		Iterator<File> it = files.iterator();
		while (it.hasNext()) {
			File next = it.next();
			if (!next.getName().endsWith(".png")) {
				it.remove();
			}
		}
		return files;
	}

}
