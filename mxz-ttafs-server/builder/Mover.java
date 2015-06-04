import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Mover {
	private static final String PATH = "C:/Users/Administrator/Desktop/服务器/";

	static SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd-HHmmss");

	public static void moveFile(File dst) throws IOException {

		File file2 = new File(PATH + "ttafs_latest.zip");

		Date date = new Date();
		String time = f1.format(date);
		String head = "bbfs_server_full_v";
		String version = "1.0.0.1";

		FileInputStream fis = new FileInputStream(file2);
		byte[] data = new byte[100240000];
		int read = fis.read(data);
		data = Arrays.copyOf(data, read);

		String md5 = md5(data);

		String hh = head + version + "_" + time;

		String dirName = dst.getName();
		String name = PATH + dirName + "/" +hh;

		fis.close();

		String binTail = "_bin.zip";

		String emailTail = "_email.html";
		String text = buildHtml(md5, date, hh + binTail);
		String email = name + emailTail;
		write(email, text);

		String pathname = name + binTail;
		File bin = new File(pathname);
		file2.renameTo(bin);


		System.out.println("二进制包: " + bin);
		System.out.println("邮件文档: " + email);
		System.out.println("BUILD SUCCESS");
	}

	static SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

	private static String buildHtml(String md5, Date date, String name) {

		String time2 = f.format(date);
		File r = new File("res/build/temp");
		String c = getContent(r);
		c = c.replaceAll("MD5_CODE", md5);
		c = c.replaceAll("FILE_NAME", name);

		String titleTime = f1.format(date);

		c = c.replaceAll("TITLE_TIME", titleTime);
		c = c.replaceAll("TIME", time2);

		return c;
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
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
				}
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
				}
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
			osw = new OutputStreamWriter(fos, "utf8");
			osw.write(content);
			osw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static String md5(byte[] data) {
		StringBuffer md5 = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(data);
			byte[] digest = md.digest();
			for (int i = 0; i < digest.length; i++) {
				md5.append(Character.forDigit((digest[i] & 0xF0) >> 4, 16));
				md5.append(Character.forDigit((digest[i] & 0xF), 16));
			}
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
		return md5.toString();
	}

	/**
	 * 获得指定路径的文件内容
	 * 
	 * @param path
	 * @return
	 */
	public static String getContent(String path) {
		return getContent(new java.io.File(path));
	}

	/**
	 * 获得文本文件内容
	 * 
	 * @param file
	 * @return
	 */
	public static String getContent(java.io.File file) {

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new java.io.FileReader(file));
			StringBuffer sb = new StringBuffer();
			read(sb, bufferedReader);
			return sb.toString();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e) {
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
}
