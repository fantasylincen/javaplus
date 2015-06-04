package _util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

	/**
	 * 获取文件, 可能获取的是一个不存在的文件
	 * @param dir 目录名字
	 * @param f 目录下的文件名
	 * @return
	 */
	public static File getFile(File dir, String f) {
		try {
			String canonicalPath = dir.getCanonicalPath();
			if (!canonicalPath.endsWith(File.separator)) {
				canonicalPath += File.separator;
			}
			f = canonicalPath + f;
			File file = new File(f);
			return file;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	//
	//	public static void main(String[] args) {
	//		System.out.println(File.separator);
	//		File f = getFile(new File("C:/windows"), "centOS.zip");
	//		System.out.println(f);
	//	}

	public static String getContent(File file) {

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			StringBuffer sb = new StringBuffer();
			read(sb, bufferedReader);
			return sb.toString();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(bufferedReader);
		}

	}

	private static void read(StringBuffer sb, BufferedReader bufferedReader) {
		while (true) {
			String line;
			try {
				line = bufferedReader.readLine();

				if(line == null) {
					break;
				}
				sb.append(line);
				sb.append("\r");
			} catch (IOException e) {
				throw Util.toRuntimeException(e);
			}
		}
	}
}
