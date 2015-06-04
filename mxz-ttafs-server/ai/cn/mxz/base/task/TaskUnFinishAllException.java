package cn.mxz.base.task;

public class TaskUnFinishAllException extends TaskException {

	private int	id;

	TaskUnFinishAllException(int id) {

		super("id = " + id);
		this.id = id;
	}

	private static final long	serialVersionUID	= 9031626712832452379L;


	public int getId() {
		return id;
	}
}
