package db.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.javaplus.collections.map.DoubleKey;
import cn.javaplus.collections.map.DoubleKeyMap;
import cn.javaplus.collections.map.DoubleKeyMapImpl;
import cn.mxz.events.Listener2;

import com.google.common.collect.Lists;

public class Cache2Impl<K1, K2, T> extends AbstractCache<K1, T> implements Cache2<K1, K2, T> {

	private DoubleKeyMap<K1, K2, CacheObject<T>>	cache	= new DoubleKeyMapImpl<K1, K2, CacheObject<T>>();

	class CacheRefreshListener implements Listener2 {

		@Override
		public synchronized void onEvent(Object e) {

			Collection<DoubleKey<K1, K2>> ks = cache.keySet();

			ks = Lists.newArrayList(ks);

			for (DoubleKey<K1, K2> k : ks) {

				K1 k1 = k.getK1();

				K2 k2 = k.getK2();

				CacheObject<T> obj = cache.get(k1, k2);

				if(obj == null) {

					new NullPointerException("k1 = " + k1 + " k2 = " + k2).printStackTrace();

					continue;
				}

				if(obj.getAliveTime() > CacheBase.ALIVE_TIME) {

					cache.remove(k1, k2);
				}
			}
		}

		@Override
		public Class<?> getEventListendClass() {
			return CacheRefreshEvent.class;
		}

	}



	public Cache2Impl() {

		DaoCacheWartchDog.getInstance().addListener(new CacheRefreshListener());
	}

	@Override
	public List<T> getAll() {

		Collection<CacheObject<T>> values = new ArrayList<CacheObject<T>>(cache.values());

		ArrayList<T> arrayList = new ArrayList<T>();

		for (CacheObject<T> cacheObject : values) {

			arrayList.add(cacheObject.getObject());
		}

		return arrayList;
	}

	@Override
	public void clear() {

		cache.clear();
	}

	@Override
	public void put(K1 k1, K2 k2, T afbo) {

		if(afbo == null) {

			throw new NullPointerException();
		}

		cache.put(k1, k2, new CacheObject<T>(afbo));
	}

	@Override
	public void delete(K1 k1, K2 k2) {

		cache.remove(k1, k2);
	}

	@Override
	public T get(K1 k1, K2 k2) {

		CacheObject<T> cacheObject = cache.get(k1, k2);

		if(cacheObject == null) {

			return null;
		}

		cacheObject.addLiveTime();

		return cacheObject.getObject();
	}
}
