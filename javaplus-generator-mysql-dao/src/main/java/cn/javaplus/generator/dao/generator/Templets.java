package cn.javaplus.generator.dao.generator;

import com.google.common.io.Resources;

import cn.javaplus.util.Util;

public class Templets {

	public static String get(String fileName) {
		return Util.File.getContent(Resources.getResource("temp/" + fileName));
	}

}
