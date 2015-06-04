package cn.javaplus.util;

/**
 * 杯子
 * 
 * 这个杯子也可以用来比喻一个冷却时间, 
 * 杯子中的水表示剩余的冷却时间(毫秒), 如果杯子为空, 就表示冷却时间到了 
 * 
 */
public interface Cup {
	
	/**
	 * 往杯子中放入水, 如果放入水太多, 那么就会抛出
	 * @param water
	 */
	public void putWater(long water);
	public boolean isEmpty();
//	public void pour(long water);
	public long getCapacity();
	public boolean canPut(long water);
	public long getHaveNow();
}
