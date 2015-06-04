package db.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.mxz.base.telnet.command.system.CacheClearEvent;
import cn.mxz.events.Listener2;

public class Cache1Impl<K1, T> extends AbstractCache<K1, T> implements
		Cache1<K1, T> {

	private Map<K1, CacheObject<T>> cache = new HashMap<K1, CacheObject<T>>();

	class CacheRefreshListener implements Listener2 {

		@Override
		public void onEvent(Object e) {

			Set<Entry<K1, CacheObject<T>>> entrySet = cache.entrySet();

			Iterator<Entry<K1, CacheObject<T>>> it = entrySet.iterator();

			while (it.hasNext()) {

				Entry<K1, CacheObject<T>> next = it.next();

				if (next.getValue().getAliveTime() > CacheBase.ALIVE_TIME) {

					it.remove();
				}
			}
		}

		@Override
		public Class<?> getEventListendClass() {
			return CacheRefreshEvent.class;
		}

	}

	class CacheClearListener implements Listener2 {

		@Override
		public void onEvent(Object e) {

			cache.clear();
		}

		@Override
		public Class<?> getEventListendClass() {
			return CacheClearEvent.class;
		}

	}

	public Cache1Impl() {

		DaoCacheWartchDog.getInstance().addListener(new CacheRefreshListener());

		DaoCacheWartchDog.getInstance().addListener(new CacheClearListener());
	}

	@Override
	public void put(K1 k1, T o) {
		cache.put(k1, new CacheObject<T>(o));
	}

	@Override
	public void delete(K1 k1) {
		cache.remove(k1);
	}

	@Override
	public T get(K1 k1) {

		CacheObject<T> cacheObject = cache.get(k1);

		if (cacheObject == null) {

			return null;
		}

		cacheObject.addLiveTime();

		return cacheObject.getObject();
	}

	@Override
	public void clear() {

		cache.clear();
	}

	@Override
	public List<T> getAll() {
		Collection<CacheObject<T>> values = cache.values();

		ArrayList<T> arrayList = new ArrayList<T>();

		for (CacheObject<T> cacheObject : values) {

			arrayList.add(cacheObject.getObject());
		}

		return arrayList;
	}

}
