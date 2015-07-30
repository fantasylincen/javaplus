package org.hhhhhh.fqzs.http;

import org.javaplus.game.common.http.HttpComponents.CallBackJson;
import org.javaplus.game.common.http.Request;

public interface FqzsClient {

	void request(String url, CallBackJson back);

	void request(Request request, CallBackJson back);

}
