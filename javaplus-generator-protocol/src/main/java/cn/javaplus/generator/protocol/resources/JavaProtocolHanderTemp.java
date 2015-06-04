package cn.javaplus.generator.protocol.resources;

import com.google.common.io.Resources;

import cn.javaplus.common.util.Util;
import cn.javaplus.generator.protocol.generator.util.Templet;

public class JavaProtocolHanderTemp {

	public static String getContent() {
		return Util.File.getContent(Resources.getResource("JavaProtocolHandler.temp"));
	}

	public static Templet getTemplet() {
		return new Templet(getContent());
	}
	
}
