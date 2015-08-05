package cn.javaplus.common.util;

import java.lang.reflect.InvocationTargetException;

public class InvocationTargetRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2260268857782334549L;

	public InvocationTargetRuntimeException(InvocationTargetException e) {
		super(e);
	}

}
