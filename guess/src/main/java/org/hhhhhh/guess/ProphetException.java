package org.hhhhhh.guess;

public class ProphetException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8254184983369061530L;

	private int ret;

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public ProphetException(int ret) {
		this.ret = ret;
	}

	public ProphetException() {
	}

	@Override
	public String getMessage() {
		String message = super.getMessage();
		if (message == null)
			return "";
		return message;
	}

	public ProphetException(int ret, String message) {
		super(message);
		this.ret = ret;
	}
}
