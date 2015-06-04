package com.test.myjava;










import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HttpServerTest {
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		try {
			int backLog = 10;
			InetSocketAddress inetSock = new InetSocketAddress(8086);
			HttpServer httpServer = HttpServer.create(inetSock, backLog);
			httpServer.createContext("/", new HandlerTestA());
			httpServer.createContext("/test", new HandlerTestB());
			httpServer.setExecutor(null);
			httpServer.start();
			System.out.println("HttpServer Test Start!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class HandlerTestA implements HttpHandler {

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		System.out.println("HandlerTestA.handle()" + httpExchange.getHttpContext());
		String responseString = "<font color='#ff0000'> chunshengnimei! </font>";
		httpExchange.sendResponseHeaders(200, responseString.length());
		OutputStream os = httpExchange.getResponseBody();
		os.write(responseString.getBytes());
		os.close();
	}

}

class HandlerTestB implements HttpHandler {

	ThreadPoolExecutor	threadPoolExecutor;

	HandlerTestB() {
		threadPoolExecutor = new ThreadPoolExecutor(2, 3, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), new ThreadPoolExecutor.CallerRunsPolicy());
	}

	@Override
	public void handle(HttpExchange he) throws IOException {
		if ((getQueueSize(threadPoolExecutor.getQueue())) < 2) {
			RequestTasks rqt = new RequestTasks(he);
			threadPoolExecutor.execute(rqt);
		} else
			System.out.println("Please Wait!");
	}

	private synchronized int getQueueSize(Queue<?> queue) {
		return queue.size();
	}

}

class RequestTasks implements Runnable {

	static int		processedNum	= 0;
	HttpExchange	httpExchange;

	RequestTasks(HttpExchange he) {
		httpExchange = he;
		processedNum++;
	}

	@Override
	public void run() {
		System.out.println("ProcessedNum:" + processedNum);
		String responseString = "ProcessedNum:" + processedNum + "\n";
		try {
			httpExchange.sendResponseHeaders(200, responseString.length());
			OutputStream os = httpExchange.getResponseBody();
			os.write(responseString.getBytes());
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}