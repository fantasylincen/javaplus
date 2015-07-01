package cn.vgame.init.server.xml;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		String projectPath = args[0];
		int zoneId = new Integer(args[1]);
		int port1 = zoneId;
		int port2 = zoneId + 1000;
		int port3 = zoneId + 2000;
		int port4 = zoneId + 3000;

		URL url = App.class.getResource("server.xml");
		String content = getContent(url);
		content = content.replaceAll("HTTP_PORT", port1 + "");
		content = content.replaceAll("REDIRECTPORT", port2 + "");
		content = content.replaceAll("AJP_PORT", port3 + "");
		content = content.replaceAll("SHUTDOWN_PORT", port4 + "");

		String file = projectPath + "/conf/server.xml";
		write(file, content);

		System.out.println("HTTP_PORT:" + port1);
		System.out.println("REDIRECTPORT:" + port2);
		System.out.println("AJP_PORT:" + port3);
		System.out.println("SHUTDOWN_PORT:" + port4);
		System.out.println("init server.xml successful: " + file);
	}

	public static String getContent(URL r) {
		InputStream s = null;
		BufferedReader bufferedReader = null;
		try {
			s = r.openStream();

			bufferedReader = new BufferedReader(
					new InputStreamReader(s, "utf8"));
			StringBuffer sb = new StringBuffer();
			read(sb, bufferedReader);
			return sb.toString();

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				s.close();
				bufferedReader.close();
			} catch (Throwable e) {
			}
		}
	}

	private static void read(StringBuffer sb, BufferedReader bufferedReader) {
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
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 将content以文本的方式, 写入到dst文件中. 强制覆盖
	 * 
	 * @param dst
	 * @param content
	 */
	public static void write(String file, String content) {
		OutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			java.io.File f = new java.io.File(file);
			if (!f.exists()) {
				int lastIndexOf = file.lastIndexOf("/");
				if (lastIndexOf == -1) {
					lastIndexOf = file.lastIndexOf("\\");
				}
				java.io.File path = new java.io.File(file.substring(0,
						lastIndexOf));
				path.mkdirs();
				f.createNewFile();
			}

			fos = new FileOutputStream(f);
			osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(content);
			osw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
	}

	public static void write(String file, Object content) {
		write(file, content.toString());
	}

	/**
	 * 将content以文本的方式, 写入到dst文件中. 强制覆盖
	 * 
	 * @param dst
	 * @param content
	 */
	public static void write(URL url, String content) {
		write(new java.io.File(url.getFile()), content);
	}

	public static void write(java.io.File file, String content) {
		try {
			write(file.getCanonicalPath(), content);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
