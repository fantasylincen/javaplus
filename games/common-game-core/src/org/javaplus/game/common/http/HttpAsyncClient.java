package org.javaplus.game.common.http;

import java.util.Collection;
import java.util.Iterator;

import org.javaplus.game.common.http.HttpComponents.CallBackBytes;
import org.javaplus.game.common.http.HttpComponents.CallBackJson;
import org.javaplus.game.common.http.HttpComponents.CallBackString;
import org.javaplus.game.common.http.HttpComponents.CrazyNetBytes;
import org.javaplus.game.common.http.HttpComponents.CrazyNetJson;
import org.javaplus.game.common.http.HttpComponents.CrazyNetString;
import org.javaplus.game.common.http.HttpComponents.GdxNetBytes;
import org.javaplus.game.common.http.HttpComponents.GdxNetJson;
import org.javaplus.game.common.http.HttpComponents.GdxNetString;
import org.javaplus.game.common.http.HttpComponents.HttpClient;

public class HttpAsyncClient implements HttpClient {

	private int timeOut;
	private CrazyNetJson netJson;
	private CrazyNetString netString;
	private CrazyNetBytes netBytes;

	private static final class FetcherImpl<T> implements Fetcher<T, String> {
		@Override
		public String get(T t) {
			return t + "";
		}
	}

	public HttpAsyncClient(int timeOut) {
		netJson = new GdxNetJson();
		netString = new GdxNetString();
		netBytes = new GdxNetBytes();
		this.timeOut = timeOut;
	}

	@Override
	public void requestJson(Request request, CallBackJson back) {
		Parameters pars = request.getParameters();
		String url = buildUrl(pars, request.getUrl());
		netJson.request(url, timeOut, back);
	}

	@Override
	public void requestString(Request request, CallBackString back) {
		Parameters pars = request.getParameters();
		String url = buildUrl(pars, request.getUrl());
		netString.request(url, timeOut, back);
	}

	@Override
	public void requestBytes(Request request, CallBackBytes back) {
		Parameters pars = request.getParameters();
		String url = buildUrl(pars, request.getUrl());
		netBytes.request(url, timeOut, back);
	}

	private String buildUrl(Parameters pars, String url) {
		if (pars.isEmpty()) {
			return url;
		}
		Collection<Parameter> ls = pars.getParameters();
		return url + "?" + linkWith("&", ls);
	}

	/**
	 * 用分隔符sp将 s的每个元素连接起来
	 * 
	 * @param sp
	 * @param s
	 * @return
	 */
	private static <T> String linkWith(String sp, java.util.Collection<T> s) {
		return linkWith(sp, s, new FetcherImpl<T>());
	}

	private interface Fetcher<T, R> {

		R get(T t);

	}

	/**
	 * 将all用sp分隔符连接起来
	 * 
	 * @param sp
	 * @param all
	 * @param fetcher
	 * @return
	 */
	private static <T, R> String linkWith(String sp,
			java.util.Collection<T> all, Fetcher<T, R> fetcher) {
		StringBuilder sb = new StringBuilder();
		Iterator<T> it = all.iterator();
		while (it.hasNext()) {
			T s = (T) it.next();
			sb.append(fetcher.get(s));
			if (it.hasNext()) {
				sb.append(sp);
			}
		}
		return sb + "";
	}

	public void requestJson(final String configAction, CallBackJson back) {
		Request request = new Request() {
			
			@Override
			public String getUrl() {
				return configAction;
			}
			
			@Override
			public Parameters getParameters() {
				return new Parameters();
			}
		};
		
		requestJson(request, back);
	}
}
