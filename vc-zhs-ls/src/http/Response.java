package http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import com.sun.net.httpserver.HttpExchange;

@SuppressWarnings("restriction")
public class Response {

	private HttpExchange e;

	public Response(HttpExchange e) {
		this.e = e;
	}

	public void sendString(String str) {
		byte[] bytes = str.getBytes();

		OutputStream os = null;
		try {
			e.sendResponseHeaders(HttpURLConnection.HTTP_OK, bytes.length);

			os = e.getResponseBody();
			try {
				os.write(bytes);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
			}
		}
	}
}
