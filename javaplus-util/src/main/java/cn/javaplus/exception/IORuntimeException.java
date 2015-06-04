package cn.javaplus.exception;

import java.io.IOException;

public class IORuntimeException extends RuntimeException {

	public IORuntimeException(IOException e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6339946461667097984L;

}
