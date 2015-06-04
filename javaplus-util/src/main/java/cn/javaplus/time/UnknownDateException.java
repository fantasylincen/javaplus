package cn.javaplus.time;

/**
 * 无法识别的
 * @author 林岑
 *
 */
public class UnknownDateException extends RuntimeException {

	public UnknownDateException(String time) {
		super(time);
	}

	public UnknownDateException() {
	}
	
	private static final long	serialVersionUID	= 1643787447199897109L;

}
