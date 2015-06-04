package cn.javaplus.collections.map;

public class DoubleKeyImpl<K1, K2> implements DoubleKey<K1, K2> {

	private K1	k1;

	private K2	k2;

	public DoubleKeyImpl(K1 k1, K2 k2) {

		this.k1 = k1;

		this.k2 = k2;
	}

	@Override
	public K1 getK1() {

		return k1;
	}

	@Override
	public K2 getK2() {

		return k2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((k1 == null) ? 0 : k1.hashCode());
		result = prime * result + ((k2 == null) ? 0 : k2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		DoubleKeyImpl other = (DoubleKeyImpl) obj;
		if (k1 == null) {
			if (other.k1 != null)
				return false;
		} else if (!k1.equals(other.k1))
			return false;
		if (k2 == null) {
			if (other.k2 != null)
				return false;
		} else if (!k2.equals(other.k2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DoubleKey [k1=" + k1 + ", k2=" + k2 + "]";
	}


}