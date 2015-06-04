package cn.mxz.util.flow;

public interface FlowManager {

	/**
	 * 记录此次操作流量使用情况
	 * @param sizeThisTime
	 */
	void add(int sizeThisTime);

	/**
	 * 提交此次流量使用情况
	 */
	void commit();

	/**
	 * 今日流量(字节数)
	 */
	int getToday();

	/**
	 * 历史流量(字节数)
	 */
	int getHistory();

}
