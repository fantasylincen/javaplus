package org.javaplus.game.common.http;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import org.javaplus.game.common.log.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.net.HttpStatus;

public class HttpComponents {

	public interface CrazyNetBytes {

		void request(String url, int timeOut, CallBackBytes back);
	}

	public interface CrazyNetString {

		void request(String url, int timeOut, CallBackString back);
	}

	public interface CrazyNetJson {
		void request(String url, int timeOut, CallBackJson back);
	}

	public interface CallBackJson {
		void completed(JsonResult result);

		void onTimeOut();

		void failed(String error);

		void onNetError(SocketException ex);

		void httpError(String error);

		void jsonParseError(String result);

		void cancelled();
	}

	public interface CallBackBytes {

		void completed(byte[] result);

		void onTimeOut();

		void onNetError(SocketException ex);

		void httpError(String string);

		void cancelled();

	}

	public interface CallBackString {

		void completed(String result);

		void onTimeOut();

		void onNetError(SocketException ex);

		void httpError(String error);

		void cancelled();
	}

	public interface HttpClient {

		void requestJson(Request request, CallBackJson back);

		void requestString(Request request, CallBackString back);

		void requestBytes(Request request, CallBackBytes back);
	}

	private static class CallBackBytesImpl implements HttpResponseListener {

		private CallBackBytes back;

		public CallBackBytesImpl(CallBackBytes back) {
			this.back = back;
		}

		@Override
		public void handleHttpResponse(HttpResponse result) {

			HttpStatus status = result.getStatus();
			int code = status.getStatusCode();

			if (-1 == code) {
				back.onTimeOut();
				return;
			}

			if (HttpStatus.SC_OK != code) {
				back.httpError("" + code);
				return;
			}
			byte[] resultBytes = result.getResult();
			back.completed(resultBytes);
		}

		@Override
		public void failed(Throwable ex) {
			if (ex instanceof SocketTimeoutException) {
				back.onTimeOut();
				return;
			}
			if (ex instanceof SocketException) {
				back.onNetError((SocketException) ex);
			}
			if (ex instanceof RuntimeException) {
				Log.e(ex);
				throw (RuntimeException) ex;
			}
			throw new RuntimeException(ex);
		}

		@Override
		public void cancelled() {
			back.cancelled();
		}
	}


	public static class CallBackBytesAdaptor implements CallBackBytes {

		@Override
		public void onTimeOut() {
			Log.e("timeOut");
		}

		@Override
		public void onNetError(SocketException ex) {
			Log.e("onNetError:" + ex.getMessage());
		}

		@Override
		public void httpError(String error) {
			Log.e("httpError:" + error);
		}

		@Override
		public void cancelled() {
			Log.e("cancelled");
		}

		@Override
		public void completed(byte[] result) {

		}
		
	}
	
	public static class CallBackStringAdaptor implements CallBackString {

		@Override
		public void onTimeOut() {
			Log.e("timeOut");
		}

		@Override
		public void onNetError(SocketException ex) {
			Log.e("onNetError:" + ex.getMessage());
		}

		@Override
		public void httpError(String error) {
			Log.e("httpError:" + error);
		}

		@Override
		public void cancelled() {
			Log.e("cancelled");
		}

		@Override
		public void completed(String result) {

		}

	}

	public static class CallBackJsonAdaptor implements CallBackJson {

		@Override
		public void completed(JsonResult result) {

		}

		@Override
		public void onTimeOut() {
			Log.e("timeOut");
		}

		@Override
		public void failed(String error) {
			Log.e("failed:" + error);
		}

		@Override
		public void onNetError(SocketException ex) {
			Log.e("onNetError:" + ex.getMessage());
		}

		@Override
		public void httpError(String error) {
			Log.e("httpError:" + error);
		}

		@Override
		public void jsonParseError(String result) {
			Log.e("jsonParseError:" + result);
		}

		@Override
		public void cancelled() {
			Log.e("cancelled");
		}

	}

	public static class GdxNetBytes implements CrazyNetBytes {

		@Override
		public void request(String url, int timeOut, CallBackBytes back) {
			HttpRequest r = new HttpRequest(HttpMethods.GET);
			r.setUrl(url);
			r.setTimeOut(timeOut);
			CallBackBytesImpl callBack = new CallBackBytesImpl(back);
			Gdx.net.sendHttpRequest(r, callBack);
			Log.d("GdxNetBytes.request", url, timeOut);
		}

	}

	public static class GdxNetString implements CrazyNetString {

		private GdxNetBytes net;

		public GdxNetString() {
			net = new GdxNetBytes();
		}

		@Override
		public void request(String url, int timeOut, final CallBackString back) {
			CallBackBytes b = new CallBackBytes() {

				@Override
				public void onTimeOut() {
					back.onTimeOut();
				}

				@Override
				public void onNetError(SocketException ex) {
					back.onNetError(ex);
				}

				@Override
				public void completed(byte[] result) {
					back.completed(new String(result));
				}

				@Override
				public void httpError(String error) {
					back.httpError(error);
				}

				@Override
				public void cancelled() {
					back.cancelled();
				}
			};
			net.request(url, timeOut, b);
		}

	}

	public static class GdxNetJson implements CrazyNetJson {

		private GdxNetString net;

		public GdxNetJson() {
			net = new GdxNetString();
		}

		@Override
		public void request(String url, int timeOut, final CallBackJson back) {
			CallBackString b = new CallBackString() {

				@Override
				public void onTimeOut() {
					back.onTimeOut();
				}

				@Override
				public void onNetError(SocketException ex) {
					back.onNetError(ex);
				}

				@Override
				public void httpError(String error) {
					back.httpError(error);
				}

				@Override
				public void completed(String result) {

					try {
						JSONObject object = JSON.parseObject(result);
						JsonResultImpl r = new JsonResultImpl(object);
						String error = r.getString("error");
						if (error != null) {
							back.failed(error);
						} else {
							back.completed(r);
						}
					} catch (JSONException e) {
						back.jsonParseError(result);
					}
				}

				@Override
				public void cancelled() {
					back.cancelled();
				}
			};
			net.request(url, timeOut, b);
		}

	}

}
