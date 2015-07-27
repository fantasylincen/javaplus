package org.javaplus.game.common.util;

import java.io.FileNotFoundException;

public class FileNotFoundRuntimeException extends RuntimeException {

	public FileNotFoundRuntimeException(FileNotFoundException e) {
		super(e);
	}

	public FileNotFoundRuntimeException() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2315964456162923031L;

}
