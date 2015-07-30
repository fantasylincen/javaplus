package org.hhhhhh.fqzs.http;

import org.hhhhhh.fqzs.core.App;
import org.javaplus.game.common.http.HttpAsyncClient;
import org.javaplus.game.common.http.HttpComponents.CallBackJson;
import org.javaplus.game.common.http.Parameters;
import org.javaplus.game.common.http.Request;

public class FqzsClientAdaptor implements FqzsClient {

	private final class RequestImplementation implements Request {
		private final Request request;

		private RequestImplementation(Request request) {
			this.request = request;
		}

		@Override
		public String getUrl() {
			return appendJsessionId(request.getUrl());
		}

		@Override
		public Parameters getParameters() {
			return request.getParameters();
		}
	}

	private HttpAsyncClient httpAsyncClient;

	public FqzsClientAdaptor(HttpAsyncClient httpAsyncClient) {
		this.httpAsyncClient = httpAsyncClient;
	}

	@Override
	public void request(String url, CallBackJson back) {
		url = appendJsessionId(url);
		httpAsyncClient.requestJson(url, back);
	}

	private String appendJsessionId(String url) {
		String jsessionid = App.getUserData().getJsessionid();
		if (jsessionid == null || jsessionid.isEmpty())
			return url;
		if (url.contains("?")) {
			return url.replaceFirst("\\?", ";jsessionid=" + jsessionid + "?");
		}
		return url + ";jsessionid=" + jsessionid;
	}

	@Override
	public void request(Request request, CallBackJson back) {
		request = appendJsessionId(request);
		httpAsyncClient.requestJson(request, back);
	}

	private Request appendJsessionId(final Request request) {
		return new RequestImplementation(request);
	}

}
