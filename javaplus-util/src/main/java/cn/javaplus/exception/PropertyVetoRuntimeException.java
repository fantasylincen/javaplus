package cn.javaplus.exception;

import java.beans.PropertyVetoException;

public class PropertyVetoRuntimeException extends RuntimeException {

	/**
	 *
	 */
	private static final long	serialVersionUID	= -1849753997502539201L;

	public PropertyVetoRuntimeException(PropertyVetoException e) {
		super(e);
	}

}
