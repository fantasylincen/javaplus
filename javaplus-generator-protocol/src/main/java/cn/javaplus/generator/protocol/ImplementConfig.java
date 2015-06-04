package cn.javaplus.generator.protocol;

import java.util.Map;

import cn.javaplus.java.JavaFile;

import com.google.common.collect.Maps;

public class ImplementConfig {

	/**
	 * <接口，实现>
	 */
	static Map<JavaFile, JavaFile> impl = Maps.newHashMap();
	/**
	 * 通过接口， 获得他的实现
	 * @param interfacee
	 * @return
	 */
	public static JavaFile get(JavaFile interfacee) {
		return impl.get(interfacee);
	}
	public static void put(JavaFile interfacee, JavaFile im) {
		impl.put(interfacee, im);
	}

}
