package db.dao.impl;

import java.util.Collection;

import org.junit.Test;

import cn.javaplus.collections.map.DoubleKey;
import cn.javaplus.collections.map.DoubleKeyMap;
import cn.javaplus.collections.map.DoubleKeyMapImpl;

public class Cache2Test {

	@Test
	public final void testCache2Impl() {

		DoubleKeyMap<Integer, String, Boolean> cache = new DoubleKeyMapImpl<Integer, String, Boolean>();

		cache.put(1, "cc", false);
		cache.put(1, "ab", false);
		cache.put(1, "cd", false);

//		 cache.clear();

		cache.remove(1, "cc");

		Collection<DoubleKey<Integer, String>> keyMap = cache.keySet();

		for (DoubleKey<Integer, String> c : keyMap) {

			Integer k1 = c.getK1();

			String k2 = c.getK2();

			Boolean obj = cache.get(k1, k2);

			System.out.println(c + "obj = " + obj);
		}

	}
}
