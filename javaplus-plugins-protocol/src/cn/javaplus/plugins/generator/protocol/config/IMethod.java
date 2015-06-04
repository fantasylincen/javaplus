package cn.javaplus.plugins.generator.protocol.config;

import java.util.List;

public interface IMethod {

	long getId();

	List<IField> getArgs();

	String getName();

	IReturn getReturn();

	String getMethodDoc();

	List<MethodBack> getBacks();

	/**
	 * 压缩方式
	 * 
	 * @return "zip" | "gzip"
	 */
	String getCompressType();
}
