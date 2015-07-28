package cn.javaplus.common.util;

public class IllegalArgumentRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5479777369391904796L;

	public IllegalArgumentRuntimeException(IllegalArgumentException e) {
		super(e);
	}

}
