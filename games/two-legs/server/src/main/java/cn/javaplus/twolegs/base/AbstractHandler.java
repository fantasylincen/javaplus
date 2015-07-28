package cn.javaplus.twolegs.base;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Map;

import cn.javaplus.log.Log;
import cn.javaplus.util.Closer;

import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class AbstractHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange e) throws IOException {
		Object a = e.getAttribute("parameters");
		@SuppressWarnings("unchecked")
		Parameters ps = new Parameters((Map<String, Object>) a);

		JSONObject response = new JSONObject();

		try {
			Response r = new Response(response);
			Log.d("request class:" + getClass().getSimpleName());
			Log.d("parameters:" + ps);
			handle(r, ps);
			System.out.println(Thread.currentThread().getId());
		} catch (GameServerException ee) {
			buildErrorJson(response, ee.getMessage());
		} catch (Throwable ee) {
			ee.printStackTrace();
			return;
		}

		response(e, response.toJSONString());
	}

	private void buildErrorJson(JSONObject response, String message) {
		response.put("error", message);
	}

	private void response(HttpExchange e, String r) throws IOException {

		byte[] bytes = r.getBytes();
		e.sendResponseHeaders(HttpURLConnection.HTTP_OK, bytes.length);
		OutputStream os = e.getResponseBody();

		try {
			os.write(bytes);
			Log.d("response: ");
			Log.d(r);
			Log.d();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			Closer.close(os);
		}
	}

	protected abstract void handle(Response response, Parameters ps);
}