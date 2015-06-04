package cn.javaplus.comunication;


/**
 * 客户端请求
 * @author 林岑
 * @since	2014年2月15日 23:21:54
 *
 */
public interface Request {

	/**
	 * 请求哪个类
	 * @return
	 */
	String getClassName();

	/**
	 * 请求哪个方法
	 * @return
	 */
	String getMethodName();

	/**
	 * 参数列表
	 * @return
	 */
	Object[] getArgsArray();
}
