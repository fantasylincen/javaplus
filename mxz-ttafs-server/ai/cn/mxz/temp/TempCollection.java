package cn.mxz.temp;

import java.util.HashMap;

import cn.javaplus.db.KeyValueCollection;
import cn.mxz.city.City;

import com.google.common.collect.Maps;

public class TempCollection implements KeyValueCollection<TempKey, Object> {

//	private HashMap<TempKey, Object>	map;
//
//	public TempCollection(City city) {
//		map = Maps.newHashMap();
//	}
//
//	@Override
//	public Object get(TempKey k) {
//		return map.get(k);
//	}
//
//	@Override
//	public void put(TempKey k, Object v) {
//		map.put(k, v);
//	}
//
//	/**
//	 * 移除 并 返回被移除的对象
//	 * @param k
//	 * @return
//	 */
//	public Object remove(TempKey k) {
//		return map.remove(k);
//	}
	
	
	
	private static HashMap<String, Object>	map = Maps.newHashMap();
private City city;

	public TempCollection(City city) {
		this.city = city;
//		map = Maps.newHashMap();
	}

	@Override
	public Object get(TempKey k) {
		return map.get(key(k));
	}

	private String key(TempKey k) {
		return city.getId() + ":" + k;
	}

	@Override
	public void put(TempKey k, Object v) {
		map.put(key(k), v);
	}

	/**
	 * 移除 并 返回被移除的对象
	 * @param k
	 * @return
	 */
	public Object remove(TempKey k) {
		return map.remove(key(k));
	}
}
