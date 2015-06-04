package cn.javaplus.stock.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class CacheManager {

	private static final int SEC2 = Integer.MAX_VALUE;
	private static final int SEC = Integer.MAX_VALUE;
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
		Cache c = CACHE.getCache(KEY);
		if (c == null) {
			c = new Cache(KEY, 5000, true, false, SEC, SEC2);
			CACHE.addCache(c);
		}
		return c;
	}

	public static void put(Object key, long time, Object o) {
		Cache cache = getCache();
		cache.put(new Element(key, o));
	}
}
