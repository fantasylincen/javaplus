package http;

import java.io.IOException;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
abstract class AbstractAsyncHandler implements HttpHandler {

	private static final String SUCCESS_STRING = "<Response><result>1</result></Response>";

	public void handle(HttpExchange e) throws IOException {
		try {
			
			@SuppressWarnings("unchecked")
			Map<String, Object> id = (Map<String, Object>) e.getAttribute("parameters");
			doGet(id, new Response(e));
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	String responseSuccess() {
		return SUCCESS_STRING;
	}

	protected abstract void doGet(Map<String, Object> parameters, Response response) throws IOException;

}