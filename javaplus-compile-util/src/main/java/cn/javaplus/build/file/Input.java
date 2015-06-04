package cn.javaplus.build.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Input {

	private FileInputStream in;

	public Input(File src) {
		try {
			in = new FileInputStream(src);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 尽量读byteLength长度的字节 如果不足 读出来的直接长度可能小于len 如果已经读完了所有的字节, 返回长度为0
	 * 
	 * @param len
	 * @return
	 */
	public byte[] readNext(int len) {
		try {
			int available = in.available();
			if (available <= 0) {
				return new byte[0];
			}

			int read = Math.min(len, available);
			byte[] data = new byte[read];

			in.read(data);
			return data;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void close() {
		try {
			in.close();
		} catch (IOException e) {
		}
	}

}
