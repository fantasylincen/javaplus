package db.domain;public interface UserJunket extends Domain , 
				UserItem
			, 
				DBBag
			{	String getUname();	void setUname(String uname);void addUname(String uname);	int getCapacity();	void setCapacity(int capacity);void addCapacity(int capacity);}