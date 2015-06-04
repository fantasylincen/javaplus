package cn.javaplus.collections.keyvalue;

public class KeyValueImpl<K, V> implements KeyValue<K, V>  {

	private K key;

	private V value;

	public KeyValueImpl(K k, V v) {

		this.key = k;

		this.value = v;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}
}
