package cn.javaplus.crazy.http;

import java.util.Collection;
import java.util.Iterator;

public class HttpAsyncClient {

	private int timeOut;
	private CrazyNet net;

	private static final class FetcherImpl<T> implements Fetcher<T, String> {
		@Override
		public String get(T t) {
			return t.toString();
		}
	}

	public HttpAsyncClient(CrazyNet net, int timeOut) {
		this.net = net;
		this.timeOut = timeOut;
	}

	public void excute(Request request, final CallBack back) {
		Parameters pars = request.getParameters();
		String url = buildUrl(pars, request.getUrl());
		net.request(url, timeOut, back);
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
}
