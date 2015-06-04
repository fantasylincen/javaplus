package cn.mxz.dogz;


public interface IPracticeBean {

	public abstract int getTypeId();

	public abstract void setTypeId(int typeId);

	public abstract int getMode();

	public abstract void setModel(int mode);

	public abstract int getTime();

	public abstract void setTime(int time);

	/**
	 * @return the endTime
	 */
	public abstract long getEndTime();

	/**
	 * @param endTime the endTime to set
	 */
	public abstract void setEndTime(long endTime);

	public abstract int getIndex();

	public abstract void setIndex(int index);

	/**
	 * 修炼时间到了
	 */
	public abstract boolean isTimeUp();

	/**
	 * @return the uname
	 */
	public abstract String getUname();
	
	/**
	 * 修炼结束剩余时间(秒)
	 */
	int getRemainingSec();

}