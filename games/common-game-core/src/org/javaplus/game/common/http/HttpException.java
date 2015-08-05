package org.javaplus.game.common.http;

import java.io.IOException;

public class HttpException extends RuntimeException {

	public HttpException(String message) {
		super(message);
	}

	public HttpException(IOException e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8222233323134443468L;

}
