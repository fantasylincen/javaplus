package db.domain;

public interface DBCounter {

	void setCount(int count);

	int getCount();

	void setCounterId(String id);

	void setUname(String uname);

	String getUname();

	String getCounterId();
}
