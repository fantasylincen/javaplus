package cn.javaplus.file;

import java.io.FileNotFoundException;

public class FileNotFoundRuntimeException extends RuntimeException {


	public FileNotFoundRuntimeException(FileNotFoundException e) {
		super(e);
	}

	/**
	 *
	 */
	private static final long	serialVersionUID	= -7446042653594066307L;

}
