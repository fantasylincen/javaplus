package cn.javaplus.build.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Output {

	private FileOutputStream out;

	public Output(File out) {
		try {
			this.out = new FileOutputStream(out);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void write(byte[] data) {
		try {
			out.write(data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void flush() {
		try {
			out.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void close() {
		try {
			out.close();
		} catch (IOException e) {
		}
	}

}
