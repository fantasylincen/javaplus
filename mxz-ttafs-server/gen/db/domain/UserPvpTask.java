package db.domain;public interface UserPvpTask extends Domain ,
				UserItem
			,
				TaskDTO
			{	String getUname();	void setUname(String uname);void addUname(String uname);	int getTaskId();	void setTaskId(int taskId);void addTaskId(int taskId);	int getFinishtimes();	void setFinishtimes(short finishtimes);void addFinishtimes(short finishtimes);	boolean getIsdraw();	void setIsdraw(boolean isdraw);}