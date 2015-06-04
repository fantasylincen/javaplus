package cn.mxz.base.server;


/**
 * 可接受数据的对象
 * @author 	林岑
 * @time	2013年5月27日 16:10:31
 *
 */

public interface ReceiveDataAble {

	/**
	 * 当某个command请求到来时, 执行该方法, query请求 不执行该方法
	 */
	void onCommand();
}
