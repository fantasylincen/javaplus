package com.linekong.platform.protocol.erating;

public interface Request {

	void put(String key, Object o);

	Response request();

}
