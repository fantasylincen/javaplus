package cn.mxz.base.exception;

/**
 *
 * 确定是客户端 非法操作  的异常
 * @author 	林岑
 * @since	2013年5月27日 17:31:03
 *
 */
public class SureIllegalOperationException extends ClientException {

	private static final long serialVersionUID = -2579375612213978015L;
	private String	message;

	public SureIllegalOperationException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return "2|" + message;
	}

	public String getText() {
		return message;
	}

}
