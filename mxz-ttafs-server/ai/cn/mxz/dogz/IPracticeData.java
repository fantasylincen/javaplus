package cn.mxz.dogz;


public interface IPracticeData {

	public abstract void setUname(String uname);

	public abstract String getUname();


	public abstract void setPracticeCount(int practiceCount);

	public abstract int getPracticeCount();


	public abstract void setColdDownEndTime(long coldDownEndTime);

	public abstract long getColdDownEndTime();


	public abstract void setModelId(int index, int value);

	public abstract int getModelId(int index);

	public abstract void setEndTime(int index, long value);

	public abstract long getEndTime(int index);

	public abstract void setTimeId(int index, int value);

	public abstract int getTimeId(int index);

	public abstract void setTypeId(int index, int value);

	public abstract int getTypeId(int index);

	public abstract int getTypeIdLen();


}