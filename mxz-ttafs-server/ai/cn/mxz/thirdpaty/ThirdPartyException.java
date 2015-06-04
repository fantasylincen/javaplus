package cn.mxz.thirdpaty;

import cn.javaplus.log.Debuger;

import com.linekong.platform.protocol.erating.ErrorCode;

/**
 * 第三方平台异常
 *
 * @author 林岑
 *
 */
public class ThirdPartyException extends RuntimeException {

	private int		errorCode;
	private String	message;

	public ThirdPartyException(int errorCode) {
		this.errorCode = errorCode;
		Debuger.debug("ThirdPartyException 构造第三方错误信息:" + errorCode);
	}

	public ThirdPartyException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		if (message != null) {
			return message;
		}
		return ErrorCode.toString(errorCode) + "  (" + errorCode + ")";
	}

	/**
	 *
	 */
	private static final long	serialVersionUID	= 5802153044797352093L;

	public int getErrorCode() {
		return errorCode;
	}

}
