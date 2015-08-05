package cn.javaplus.game.shhz.assets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import cn.javaplus.shhz.exceptions.FileNotFoundRuntimeException;
import cn.javaplus.shhz.util.Closer;
import cn.javaplus.shhz.util.Util;
import cn.javaplus.shhz.util.Util.Exception;

public class CopyTextureShapes {

	public void copy() {
		String content = Util.File.getContent("texture_shapes.json");
		content = content.replaceAll("\\.\\./android/assets/", "");

		Util.File.write("../android/assets/texture_shapes.json", content);
	}

	/**
	 * 获得文本文件内容
	 * 
	 * @param file
	 * @return
	 */
	static String getContent(java.io.File file) {

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new java.io.FileReader(file));
			StringBuffer sb = new StringBuffer();
			read(sb, bufferedReader);
			return sb.toString();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundRuntimeException(e);
		} finally {
			Closer.close(bufferedReader);
		}

	}

	static void read(StringBuffer sb, BufferedReader bufferedReader) {
		while (true) {
			String line;
			try {
				line = bufferedReader.readLine();

				if (line == null) {
					break;
				}
				sb.append(line);
				sb.append("\r");
			} catch (IOException e) {
				throw Exception.toRuntimeException(e);
			}
		}
	}
}
