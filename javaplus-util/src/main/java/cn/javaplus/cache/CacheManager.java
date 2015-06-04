package cn.javaplus.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class CacheManager<K, V> {

	private final Map<K, CacheObject<V>> cacheMap = new ConcurrentHashMap<K, CacheObject<V>>();
	
	private static List<CacheManager<?, ?>> all = new ArrayList<CacheManager<?, ?>>();
	private static Thread t = new ClearCacheThread();
	static {
		t.start();
	}

	public static<K, V> CacheManager<K, V> newInstance() {
		synchronized(all) {
			CacheManager<K, V> c = new CacheManager<K, V>();
			all.add(c);
			return c;
		}
	}

	private CacheManager(){
	}

	public boolean addCache ( K key, V value, long durableSecond ) {
		CacheObject<V> co = new CacheObject<V>( value, durableSecond );
		cacheMap.put( key, co );
		return true;
	}

	public V getCache ( K key ) {
		CacheObject<V> co = cacheMap.get( key );
		if( co != null ){
			return co.getObj();
		}
		return null;
	}

	/**
	 * 系统缓存后台线程
	 * @author 	林岑
	 * @time	2012年9月29日 10:19:45
	 *
	 */
	private static class ClearCacheThread extends Thread {
		
		public ClearCacheThread() {
			super("系统缓存");
			this.setDaemon(true);
		}
		
		@Override
		public void run(){
			while( true ){
				try {
					TimeUnit.SECONDS.sleep(  1  );
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				for (CacheManager<?, ?> ic : all) {
					Iterator<?> it = ic.cacheMap.keySet().iterator();
					while(it.hasNext()) {
						Object k = it.next();
						CacheObject<?> v = ic.cacheMap.get(k);
						if(v.isExpired()) {
							it.remove();
						}
					}
				}
			}
		}
	}
	
	
	private static class CacheObject<V>{

		private long endTime;	//缓存到期的时候结束的时间毫秒数
		private V obj;
		
		/**
		 * 
		 * @param obj				要缓存的对象
		 * @param urableSecond		需要缓存的时间秒数！
		 */
		CacheObject(V obj, long durableSecond ) {
			this.obj = obj;
			this.endTime = System.currentTimeMillis() + durableSecond;
		}

		public V getObj () {
			return obj;
		}

		public boolean isExpired(){
			return endTime < System.currentTimeMillis();
		}
		@Override
		public String toString () {
			return "CacheObject [endTime=" + new Date( endTime).toString() + ", obj=" + obj + "]";
		}
	}


	/**
	 * 移除缓存
	 * @param userName
	 */
	public void remove(String userName) {
		this.cacheMap.remove(userName);
	}
}


