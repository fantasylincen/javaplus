package cn.mxz.base.exception;

import cn.mxz.city.City;



/**
 * 异常处理器
 * @author 林岑
 * @since	2013年6月1日 23:00:54
 *
 */
public interface ExceptionHandler {

	/**
	 * 处理这个异常
	 * @param user
	 * @throws Throwable
	 */
	public abstract void process(Throwable e, City user) ;

	/**
	 * 过滤这个异常,把不必要的信息过滤掉
	 * @param e
	 */
	public abstract void filter(Throwable e);

}
