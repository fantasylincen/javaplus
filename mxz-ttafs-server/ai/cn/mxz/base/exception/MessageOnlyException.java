package cn.mxz.base.exception;

public class MessageOnlyException extends OperationFaildException {

	private static final long	serialVersionUID	= -4124761777277865703L;

	public MessageOnlyException(int messageCode, Object... info) {
		super(messageCode, info);
	}

}
