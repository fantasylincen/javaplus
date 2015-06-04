package cn.mxz.city;

public class NotInitOverException extends RuntimeException {

	/**
	 *
	 */
	private static final long	serialVersionUID	= -3553477392594903027L;

	public NotInitOverException() {
		super("模块初始化没有完成前, 不要访问这个模块!");
	}
}
