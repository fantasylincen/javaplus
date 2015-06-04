package cn.javaplus.commons.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
public abstract class AbstractHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange e) throws IOException {

		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> id = (Map<String, Object>) e.getAttribute("parameters");

			String r = doGet(id);
			byte[] bytes = r.getBytes();

			e.sendResponseHeaders(HttpURLConnection.HTTP_OK, bytes.length);

			OutputStream os = e.getResponseBody();
			try {
				os.write(bytes);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			os.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected abstract String doGet(Map<String, Object> parameters);

}