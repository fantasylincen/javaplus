package cn.mxz.base.exception;

class ExceptionMail {

	private Throwable	exception;
	private String	mailPath;

	ExceptionMail(Throwable e, String mailPath) {
		this.exception = e;
		this.mailPath = mailPath;
	}

	public Throwable getException() {
		return exception;
	}

	public String getMailPath() {
		return mailPath;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public void setMailPath(String mailPath) {
		this.mailPath = mailPath;
	}


}
