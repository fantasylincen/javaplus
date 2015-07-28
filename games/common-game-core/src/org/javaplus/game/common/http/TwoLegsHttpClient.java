package org.javaplus.game.common.http;

import java.net.SocketException;

import org.javaplus.game.common.http.HttpComponents.CallBackJson;
import org.javaplus.game.common.http.HttpComponents.CallBackJsonAdaptor;
import org.javaplus.game.common.http.HttpComponents.HttpClient;
import org.javaplus.game.common.log.Log;

import com.alibaba.fastjson.JSONObject;

public class TwoLegsHttpClient {

	private HttpClient client;

	private String serverAddress;

	private int requestTime;

	private final class GetServerAddress extends RequestAdaptor {
		@Override
		public String getUrl() {
			return "http://bcs.duapp.com/two-legs/server.json";
		}
	}

	private final class ServerAddressCallBack extends CallBackJsonAdaptor {
		private static final int MAX_REQUEST_TIMES = 3;
		private final CallBackJson callBack;
		private final TwoLegsRequest request;

		private ServerAddressCallBack(CallBackJson callBack, TwoLegsRequest request) {
			this.callBack = callBack;
			this.request = request;
		}

		@Override
		public void completed(JsonResult result) {
			JSONObject js = result.toJsonObject();
			JSONObject s = js.getJSONObject("server");
			String ip = s.getString("ip");
			String port = s.getString("port");
			serverAddress = ip + ":" + port;
			Log.d("get server address:" + serverAddress);
			sendRequest(request, callBack);
		}

		@Override
		public void failed(String error) {
			requestTime++;
			if (requestTime > MAX_REQUEST_TIMES) {
				Log.e("error:" + error);
			} else {
				requestServerAddress(request, callBack);
			}
		}

		@Override
		public void onTimeOut() {
			requestTime++;
			if (requestTime > MAX_REQUEST_TIMES) {
				Log.e("time out");
			} else {
				requestServerAddress(request, callBack);
			}
		}

		@Override
		public void onNetError(SocketException ex) {
			requestTime++;
			if (requestTime > MAX_REQUEST_TIMES) {
				Log.e("net error:" + ex.getMessage());
			} else {
				requestServerAddress(request, callBack);
			}
		}
	}

	public TwoLegsHttpClient(HttpClient client) {
		this.client = client;
	}

	public void excute(final TwoLegsRequest request, CallBackJson callBack) {
		if (serverAddress == null) {
			requestTime = 0;
			requestServerAddress(request, callBack);
		} else {
			sendRequest(request, callBack);
		}
	}
//	Map<String, String> params = new HashMap<String, String>();
//	params.put("surname", "rand");
//	params.put("xing", "TDana");
//	params.put("sex", "all");
//	params.put("num", "1");
//	
//	
//	HttpRespons s = r.sendPost("http://www.qmsjmfb.com", params);

	/**
	 * 请求服务器地址, 服务器地址请求成功后, 才会执行cc
	 */
	private void requestServerAddress(TwoLegsRequest rr, CallBackJson cc) {
		GetServerAddress r = new GetServerAddress();
		ServerAddressCallBack c = new ServerAddressCallBack(cc, rr);
		client.requestJson(r, c);
	}

	protected void sendRequest(final TwoLegsRequest request, CallBackJson callBack) {
		client.requestJson(new RequestAdaptor() {
			@Override
			public String getUrl() {
				String url = "http://" + serverAddress + "/" + request.getDomain();
				return url;
			}

			@Override
			public Parameters getParameters() {
				return request.getParameters();
			}
		}, callBack);
	}
}
