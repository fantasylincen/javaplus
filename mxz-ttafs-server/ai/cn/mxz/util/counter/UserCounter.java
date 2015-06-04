package cn.mxz.util.counter;

/**
 *
 * 用户今日各种操作次数 计数器, 该计数器在12点时清零
 *
 * @author 林岑
 *
 */
public interface UserCounter extends Marker, UserCounterSetter {

	/**
	 * 获得指定ID的计数器值
	 * @param id	计数器ID
	 * @param args	未计数器ID而增加的参数列表, 比如   id = USER_XXX  args = lc, 1  那么存入数据库的ID为 : USER_XXXlc1
	 * @return
	 */
	int get(CounterKey id, Object... args);
}
