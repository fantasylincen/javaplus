package cn.javaplus.time.colddown;


/**
 * 强制冷却时间
 *
 * 每次调用 add 方法会指定增长一段时间(比如增长10秒钟)
 *
 * 当增长到指定长的时间之后, 就不能操作了(isFreezing == true) 比如100秒钟的时候就不能操作了
 *
 * (也就是说这个冷却时间最多允许玩家一次性连续add 10次)
 *
 * 冷却时间的累计时间会随着时间的流失而逐渐减少
 *
 * @author 林岑
 * @since 2013年2月16日 16:34:53
 */
public interface ColdDown {

	/**
	 * 是否处于结冰状态?
	 * 如果是, 就不可进行某种操作
	 */
	boolean isFreezing();

	/**
	 * 让冷却时间累加一次
	 *
	 * 如果 isFreezing == true  那么调用该方法的时候, 会抛出异常:正在冷却
	 *
	 */
	void add();

	/**
	 * 清空CD
	 */
	void clear();

	/**
	 * CD结束时间
	 */
	long getEndTime();

	/**
	 * 剩余秒
	 */
	int getRemainingSec();

	/**
	 * 剩余分(不足1分按1分算)
	 */
	int getRemainingMin();

	/**
	 * 冻结时间
	 * @return
	 */
	long getTimeFreezing();

	/**
	 * CD侦听器
	 * @param listener
	 */
	void addListener(ColdDownListener listener);

	/**
	 * 检查冷却事件是否到了
	 */
	void check();
}
