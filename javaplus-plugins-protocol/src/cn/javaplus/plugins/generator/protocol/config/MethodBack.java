package cn.javaplus.plugins.generator.protocol.config;

import java.util.List;

/**
 * 
 * 方法执行后返回给客户端的其他数据
 * 
 * @author 林岑
 * 
 */
public interface MethodBack {

	IInterface getClazz();

	IMethod getMethod();

	/**
	 * 方法返回所需参数
	 * 
	 * @return
	 */
	List<String> getArgs();
}
