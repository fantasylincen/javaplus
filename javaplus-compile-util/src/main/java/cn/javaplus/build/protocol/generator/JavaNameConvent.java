package cn.javaplus.build.protocol.generator;

import cn.javaplus.util.Util;

public class JavaNameConvent {

	public String getJavaName(String fileName) {
		fileName = fileName.replaceAll("/", "_");
		fileName = fileName.replaceAll("\\-", "_");
		fileName = fileName.replaceAll("\\.", "_");
		fileName = Util.Str.hump(fileName);
		fileName = Util.Str.firstToUpperCase(fileName);
		if (fileName.substring(0, 1).matches("[0-9]")) {
			fileName = "_" + fileName;
		}
		return fileName;
	}
}
