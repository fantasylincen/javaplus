package cn.javaplus.stock.apps;

import cn.javaplus.stock.gen.dto.MongoGen.MongoDbProperties;

public final class MongoDbPropertiesImpl implements
		MongoDbProperties {
	@Override
	public int getPort() {
		return 27017;
	}

	@Override
	public String getName() {
		return "stock";
	}

	@Override
	public String getHost() {
		return "127.0.0.1";
	}
}