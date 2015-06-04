package cn.mxz.keyvalue;

import java.util.HashSet;

import com.google.common.collect.Sets;

import cn.javaplus.db.KeyValueCollection;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.city.City;
import cn.mxz.util.db.KeyValueCollectionFactory;

public class KeyValueTransformImpl implements KeyValueTransform {

	private City user;

	@Override
	public void put(String key, String value) {
		KeyValueCollection<Object, String> c = KeyValueCollectionFactory
				.getMongoCollection();
		if (user != null) {
			c.put(key(key), value);
		}
	}

	private String key(String key) {
		return "CLIENT_DB:" + user.getId() + ":" + key;
	}

	@Override
	public Value getValue(String key) {

//		HashSet<String> s = Sets.newHashSet("105", "106", "110");
//		if (s.contains(key)) {
//			return new ValueImpl("1");
//		}

		KeyValueCollection<Object, String> c = KeyValueCollectionFactory
				.getMongoCollection();
		if (user == null) {
			throw new SureIllegalOperationException("无效的 session ");
		}
		return new ValueImpl(c.get(key(key)));
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

}
