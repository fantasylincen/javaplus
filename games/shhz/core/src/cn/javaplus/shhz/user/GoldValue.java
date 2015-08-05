package cn.javaplus.shhz.user;

public class GoldValue implements Value {

	int value = 2000;

	@Override
	public int getInt() {
		return value;
	}

}
