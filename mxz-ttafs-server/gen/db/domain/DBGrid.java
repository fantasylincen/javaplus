package db.domain;

public interface DBGrid {

	public abstract String tableName();

	public abstract void setUname(String uname);

	public abstract String getUname();

	public abstract void addUname(String uname);

	public abstract void setGridId(int gridId);

	public abstract int getGridId();

	public abstract void addGridId(int gridId);

	public abstract void setTypeid(int typeid);

	public abstract int getTypeid();

	public abstract void addTypeid(int typeid);

	public abstract void setCount(int count);

	public abstract int getCount();

	public abstract void addCount(int count);

}