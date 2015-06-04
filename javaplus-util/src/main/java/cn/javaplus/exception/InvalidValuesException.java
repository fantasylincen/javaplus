package cn.javaplus.exception;

/**
 * 无效数值异常
 */
public class InvalidValuesException extends RuntimeException {
	private static final long serialVersionUID = -1934812936474724673L;

	private int number;
	public InvalidValuesException(int number) {
		super(number + "是一个无效数值!");
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}
