package cn.javaplus.crazy.base;

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
			doGet(r, ps);
		} catch (GateException ee) {
			buildErrorJson(response, ee.getMessage());
		}

		response(e, response.toJSONString());
	}

	private void buildErrorJson(JSONObject response, String message) {
		response.put("error", message);
	}

	// private String buildErrorJson(JSONObject response, Throwable e1) {
	// StringWriter sw = new StringWriter();
	// PrintWriter pw = new PrintWriter(sw);
	// e1.printStackTrace(pw);
	//
	// buildErrorJson(response, sw.toString());
	//
	// pw.close();
	// return response.toJSONString();
	// }

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
			Log.e(e1);
			e1.printStackTrace();
		} finally {
			Closer.close(os);
		}
	}

	protected abstract void doGet(Response response, Parameters ps);

	public abstract String getPath();
}