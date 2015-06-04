package cn.javaplus.comunication;

public interface OnDataListener<U extends ProtocolUser> {

	/**
	 * 数据处理前 执行
	 */
	void beforeOnData(OnDataEvent<U> e);

	/**
	 * 数据处理成功后 执行 未报异常的情况下
 	 */
	void afterOnData(OnDataEvent<U> e);

}
