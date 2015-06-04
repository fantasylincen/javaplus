package db.domain;public interface UserGps extends Domain , 
				UserItem
			{	String getUname();	void setUname(String uname);void addUname(String uname);	int getLongitude();	void setLongitude(int longitude);void addLongitude(int longitude);	int getLatitude();	void setLatitude(int latitude);void addLatitude(int latitude);}