package cn.javaplus.plugins.generator.protocol.config;

/**
 * 方法返回
 * 
 * @author 林岑
 * 
 */
public interface IReturn {

	/**
	 * 关键字
	 */
	void setKeyWord(String string);

	/**
	 * 文档说明
	 */
	void setReturnDoc(String returnDoc);

	/**
	 * 文档说明
	 */
	String getReturnDoc();

	/**
	 * 关键字
	 */
	String getType();

	/**
	 * 返回值是否为空
	 * 
	 * @return
	 */
	boolean isVoid();

	boolean isBaseType();

	String getTypeSimple();
}
