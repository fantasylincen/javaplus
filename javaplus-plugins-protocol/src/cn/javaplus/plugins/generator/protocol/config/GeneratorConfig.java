package cn.javaplus.plugins.generator.protocol.config;

import java.util.List;

public interface GeneratorConfig {

	List<IClass> getClazzs();

	String getTemplet(String string);

	/**
	 * @param path
	 *            文件路径
	 */
	FileUtil getFileUtil(String path);

	IInterface getInterface(String interfaceName);

	/**
	 * 根据 "GodPro" 获得 "cn.mxz.xxx.GodP.GodPro"
	 *
	 * @param typeSimple
	 * @return
	 */
	String getJavaPackage(String typeSimple);

	boolean contains(String typeSimple);
}
