package cn.mxz.base.task;

/**
 * 任务异常
 * @author 林岑
 *
 */
class TaskException extends Exception {

	private static final long	serialVersionUID	= -4247615669880615831L;

	public TaskException() {
		super();
	}

	TaskException(String string) {
		super(string);
	}

}
