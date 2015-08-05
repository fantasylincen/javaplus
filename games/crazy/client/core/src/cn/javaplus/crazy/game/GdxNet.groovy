package cn.javaplus.crazy.game;

import cn.javaplus.crazy.http.CallBack
import cn.javaplus.crazy.http.CrazyNet
import cn.javaplus.crazy.http.JsonResultImpl

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Net.HttpMethods
import com.badlogic.gdx.Net.HttpRequest
import com.badlogic.gdx.Net.HttpResponse
import com.badlogic.gdx.Net.HttpResponseListener
import com.badlogic.gdx.net.HttpStatus

public class GdxNet implements CrazyNet {

	@Override
	public void request(String url, int timeOut, CallBack back) {
		HttpRequest r = new HttpRequest(HttpMethods.GET);
		r.setUrl(url);
		r.setTimeOut(timeOut);
		CallBackImpl httpResponseListener = new CallBackImpl(back);
		Gdx.net.sendHttpRequest(r, httpResponseListener);
	}

}

class CallBackImpl implements HttpResponseListener {

	private CallBack back;

	public CallBackImpl(CallBack back) {
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
			back.failed("" + code);
			return;
		}

		JsonResultImpl r = new JsonResultImpl(result);
		String error = r.getString("error");
		if (error != null) {
			back.failed(error);
		} else {
			back.completed(r);
		}
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

	}

}
