package cn.javaplus.plugins.generator.protocol.config;

public interface IInterface extends IBeanAble {
	/**
	 * 获得指定名称的方法
	 * 
	 * @param m
	 * @return
	 */
	IMethod getMethod(String m);

	void addMethod(IMethod method);
}
