package cn.mxz;

import java.io.File;
import java.util.Set;

import com.google.common.collect.Sets;

public class DeleteAllTempletGenerate {

	private static File	file  = new File("D:\\workspace\\MobileServer\\gen\\cn\\mxz");

	private static Set<String> all = Sets.newHashSet();

	public static void main(String[] args) {

		File[] ls = file.listFiles();
		addAllName(file.list());
		for (File file : ls) {
			if(isTempletFile(file)) {
				System.out.println(file);
				file.delete();
			}
		}
	}

	private static void addAllName(String[] list) {
		for (String string : list) {
			all.add(string);
		}
	}

	private static boolean isTempletFile(File f) {
		if(f.isDirectory()) {
			return false;
		}

		return f.getName().endsWith("TempletConfig.java");
	}



}
