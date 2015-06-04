package cn.javaplus.time;

import java.util.HashMap;
import java.util.Map;

/**
 * 休息室
 * 
 * 操作一次后, 会将T移入休息室中休息室中休息一段时间
 * 
 * 如果正在休息中调用checkTime方法会执行throwError方法
 * 
 * @author 	林岑
 * @time	2013-3-18
 */
public abstract class RestRoom<T> {

	/**
	 * 休息时长
	 */
	private long time;

	private Map<T, Long> map = new HashMap<T, Long>();

	public RestRoom(long time) {
		this.time = time;
	}

	/**
	 * 检查展示时间
	 */
	public void checkTime(T key) {
		boolean a = isResting(key);
		if(a) {
			throwError();
		}
	}
	
	/**
	 * 是否正在休息
	 */
	public boolean isResting(T key) {
		Long time = map.get(key);
		long now = System.currentTimeMillis();
		if(time == null) {
			map.put(key, now);
		} else if(now - time < this.time) {
			return true;
		} else {
			map.put(key, now);
		}
		return false;
	}

	protected abstract void throwError();
}
