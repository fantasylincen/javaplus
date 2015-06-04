package cn.javaplus.exception;

import java.net.UnknownHostException;

public class UnknownHostRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2073586550234285023L;

	public UnknownHostRuntimeException(UnknownHostException e) {
		super(e);
	}

}
