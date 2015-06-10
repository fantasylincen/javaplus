package http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
abstract class AbstractHandler implements HttpHandler {

	private static final String SUCCESS_STRING = "<Response><result>1</result></Response>";

	public void handle(HttpExchange e) throws IOException {
		// System.out.println( e.getRequestURI() );
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> id = (Map<String, Object>) e.getAttribute("parameters");

			String r = doGet(id);
			byte[] bytes = r.getBytes();

			// Debuger.debug("AbstractHandler.handle()" + bytes.length);

			// e.setAttribute("Access-Control-Allow-Origin", "*");
			e.sendResponseHeaders(HttpURLConnection.HTTP_OK, bytes.length);
			// e.sendResponseHeaders("Access-Control-Allow-Origin", "*");
			// response.setHeader("Access-Control-Allow-Origin", "*");

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

	String responseSuccess() {
		return SUCCESS_STRING;
	}

	protected abstract String doGet(Map<String, Object> parameters);

}