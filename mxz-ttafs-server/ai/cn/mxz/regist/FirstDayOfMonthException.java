package cn.mxz.regist;

/**
 *
 * 在本月的第一天
 * @author 林岑
 *
 */

public class FirstDayOfMonthException extends RuntimeException {

	public FirstDayOfMonthException(String s) {
		super(s);
	}

	private static final long serialVersionUID = -7928014698520596713L;

}
