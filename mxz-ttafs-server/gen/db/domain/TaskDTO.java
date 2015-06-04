package db.domain;

public interface TaskDTO {
	public void setUname(String uname);

	public String getUname();

	public void setTaskId(int taskId);

	public int getTaskId();

	public void setFinishtimes(int finishtimes);

	public int getFinishtimes();

	public void setIsDraw(boolean isdraw);

	public boolean getIsDraw();
}
