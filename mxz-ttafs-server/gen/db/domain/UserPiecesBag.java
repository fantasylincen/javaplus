package db.domain;import java.util.Arrays;public interface UserPiecesBag extends Domain , 
				UserItem
			, 
				DBBag
			{	String getUname();	void setUname(String uname);void addUname(String uname);	int getCapacity();	void setCapacity(int capacity);void addCapacity(int capacity);}