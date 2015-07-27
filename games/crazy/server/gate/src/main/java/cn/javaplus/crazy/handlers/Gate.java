package cn.javaplus.crazy.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javaplus.crazy.base.AbstractHandler;
import cn.javaplus.crazy.config.Node;
import cn.javaplus.crazy.config.ServerJson;
import cn.javaplus.crazy.events.Events;
import cn.javaplus.crazy.events.GateStartEvent;
import cn.javaplus.log.Log;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class Gate {

	private HttpServer server;

	public void start() {
		try {

			addHandler(new LoginHandler());
			addHandler(new RegistHandler());
			addHandler(new OneKeyRegistHandler());
			addHandler(new CreateRoleHandler());

			server.setExecutor(null);
			server.start();
			Events.dispatch(new GateStartEvent(this));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Gate() {
		int backLog = 5;
		
		Node node = ServerJson.getRoot();
		node = node.get("gate");
		
		int port = node.getInt("port");

		Log.d(port);

		InetSocketAddress inetSock = new InetSocketAddress(port);
		try {
			server = HttpServer.create(inetSock, backLog);
			Log.d("gate start - port " + port);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void addHandler(AbstractHandler handler) {
		HttpContext c = server.createContext("/" + handler.getPath(), handler);
		c.getFilters().add(new ParameterFilter());
	}

	public class ParameterFilter extends Filter {

		@Override
		public String description() {
			return "Parses the requested URI for parameters";
		}

		@Override
		public void doFilter(HttpExchange exchange, Chain chain)
				throws IOException {
			parseGetParameters(exchange);
			parsePostParameters(exchange);
			chain.doFilter(exchange);
		}

		private void parseGetParameters(HttpExchange exchange)
				throws UnsupportedEncodingException {
			Map<String, Object> parameters = new HashMap<String, Object>();
			URI requestedUri = exchange.getRequestURI();
			String query = requestedUri.getRawQuery();
			parseQuery(query, parameters);
			exchange.setAttribute("parameters", parameters);
		}

		private void parsePostParameters(HttpExchange exchange)
				throws IOException {
			if ("post".equalsIgnoreCase(exchange.getRequestMethod())) {
				@SuppressWarnings("unchecked")
				Map<String, Object> parameters = (Map<String, Object>) exchange
						.getAttribute("parameters");
				InputStreamReader isr = new InputStreamReader(
						exchange.getRequestBody(), "utf-8");
				BufferedReader br = new BufferedReader(isr);
				String query = br.readLine();
				parseQuery(query, parameters);
			}
		}

		@SuppressWarnings("unchecked")
		private void parseQuery(String query, Map<String, Object> parameters)
				throws UnsupportedEncodingException {
			if (query != null) {
				String pairs[] = query.split("[&]");
				for (String pair : pairs) {
					String param[] = pair.split("[=]");
					String key = null;
					String value = null;
					if (param.length > 0) {
						key = URLDecoder.decode(param[0],
								System.getProperty("file.encoding"));
					}
					if (param.length > 1) {
						value = URLDecoder.decode(param[1],
								System.getProperty("file.encoding"));
					}
					if (parameters.containsKey(key)) {
						Object obj = parameters.get(key);
						if (obj instanceof List<?>) {
							List<String> values = (List<String>) obj;
							values.add(value);
						} else if (obj instanceof String) {
							List<String> values = new ArrayList<String>();
							values.add((String) obj);
							values.add(value);
							parameters.put(key, values);
						}
					} else {
						parameters.put(key, value);
					}
				}
			}
		}
	}
}
