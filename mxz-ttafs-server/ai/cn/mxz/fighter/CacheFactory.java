package cn.mxz.fighter;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class CacheFactory {

	public static Cache getCache(String cacheKey, int timeOutSec) {
		CacheManager manager = CacheManager.getInstance();
		Cache cacheOld = manager.getCache(cacheKey);
		Cache cacheNew = new Cache(cacheKey, 5000, true, false, timeOutSec, timeOutSec);
		if (cacheOld != null) {
			return cacheOld;
		} else {

			try {
				manager.addCache(cacheNew);
				return cacheNew;
			} catch (net.sf.ehcache.ObjectExistsException e) {
				return manager.getCache(cacheKey);
			}
		}
	}


}
