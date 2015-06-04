package cn.mxz.base.exception;


/**
 * 一个临时异常
 * @author 林岑
 *
 */
public class IllegalOperationException extends SureIllegalOperationException {

	private static final long	serialVersionUID	= 3750853953508527098L;

	public IllegalOperationException(String message) {
		super(message);
	}

}
