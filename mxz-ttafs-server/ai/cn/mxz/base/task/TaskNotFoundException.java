package cn.mxz.base.task;

public class TaskNotFoundException extends TaskException {

	private static final long	serialVersionUID	= -3607123947718900878L;

	private int	id;

	TaskNotFoundException(int id) {

		super("id = " + id);

		this.id = id;
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	public int getId() {
		return id;
	}
}
