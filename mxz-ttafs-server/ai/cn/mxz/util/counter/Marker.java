package cn.mxz.util.counter;

public interface Marker {

	/**
	 * 是否被标记过
	 * @param id
	 * @param args	未计数器ID而增加的参数列表, 比如   id = USER_XXX  args = lc, 1  那么存入数据库的ID为 : USER_XXXlc1
	 * @return
	 */
	boolean isMark(CounterKey id, Object... args);

	/**
	 * 标记			(标记之后, 这个计数器值为1)
	 * @param id
	 * @param args	未计数器ID而增加的参数列表, 比如   id = USER_XXX  args = lc, 1  那么存入数据库的ID为 : USER_XXXlc1
	 */
	void mark(CounterKey id, Object... args);

}