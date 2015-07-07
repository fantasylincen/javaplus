package cn.vgame.share;

import cn.javaplus.log.Log;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class CacheManager {

	private static final int SEC2 = 20;
	private static final int SEC = 35;
	private static final String KEY = "VGAME";
	static net.sf.ehcache.CacheManager CACHE;

	public static Object get(Object key) {
		Cache c = getCache();
		Element e = c.get(key);
		if (e == null)
			return null;
		return e.getObjectValue();
	}

	private static Cache getCache() {
		if (CACHE == null)
			CACHE = net.sf.ehcache.CacheManager.create();
		synchronized (CACHE) {
			if (CACHE.getCache(KEY) == null) {
				Log.d("init cache " + Thread.currentThread().getId());
				CACHE.addCache(new Cache(KEY, 5000, false, false, SEC, SEC2));
			}
		}
		return CACHE.getCache(KEY);
	}
	

	public static void main(String[] args) {
		long t = System.currentTimeMillis();

		CACHE = net.sf.ehcache.CacheManager.create();
		CACHE = net.sf.ehcache.CacheManager.create();
		CACHE = net.sf.ehcache.CacheManager.create();
		CACHE = net.sf.ehcache.CacheManager.create();
		CACHE = net.sf.ehcache.CacheManager.create();
		CACHE = net.sf.ehcache.CacheManager.create();
		CACHE = net.sf.ehcache.CacheManager.create();
		CACHE = net.sf.ehcache.CacheManager.create();
		CACHE = net.sf.ehcache.CacheManager.create();
		CACHE = net.sf.ehcache.CacheManager.create();
		CACHE = net.sf.ehcache.CacheManager.create();
		CACHE = net.sf.ehcache.CacheManager.create();
		CACHE = net.sf.ehcache.CacheManager.create();
		CACHE = net.sf.ehcache.CacheManager.create();
		Cache c;
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		c = new Cache(KEY, 5000, false, false, SEC, SEC2);
		System.out.println((System.currentTimeMillis() - t));
	}

	public static void put(Object key, Object o) {
		Cache cache = getCache();
		cache.put(new Element(key, o));
	}
}
