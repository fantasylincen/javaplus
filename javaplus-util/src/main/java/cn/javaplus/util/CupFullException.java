package cn.javaplus.util;

/**
 * 杯子装满异常
 * @author 	林岑
 * @since	2013年1月6日 19:53:49
 *
 */
public class CupFullException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CupFullException(String message) {
		super(message);
	}

}
