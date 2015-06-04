package cn.mxz.util.counter;

public interface UserCounterSetter {

	/**
	 * 增加次数
	 *
	 * @param id	计数器ID
	 * @param add	增量
	 * @param args	未计数器ID而增加的参数列表, 比如   id = USER_XXX  args = lc, 1  那么存入数据库的ID为 : USER_XXXlc1
	 */
	public abstract void add(CounterKey id, int add, Object... args);

	/**
	 * 清除计数器记数
	 * @param args	未计数器ID而增加的参数列表, 比如   id = USER_XXX  args = lc, 1  那么存入数据库的ID为 : USER_XXXlc1
	 * @param id	计数器ID
	 */
	public abstract void clear(CounterKey id, Object... args);

	/**
	 * 设置计数器的值
	 * @param id
	 * @param value
	 * @param args
	 */
	public abstract void set(CounterKey id, int value, Object... args);

	/**
	 * 标记			(标记之后, 这个计数器值为1)
	 * @param id
	 * @param args	未计数器ID而增加的参数列表, 比如   id = USER_XXX  args = lc, 1  那么存入数据库的ID为 : USER_XXXlc1
	 */
	void mark(CounterKey id, Object... args);
}