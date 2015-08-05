package org.javaplus.game.common.http;

/**
 * HTTP 请求
 * 
 * @author 林岑
 * 
 */
public interface Request {

	/**
	 * URL
	 * 
	 * @return
	 */
	String getUrl();

	/**
	 * 参数列表
	 * 
	 * @return
	 */
	Parameters getParameters();

}
