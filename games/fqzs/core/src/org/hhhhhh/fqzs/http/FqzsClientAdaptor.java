package org.hhhhhh.fqzs.http;

import java.net.SocketException;

import org.hhhhhh.fqzs.core.App;
import org.javaplus.game.common.http.HttpAsyncClient;
import org.javaplus.game.common.http.HttpComponents.CallBackJson;
import org.javaplus.game.common.http.JsonResult;
import org.javaplus.game.common.http.Parameters;
import org.javaplus.game.common.http.Request;

public class FqzsClientAdaptor implements FqzsClient {

	public class CallBackJsonInterceptor implements CallBackJson {

		private CallBackJson back;

		public CallBackJsonInterceptor(CallBackJson back) {
			this.back = back;
		}

		public void completed(JsonResult result) {
			back.completed(result);
		}

		public void onTimeOut() {
			back.onTimeOut();
		}

		public void failed(String error) {
			back.failed(error);
		}

		public void onNetError(SocketException ex) {
			back.onNetError(ex);
		}

		public void httpError(String error) {
			back.httpError(error);
		}

		public void jsonParseError(String result) {
			back.jsonParseError(result);
		}

		public void cancelled() {
			back.cancelled();
		}

	}

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
		
		back = new CallBackJsonInterceptor(back);
		
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
