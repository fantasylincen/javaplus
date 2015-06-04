package cn.mxz.base.exception;

/**
 * 
 * 非法协议异常
 * @author 	林岑
 * @since	2013年5月29日 09:35:02
 *
 */
public class IllegalProtocolException extends ClientException {

	private static final long serialVersionUID = -5647546669364876587L;

	public IllegalProtocolException() {
	}
	
	
	public IllegalProtocolException(String m) {
		super(m);
	}
	
	@Override
	public String getMessage() {
		return "2|Please Update ProtocolSDK!";
	}
}
