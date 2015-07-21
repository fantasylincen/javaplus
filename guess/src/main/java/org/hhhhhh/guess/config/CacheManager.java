//package org.hhhhhh.guess.config;
//
//import cn.javaplus.log.Log;
//import net.sf.ehcache.Cache;
//import net.sf.ehcache.Element;
//
//public class CacheManager {
//
//	private static final int SEC2 = 10;
//	private static final int SEC = 15;
//	private static final String KEY = "VGAME";
//	static net.sf.ehcache.CacheManager CACHE;
//
//	public static Object get(Object key) {
//		Cache c = getCache();
//		Element e = c.get(key);
//		if (e == null)
//			return null;
//		return e.getObjectValue();
//	}
//
//	private static Cache getCache() {
//		if (CACHE == null)
//			CACHE = net.sf.ehcache.CacheManager.create();
//		synchronized (CACHE) {
//			if (CACHE.getCache(KEY) == null) {
//				Log.d("init cache " + Thread.currentThread().getId());
//				CACHE.addCache(new Cache(KEY, 5000, false, false, SEC, SEC2));
//			}
//		}
//		return CACHE.getCache(KEY);
//	}
//
//	public static void put(Object key, Object o) {
//		Cache cache = getCache();
//		cache.put(new Element(key, o));
//	}
//}
