package cn.javaplus.commons.socket;

import java.net.BindException;

public class BindRuntimeException extends RuntimeException {

	public BindRuntimeException(BindException e) {
		super(e);
	}

	/**
	 *
	 */
	private static final long	serialVersionUID	= -1556082676751265794L;

}
