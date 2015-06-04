package cn.javaplus.time.coolingtimer;

import java.util.HashMap;
import java.util.Map;

/**
 * 冷却时间集
 * @author 	林岑
 * @since	2012年6月20日 21:29:06
 * @param <K>
 */
public class CoolingTimers<K> {
	
	private Map<K, ICoolingTimer> coolingTimers = new HashMap<K, ICoolingTimer>();
	
	/**
	 * 获得key对应的冷却时间
	 * @param key
	 * @return
	 */
	public ICoolingTimer getTimer(K key) {
		ICoolingTimer ic = this.coolingTimers.get(key);
		if(ic == null) {
			ic = new CoolingTimer();
			this.coolingTimers.put(key, ic);
		}
		return ic;
	}
}
