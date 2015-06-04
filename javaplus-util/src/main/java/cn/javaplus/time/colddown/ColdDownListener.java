package cn.javaplus.time.colddown;

/**
 * CD时间清空时
 * @author 林岑
 *
 */
public interface ColdDownListener {

	/**
	 * cd清空时调用
	 */
	void onClear();

	/**
	 * cd增加时调用
	 */
	void onAdd();

	/**
	 * cd设置时调用
	 */
	void onSet();

}
