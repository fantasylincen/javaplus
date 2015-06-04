package cn.javaplus.build.protocol.generator;

import java.io.File;
import java.util.HashSet;

import cn.javaplus.collections.counter.Counter;
import cn.javaplus.file.Templet;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

import com.google.common.collect.Sets;

public class AltasBuilder {
	private static final String TAIL = ".txt";
	private static final String PATH = "../../client/ui/Resources/";

	public String generate() {
		Templet t = Templet.createByFilePath("AltasBuilder.temp");

		File file = new File(PATH);
		File[] listFiles = file.listFiles();
		if (listFiles != null)
			for (File f : listFiles) {
				if (f.getName().endsWith(TAIL)) {
					t.append("CLASSES", buildClass(f));
				}
			}

		return t.toString();
	}

	private String buildClass(File f) {

		String name = f.getName();
		String[] split = name.split("\\.");
		name = split[0];
		StringPrinter out = new StringPrinter();
		out.println("		public static " + name + "Altas get"
				+ Util.Str.firstToUpperCase(name) + " () {");
		out.println("			return new " + name + "Altas();");
		out.println("		}");
		out.println("		public static class " + name + "Altas {");
		printGetters(out, f, name);
		out.println("			public void load() {");
		out.println("				Assets.loadTextureAtlas(\"" + name + TAIL + "\");");
		out.println("			}");
		out.println("			public void unload() {");
		out.println("				Assets.unload(\"" + name + TAIL + "\");");
		out.println("			}");
		out.println("			public TextureAtlas getAltas() {");
		out.println("				return Assets.getTextureAtlas(\"" + name + TAIL
				+ "\");");
		out.println("			}");
		out.println("		}");

		return out.toString();
	}

	private void printGetters(StringPrinter out, File f, String name) {
		String content = Util.File.getContent(f);
		content = content.replaceAll(".+:.+", "");
		content = content.replaceFirst(".+\\..+", "");
		content = content.replaceAll("\r{2,1000}", "\r");
		String[] split = content.split("\r");
		Counter<String> counter = counterAnimation(split);

		HashSet<String> set = Sets.newHashSet(split);

		for (String string : set) {
			if (!string.isEmpty()) {
				int count = counter.get(string);
				boolean isAnimation = count > 1;
				if (isAnimation) {
					printGetterAnimation(out, string, name);
				} else {
					printGetter(out, string, name);
				}
			}
		}
	}

	private void printGetterAnimation(StringPrinter out, String textureName,
			String textureAltasName) {
		String getName = Util.Str.hump(textureName);
		getName = Util.Str.firstToUpperCase(getName);
		out.println("			public final Array<AtlasRegion> get" + getName + "() {");
		out.println(" 				return getAltas().findRegions(\"" + textureName + "\");");
		out.println("			}");
	}

	private Counter<String> counterAnimation(String[] split) {
		Counter<String> counter = new Counter<String>();
		for (String string : split) {
			if (!string.isEmpty()) {
				counter.add(string);
			}
		}
		return counter;
	}

	private void printGetter(StringPrinter out, String textureName,
			String textureAltasName) {
		String getName = Util.Str.hump(textureName);
		getName = Util.Str.firstToUpperCase(getName);
		out.println("			public AtlasRegion get" + getName + "() {");
		out.println(" 				return getAltas().findRegion(\"" + textureName + "\");");
		out.println("			}");
	}
}
