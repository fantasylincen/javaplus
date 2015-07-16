package org.hhhhhh.guess;

public class ProphetExceptionAdaptor {

	private final ProphetException e;

	public ProphetExceptionAdaptor(ProphetException e) {
		this.e = e;
	}

	public int getRet() {
		return e.getRet();
	}

	public String getMessage() {
		return e.getMessage();
	}

}
