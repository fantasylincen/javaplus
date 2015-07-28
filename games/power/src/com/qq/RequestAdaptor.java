package com.qq;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.open.HttpStatusException;
import com.tencent.open.NetworkUnavailableException;
import com.tencent.tauth.IRequestListener;

public class RequestAdaptor implements IRequestListener {

	@Override
	public void onComplete(JSONObject arg0, Object arg1) {

	}

	@Override
	public void onConnectTimeoutException(ConnectTimeoutException arg0, Object arg1) {

	}

	@Override
	public void onHttpStatusException(HttpStatusException arg0, Object arg1) {

	}

	@Override
	public void onIOException(IOException arg0, Object arg1) {

	}

	@Override
	public void onJSONException(JSONException arg0, Object arg1) {

	}

	@Override
	public void onMalformedURLException(MalformedURLException arg0, Object arg1) {

	}

	@Override
	public void onNetworkUnavailableException(NetworkUnavailableException arg0, Object arg1) {

	}

	@Override
	public void onSocketTimeoutException(SocketTimeoutException arg0, Object arg1) {

	}

	@Override
	public void onUnknowException(Exception arg0, Object arg1) {

	}

}
