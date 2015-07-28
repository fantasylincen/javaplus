package cn.javaplus.crazy.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.crazy.collections.Lists;
import cn.javaplus.exception.FileNotFoundRuntimeException;

/**
 * 工具类
 * 
 * @author 林岑
 * @since 2012年1月7日 09:55:08
 */
public class Util {

	public static final java.util.Random R = new java.util.Random();

	/**
	 * 文件工具
	 * 
	 * @author 林岑
	 * 
	 */
	public static final class File {

		/**
		 * 遍历一个文件夹中, 所有文本文件中的文本
		 * 
		 * @param dir
		 * @return
		 */
		public static Iterator<String> buildContentIterator(URL dir) {

			String file = dir.getFile();
			java.io.File f = new java.io.File(file);
			final java.io.File[] files = f.listFiles();

			return new TextFileContentIterator(files);
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
				Closer.close(fos);
			}
		}

		public static void write(java.io.File file, String content) {
			try {
				write(file.getCanonicalPath(), content);
			} catch (IOException e) {
				throw Exception.toRuntimeException(e);
			}
		}

		/**
		 * 获得某个文件中所有的行
		 * 
		 * @param filePath
		 * @return
		 */
		public static List<String> getLines(String filePath) {
			java.io.File file;
			try {
				file = new java.io.File(filePath);
			} catch (java.lang.Exception e) {
				throw Util.Exception.toRuntimeException(e);
			}

			return getLines(file);
		}

		public static List<String> getLines(URL resource) {
			FileLinesReader f;
			InputStream input = null;
			try {

				input = resource.openStream();

				f = new FileLinesReader(new InputStreamReader(input));

				return f.readLines();

			} catch (java.lang.Exception e) {
				throw Util.Exception.toRuntimeException(e);
			} finally {
				Closer.close(input);
			}
		}

		public static List<String> getLines(java.io.File file) {
			FileLinesReader f;
			try {
				f = new FileLinesReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				throw Util.Exception.toRuntimeException(e);
			}
			return f.readLines();
		}

		/**
		 * 获得某个文件夹下, 所有的文件
		 * 
		 * @param path
		 *            文件夹路径
		 * @return
		 */
		public static List<java.io.File> getFiles(String path) {
			List<java.io.File> files = Lists.newArrayList();
			java.io.File f = new java.io.File(path);
			String[] all = f.list();

			if (all != null) {
				for (String fname : all) {
					java.io.File fs = new java.io.File(path + "/" + fname);
					if (fs.isDirectory()) {
						files.addAll(getFiles(path + "/" + fname));
					} else {
						files.add(fs);
					}
				}
			}
			return files;
		}

		/**
		 * 获得某个文件夹下, 所有的文件
		 * 
		 * @param dirPath
		 *            文件夹路径
		 * @param except
		 *            排除的文件後綴列表
		 * @return
		 */
		public static List<java.io.File> getFiles(String dirPath,
				String... except) {

			List<java.io.File> files = getFiles(dirPath);
			Iterator<java.io.File> it = files.iterator();
			while (it.hasNext()) {
				java.io.File file = it.next();
				String name = file.getName();
				if (isExcept(name, except)) {
					it.remove();
				}
			}
			return files;
		}

		/**
		 * 是否被排除
		 * 
		 * @param name
		 * @return
		 */
		private static boolean isExcept(String name, String... except) {

			for (String s : except) {

				if (name.endsWith(s)) {

					return true;
				}
			}

			return false;
		}

		/**
		 * 遍历某个目录下所有文件
		 * 
		 * @param path
		 * @return
		 */
		public static Iterator<java.io.File> getFilesIterator(String path) {
			List<java.io.File> all = getFiles(path);
			return all.iterator();
		}

		public static List<java.io.File> getFiles(URL resource) {
			String file = resource.getFile();
			java.io.File f = new java.io.File(file);
			return Lists.newArrayList(f.listFiles());
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
				bufferedReader = new BufferedReader(
						new java.io.FileReader(file));
				StringBuffer sb = new StringBuffer();
				read(sb, bufferedReader);
				return sb.toString();
			} catch (FileNotFoundException e) {
				throw new FileNotFoundRuntimeException(e);
			} finally {
				Closer.close(bufferedReader);
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
					throw Exception.toRuntimeException(e);
				}
			}
		}

		/**
		 * 获得指定路径的文件内容
		 * 
		 * @param path
		 * @return
		 */
		public static String getContent(String path) {
			java.io.File file = new java.io.File(path);
			return getContent(file);
		}

		public static String getContent(URL r) {
			InputStream s = null;
			BufferedReader bufferedReader = null;
			try {
				s = r.openStream();

				bufferedReader = new BufferedReader(new InputStreamReader(s));
				StringBuffer sb = new StringBuffer();
				read(sb, bufferedReader);
				return sb.toString();

			} catch (IOException e) {
				throw Util.Exception.toRuntimeException(e);
			} finally {
				Closer.close(s);
				Closer.close(bufferedReader);
			}
		}

	}

	/**
	 * 字符串处理工具
	 * 
	 * @author 林岑
	 * 
	 */
	public static final class Str {

		/**
		 * 半角转全角
		 * 
		 * @param input
		 *            String.
		 * @return 全角字符串.
		 */
		public static String toSBC(String input) {
			char [] c = input.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (c[i] == ' ') {
					c[i] = '\u3000';
				} else if (c[i] < '\177') {
					c[i] = (char) (c[i] + 65248);

				}
			}
			return new String(c);
		}

		/**
		 * 全角转半角
		 * 
		 * @param input
		 *            String.
		 * @return 半角字符串
		 */
		public static String toDBC(String input) {

			char [] c = input.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (c[i] == '\u3000') {
					c[i] = ' ';
				} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
					c[i] = (char) (c[i] - 65248);

				}
			}
			String returnString = new String(c);

			return returnString;
		}

		/**
		 * 
		 * 将以逗号分隔的字符串equipments的第index个值修改为 value<br>
		 * <br>
		 * 例如: 456564,649871,69871,6464,645 index = 3, value = xxxxxx时, 修改后就得到
		 * 456564,649871,69871,xxxxxx,645<br>
		 * <br>
		 * 如果传入的字符穿当中包含了-1, 那么就自动将-1置为空<br>
		 * 例如: "-1, 123, -1" alter 1, "xx" ----> ",xx,"
		 * 
		 * @param equipments
		 *            待修改的字符串
		 * @param index
		 *            修改第index个逗号 和 index + 1 个逗号所夹的字符串
		 * @param value
		 *            修改值
		 */
		public static String alter(String equipments, int index, String value) {

			equipments = equipments.replaceAll(",,", ",-1,");
			equipments = equipments.replace(" ", "");// 消除字符串中所有空格
			if (equipments.endsWith(",")) {
				equipments = equipments + "-1";
			}
			if (equipments.startsWith(",")) {
				equipments = "-1" + equipments;
			}

			String[] equips = equipments.split(",");

			equips[index] = value;

			StringBuffer stringLink = new StringBuffer("");
			stringLink.append(equips[0].equals("-1") ? "" : equips[0]);
			for (int i = 1; i < equips.length; i++) {
				stringLink.append(","
						+ (equips[i].equals("-1") ? "" : equips[i]));
			}

			return stringLink.toString();
		}

		/**
		 * 将ID列表转成字符串形式, 以逗号分隔
		 * 
		 * @param idList
		 * @return
		 */
		public static String toStringSplitByComma(List<Integer> idList) {
			if (idList == null || idList.size() == 0) {
				return "";
			}
			String temp = "";
			for (int i = 0; i < idList.size(); i++) {
				Integer id = idList.get(i);
				if (i == 0) {
					temp += id;
				} else {
					temp += "," + id;
				}
			}
			return temp;
		}

		/**
		 * 将第一个字符转换成大写
		 * 
		 * @param src
		 * @return
		 */
		public static final String firstToUpperCase(String src) {
			return src.replaceFirst(src.substring(0, 1), src.substring(0, 1)
					.toUpperCase());
		}

		/**
		 * 将小写的命名改为常量命名规则
		 * 
		 * @param beanName
		 * @return
		 */
		public static String toConst(String beanName) {
			List<String> split = splitByUpper(beanName);
			String s = "";
			for (String ss : split) {
				s += "_" + ss.toUpperCase();
			}
			while (s.startsWith("_")) {
				s = s.replaceFirst("_", "");
			}
			return s;
		}

		/**
		 * 所有大写字母的索引位置
		 * 
		 * @param beanName
		 * @return
		 */
		private static List<String> splitByUpper(String beanName) {
			List<Integer> ls = new ArrayList<Integer>();
			ls.add(0);

			ls.addAll(getUppers(beanName));
			ls.add(beanName.length());

			List<String> l = new ArrayList<String>();

			for (int i = 0; i < ls.size() - 1; i++) {
				Integer a = ls.get(i);
				Integer b = ls.get(i + 1);
				l.add(beanName.substring(a, b));
			}
			return l;

		}

		private static List<Integer> getUppers(String beanName) {
			List<Integer> ls = new ArrayList<Integer>();
			for (int i = 0; i < beanName.length(); i++) {
				char c = beanName.charAt(i);
				if (Character.isUpperCase(c)) {
					ls.add(i);
				}
			}
			return ls;
		}

		/**
		 * 将字符串的首字母大写，一般用于将表名转换为类名
		 * 
		 * @param tableName
		 *            用下划线连接的全小写字段名, 例如"aaa_bbb_ccc" 表名
		 * @return 类名 "AaaBbbCcc"
		 */
		public static String generateClassName(String tableName) {
			return xxx(tableName).replaceFirst(tableName.substring(0, 1),
					tableName.substring(0, 1).toUpperCase());
		}

		public static String xxx(String columnName) {
			String[] labels = columnName.split("_");
			String r = labels[0];
			for (int i = 1; i < labels.length; i++)
				r = r + generateClassName(labels[i]);
			return r;
		}

		/**
		 * 将类名转换为对象 参数名，即第一个字母小写，如：User - user
		 * 
		 * @param tableName
		 *            表名
		 * @return 参数名
		 */
		public static String generateParameterName(String tableName) {

			String name = hump(tableName);

			if (name.length() > 7) {
				StringBuilder sb = new StringBuilder();

				char[] chars = name.toCharArray();

				for (int i = 0; i < chars.length; i++) {
					char c = chars[i];
					if (i == 0 || Character.isUpperCase(c)) {
						sb.append(Character.toLowerCase(c));
					}
				}
				return sb.toString() + "o";
			} else {
				return name + "o";
			}
		}

		/**
		 * 将普通字符串, 包含下划线的, 转换成驼峰标识 的字符串
		 * 
		 * @param srcText
		 * @return
		 */
		public static String hump(String srcText) {

			// 把下划线之后的一个字符都变为大写
			StringBuilder sb = new StringBuilder();
			char[] chars = srcText.toCharArray();

			for (int i = 0; i < chars.length; i++) {
				if (i != 0 && chars[i - 1] == '_') {
					sb.append(Character.toUpperCase(chars[i]));
				} else {
					sb.append(Character.toLowerCase(chars[i]));
				}
			}

			String text = sb.toString().replace("_", "");
			return text;
		}

		/**
		 * 补起字符串, 用空格补齐, 保证返回值长度大于等于weigth
		 * 
		 * @param k
		 * @param weigth
		 * @return
		 */
		public static String polishing(String k, int weigth) {

			return k + build(weigth - k.length());
		}

		private static String build(int i) {

			StringBuilder sb = new StringBuilder();

			for (int j = 0; j < i; j++) {

				sb.append(" ");
			}

			return sb + "";
		}

		public static String firstToLowerCase(String src) {
			return src.replaceFirst(src.substring(0, 1), src.substring(0, 1)
					.toLowerCase());
		}

		/**
		 * 下划线后的一个字符转为大写, 同时将下划线删除
		 * 
		 * @param f
		 * @return
		 */
		public static String parseAfter_ToUpperCase(String f) {
			// 把下划线之后的一个字符都变为大写
			StringBuilder sb = new StringBuilder();
			char[] chars = f.toCharArray();

			for (int i = 0; i < chars.length; i++) {
				if (i != 0 && chars[i - 1] == '_') {
					sb.append(Character.toUpperCase(chars[i]));
				} else {
					sb.append(chars[i]);
				}
			}

			String text = sb.toString().replace("_", "");
			return text;
		}

		public static final String ASCII_CHARSET = "US-ASCII";

		public static final String ISO88591_CHARSET = "ISO-8859-1";

		/**
		 * 判定是否由纯粹的西方字符组成
		 * 
		 * @param string
		 * @return
		 */
		public static boolean isEnglishAndNumeric(String string) {
			if (string == null || string.length() == 0) {
				return false;
			}
			char[] chars = string.toCharArray();
			int size = chars.length;
			for (int j = 0; j < size; j++) {
				char letter = chars[j];
				if ((97 > letter || letter > 122)
						&& (65 > letter || letter > 90)
						&& (48 > letter || letter > 57)) {
					return false;
				}
			}
			return true;
		}

		/**
		 * 分解字符串
		 * 
		 * @param string
		 * @param tag
		 * @return
		 */
		public static String[] split(final String string, final String tag) {
			StringTokenizer str = new StringTokenizer(string, tag);
			String[] result = new String[str.countTokens()];
			int index = 0;
			for (; str.hasMoreTokens();) {
				result[index++] = str.nextToken();
			}
			return result;
		}

		/**
		 * 过滤指定字符串
		 * 
		 * @param string
		 * @param oldString
		 * @param newString
		 * @return
		 */
		public static final String replace(String string, String oldString,
				String newString) {
			if (string == null)
				return null;
			if (newString == null)
				return string;
			int i = 0;
			if ((i = string.indexOf(oldString, i)) >= 0) {
				char [] string2 = string.toCharArray();
				char [] newString2 = newString.toCharArray();
				int oLength = oldString.length();
				StringBuffer buf = new StringBuffer(string2.length);
				buf.append(string2, 0, i).append(newString2);
				i += oLength;
				int j;
				for (j = i; (i = string.indexOf(oldString, i)) > 0; j = i) {
					buf.append(string2, j, i - j).append(newString2);
					i += oLength;
				}

				buf.append(string2, j, string2.length - j);
				return buf.toString();
			} else {
				return string;
			}
		}

		/**
		 * 不匹配大小写的过滤指定字符串
		 * 
		 * @param line
		 * @param oldString
		 * @param newString
		 * @return
		 */
		public static final String replaceIgnoreCase(String line,
				String oldString, String newString) {
			if (line == null)
				return null;
			String lcLine = line.toLowerCase();
			String lcOldString = oldString.toLowerCase();
			int i = 0;
			if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
				char []line2 = line.toCharArray();
				char []newString2 = newString.toCharArray();
				int oLength = oldString.length();
				StringBuffer buf = new StringBuffer(line2.length);
				buf.append(line2, 0, i).append(newString2);
				i += oLength;
				int j;
				for (j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
					buf.append(line2, j, i - j).append(newString2);
					i += oLength;
				}

				buf.append(line2, j, line2.length - j);
				return buf.toString();
			} else {
				return line;
			}
		}

		/**
		 * 不匹配大小写的过滤指定字符串
		 * 
		 * @param line
		 * @param oldString
		 * @param newString
		 * @param count
		 * @return
		 */
		public static final String replaceIgnoreCase(String line,
				String oldString, String newString, int [] count) {
			if (line == null)
				return null;
			String lcLine = line.toLowerCase();
			String lcOldString = oldString.toLowerCase();
			int i = 0;
			if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
				int counter = 1;
				char []line2 = line.toCharArray();
				char []newString2 = newString.toCharArray();
				int oLength = oldString.length();
				StringBuffer buf = new StringBuffer(line2.length);
				buf.append(line2, 0, i).append(newString2);
				i += oLength;
				int j;
				for (j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
					counter++;
					buf.append(line2, j, i - j).append(newString2);
					i += oLength;
				}

				buf.append(line2, j, line2.length - j);
				count[0] = counter;
				return buf.toString();
			} else {
				return line;
			}
		}

		/**
		 * 以指定条件过滤字符串
		 * 
		 * @param line
		 * @param oldString
		 * @param newString
		 * @param count
		 * @return
		 */
		public static final String replace(String line, String oldString,
				String newString, int []count) {
			if (line == null)
				return null;
			int i = 0;
			if ((i = line.indexOf(oldString, i)) >= 0) {
				int counter = 1;
				char []line2 = line.toCharArray();
				char []newString2 = newString.toCharArray();
				int oLength = oldString.length();
				StringBuffer buf = new StringBuffer(line2.length);
				buf.append(line2, 0, i).append(newString2);
				i += oLength;
				int j;
				for (j = i; (i = line.indexOf(oldString, i)) > 0; j = i) {
					counter++;
					buf.append(line2, j, i - j).append(newString2);
					i += oLength;
				}

				buf.append(line2, j, line2.length - j);
				count[0] = counter;
				return buf.toString();
			} else {
				return line;
			}
		}

		/**
		 * 过滤\n标记
		 * 
		 * @param text
		 * @return
		 */
		public static String[] parseString(String text) {
			int token, index, index2;
			token = index = index2 = 0;
			while ((index = text.indexOf('\n', index)) != -1) {
				token++;
				index++;
			}
			token++;
			index = 0;

			String[] document = new String[token];
			for (int i = 0; i < token; i++) {
				index2 = text.indexOf('\n', index);
				if (index2 == -1) {
					index2 = text.length();
				}
				document[i] = text.substring(index, index2);
				index = index2 + 1;
			}

			return document;
		}

		/**
		 * 检查一组字符串是否完全由中文组成
		 * 
		 * @param str
		 * @return
		 */
		public static boolean isChinaLanguage(String str) {
			char[] chars = str.toCharArray();
			int[] ints = new int[2];
			boolean isChinese = false;
			int length = chars.length;
			byte[] bytes = null;
			for (int i = 0; i < length; i++) {
				bytes = ("" + chars[i]).getBytes();
				if (bytes.length == 2) {
					ints[0] = bytes[0] & 0xff;
					ints[1] = bytes[1] & 0xff;
					if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40
							&& ints[1] <= 0xFE) {
						isChinese = true;
					}
				} else {
					return false;
				}
			}
			return isChinese;
		}

		/**
		 * 判断是否为null
		 * 
		 * @param param
		 * @return
		 */
		public static boolean isEmpty(String param) {
			return param == null || param.length() == 0 || param.trim().equals("");
		}

		/**
		 * 显示指定编码下的字符长度
		 * 
		 * @param encoding
		 * @param str
		 * @return
		 */
		public static int getBytesLengthOfEncoding(String encoding, String str) {
			if (str == null || str.length() == 0)
				return 0;
			try {
				byte []bytes = str.getBytes(encoding);
				int length = bytes.length;
				return length;
			} catch (UnsupportedEncodingException exception) {
				System.err.println(exception.getMessage());
			}
			return 0;
		}

		/**
		 * 转化指定字符串为指定编码格式
		 * 
		 * @param context
		 * @param encoding
		 * @return
		 */
		public static String getSpecialString(String context, String encoding) {
			try {
				ByteArrayInputStream ins = new ByteArrayInputStream(
						context.getBytes());
				InputStreamReader isr = new InputStreamReader(ins, encoding);
				BufferedReader reader = new BufferedReader(isr);
				StringBuffer buffer = new StringBuffer();
				String result;
				while ((result = reader.readLine()) != null) {
					buffer.append(result);
				}
				return buffer.toString();
			} catch (java.lang.Exception ex) {
				return context;
			}
		}

		/**
		 * 检查指定字符串中是否存在中文字符。
		 * 
		 * @param checkStr
		 *            指定需要检查的字符串。
		 * @return 逻辑值（True Or False）。
		 */
		public static final boolean hasChinese(String checkStr) {
			boolean checkedStatus = false;
			boolean isError = false;
			String spStr = " _-";
			int checkStrLength = checkStr.length() - 1;
			for (int i = 0; i <= checkStrLength; i++) {
				char ch = checkStr.charAt(i);
				if (ch < '\176') {
					ch = Character.toUpperCase(ch);
					if (((ch < 'A') || (ch > 'Z'))
							&& ((ch < '0') || (ch > '9'))
							&& (spStr.indexOf(ch) < 0)) {
						isError = true;
					}
				}
			}
			checkedStatus = !isError;
			return checkedStatus;
		}

		/**
		 * 检查是否为纯字母
		 * 
		 * @param value
		 * @return
		 */
		public final static boolean isAlphabet(String value) {
			if (value == null || value.length() == 0)
				return false;
			for (int i = 0; i < value.length(); i++) {
				char c = Character.toUpperCase(value.charAt(i));
				if ('A' <= c && c <= 'Z')
					return true;
			}
			return false;
		}

		/**
		 * 检查是否为字母与数字混合
		 * 
		 * @param value
		 * @return
		 */
		public static boolean isAlphabetNumeric(String value) {
			if (value == null || value.trim().length() == 0)
				return true;
			for (int i = 0; i < value.length(); i++) {
				char letter = value.charAt(i);
				if (('a' > letter || letter > 'z')
						&& ('A' > letter || letter > 'Z')
						&& ('0' > letter || letter > '9'))
					return false;
			}
			return true;
		}

		/**
		 * 过滤首字符
		 * 
		 * @param str
		 * @param pattern
		 * @param replace
		 * @return
		 */
		public static final String replaceFirst(String str, String pattern,
				String replace) {
			int s = 0;
			int e = 0;
			StringBuffer result = new StringBuffer();

			if ((e = str.indexOf(pattern, s)) >= 0) {
				result.append(str.substring(s, e));
				result.append(replace);
				s = e + pattern.length();
			}
			result.append(str.substring(s));
			return result.toString();
		}

		/**
		 * 替换指定字符串
		 * 
		 * @param line
		 * @param oldString
		 * @param newString
		 * @return
		 */
		public static String replaceMatch(String line, String oldString,
				String newString) {
			int i = 0;
			int j = 0;
			if ((i = line.indexOf(oldString, i)) >= 0) {
				char []line2 = line.toCharArray();
				char []newString2 = newString.toCharArray();
				int oLength = oldString.length();
				StringBuffer buffer = new StringBuffer(line2.length);
				buffer.append(line2, 0, i).append(newString2);
				i += oLength;
				for (j = i; (i = line.indexOf(oldString, i)) > 0; j = i) {
					buffer.append(line2, j, i - j).append(newString2);
					i += oLength;
				}
				buffer.append(line2, j, line2.length - j);
				return buffer.toString();
			} else {
				return line;
			}
		}

		/**
		 * 以" "充满指定字符串
		 * 
		 * @param str
		 * @param length
		 * @return
		 */
		public static String fillSpace(String str, int length) {
			int strLength = str.length();
			if (strLength >= length) {
				return str;
			}
			StringBuffer spaceBuffer = new StringBuffer();
			for (int i = 0; i < (length - strLength); i++) {
				spaceBuffer.append(" ");
			}
			return str + spaceBuffer.toString();
		}

		/**
		 * 得到定字节长的字符串，位数不足右补空格
		 * 
		 * @param str
		 * @param length
		 * @return
		 */
		public static String fillSpaceByByte(String str, int length) {
			byte[] strbyte = str.getBytes();
			int strLength = strbyte.length;
			if (strLength >= length) {
				return str;
			}
			StringBuffer spaceBuffer = new StringBuffer();
			for (int i = 0; i < (length - strLength); i++) {
				spaceBuffer.append(" ");
			}
			return str.concat(spaceBuffer.toString());
		}

		/**
		 * 返回指定字符串长度
		 * 
		 * @param s
		 * @return
		 */
		public static int length(String s) {
			if (s == null)
				return 0;
			else
				return s.getBytes().length;
		}

		/**
		 * 获得特定字符总数
		 * 
		 * @param str
		 * @param chr
		 * @return
		 */
		public static int charCount(String str, char chr) {
			int count = 0;
			if (str != null) {
				int length = str.length();
				for (int i = 0; i < length; i++) {
					if (str.charAt(i) == chr) {
						count++;
					}
				}
				return count;
			}
			return count;
		}

		public static byte[] getBytes(String data) {
			if (data == null) {
				throw new IllegalArgumentException("Parameter may not be null");
			}
			try {
				return data.getBytes(ASCII_CHARSET);
			} catch (UnsupportedEncodingException e) {

			}

			return data.getBytes();
		}

		public static String getString(byte[] data, int offset, int length) {
			if (data == null) {
				throw new IllegalArgumentException("Parameter may not be null");
			}
			try {
				return new String(data, offset, length, ASCII_CHARSET);
			} catch (UnsupportedEncodingException e) {

			}

			return new String(data, offset, length);
		}

		public static String getString(byte[] data) {
			return getString(data, 0, data.length);
		}

		public static byte[] getContentBytes(String data, String charset) {
			if (data == null) {
				throw new IllegalArgumentException("Parameter may not be null");
			}

			if ((charset == null) || (charset.equals(""))) {
				charset = ISO88591_CHARSET;
			}
			try {
				return data.getBytes(charset);
			} catch (UnsupportedEncodingException e) {

				try {
					return data.getBytes(ISO88591_CHARSET);
				} catch (UnsupportedEncodingException e2) {

				}
			}

			return data.getBytes();
		}

		public static String getContentString(byte[] data, int offset,
				int length, String charset) {
			if (data == null) {
				throw new IllegalArgumentException("Parameter may not be null");
			}

			if ((charset == null) || (charset.equals(""))) {
				charset = ISO88591_CHARSET;
			}
			try {
				return new String(data, offset, length, charset);
			} catch (UnsupportedEncodingException e) {
				try {
					return new String(data, offset, length, ISO88591_CHARSET);
				} catch (UnsupportedEncodingException e2) {
				}
			}

			return new String(data, offset, length);
		}

		public static String getContentString(byte[] data, String charset) {
			return getContentString(data, 0, data.length, charset);
		}

		public static byte[] getContentBytes(String data) {
			return getContentBytes(data, null);
		}

		public static String getContentString(byte[] data, int offset,
				int length) {
			return getContentString(data, offset, length, null);
		}

		public static String getContentString(byte[] data) {
			return getContentString(data, null);
		}

		public static byte[] getAsciiBytes(String data) {
			if (data == null) {
				throw new IllegalArgumentException("Parameter may not be null");
			}
			try {
				return data.getBytes(ASCII_CHARSET);
			} catch (UnsupportedEncodingException e) {
			}
			throw new RuntimeException("LGame requires ASCII support");
		}

		public static String getAsciiString(byte[] data, int offset, int length) {
			if (data == null) {
				throw new IllegalArgumentException("Parameter may not be null");
			}
			try {
				return new String(data, offset, length, ASCII_CHARSET);
			} catch (UnsupportedEncodingException e) {
			}
			throw new RuntimeException("LGame requires ASCII support");
		}

		public static String getAsciiString(byte[] data) {
			return getAsciiString(data, 0, data.length);
		}

		/**
		 * 找string中第n个 匹配regex的字符串, 如果找不到, 返回null
		 * 
		 * @param string
		 * @param n
		 * @param regex
		 * @return
		 */
		public static String find(String string, int n, String regex) {

			if (n < 1) {
				throw new IllegalArgumentException("参数n必须大于0");
			}

			Pattern compile = Pattern.compile(regex);
			Matcher m = compile.matcher(string);

			while (m.find()) {
				String g = m.group();
				if (n == 1) {
					return g;
				}
				n--;
			}
			return null;
		}

		/**
		 * 将父路径和子路径连接起来
		 * 
		 * @param path
		 *            父路径 绝对路径或者相对路径 以下是所有情况 aa/bbb/cccc/d aa/bbb/cccc/d/
		 *            /aa/bbb/cccc/d /aa/bbb/cccc/d/ D:/aa/bbb/cccc/d
		 *            D:/aa/bbb/cccc/d/ D:/aa/bbb/cccc/d D:/aa/bbb/cccc/d/
		 * @param child
		 *            自路径 相对路径
		 * 
		 *            aa/bbb/cccc/d aa/bbb/cccc/d/ /aa/bbb/cccc/d
		 *            /aa/bbb/cccc/d/
		 * 
		 * @return 必定以/结尾
		 */
		public static String appendFilePath(String path, String child) {
			if (!path.endsWith("/")) {
				path = path + "/";
			}

			if (child.startsWith("/")) {
				child = child.substring(1, child.length());
			}

			if (!child.endsWith("/")) {
				child = child + "/";
			}
			return path + child;
		}

	}

	/**
	 * 安全相关
	 * 
	 * @author 林岑
	 * 
	 */
	public static final class Secure {

		/**
		 * 文本的MD5
		 * 
		 * @param text
		 * @return
		 */
		public static String md5(String text) {
			return md5(text.getBytes());
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

		public static String MD5Encrypt(String inStr) {
			MessageDigest md = null;
			String outStr = null;
			try {
				md = MessageDigest.getInstance("MD5");
				byte[] digest = md.digest(inStr.getBytes());
				outStr = bytetoString(digest);
			} catch (NoSuchAlgorithmException nsae) {
				nsae.printStackTrace();
			}
			return outStr;
		}

		private static String bytetoString(byte[] digest) {
			String str = "";
			String tempStr = "";
			for (int i = 1; i < digest.length; i++) {
				tempStr = (Integer.toHexString(digest[i] & 0xff));

				if (tempStr.length() == 1) {
					str = str + "0" + tempStr;
				} else {
					str = str + tempStr;
				}
			}
			return str.toLowerCase();
		}

	}

	public static final class Buffer {

		/**
		 * 将字符串放入一个ByteBuffer中
		 * 
		 * @param text
		 * @return
		 */
		public static ByteBuffer toByteBuffer(String text) {
			byte[] data = text.getBytes();
			ByteBuffer buffer = ByteBuffer.allocate(data.length);
			buffer.put(data);
			buffer.flip();
			return buffer;
		}

	}

	public static final class Exception {

		public static RuntimeException toRuntimeException(Throwable e) {
			return new RuntimeException(e);
		}

		public static RuntimeException toRuntimeException(String message,
				Throwable e) {
			return new RuntimeException(message, e);
		}

		/**
		 * 如果o 为空, 抛出空指针异常, 异常中将 args全部打印出来
		 * 
		 * @param o
		 * @param args
		 */
		public static void checkNull(Object o, Object... args) {
			if (o == null) {
				throw new NullPointerException(Arrays.toString(args));
			}
		}

	}

	public static final class Collection {

		/**
		 * 在列表中截取元素, 如果数量大于c, 那么取前c个元素, 如果不足, 返回全部元素
		 * 
		 * @param all
		 * @param c
		 * @return
		 */
		public static <T> java.util.List<T> sub(java.util.List<T> all, int c) {

			if (all.size() < c) {

				return new ArrayList<T>(all);

			} else {

				return new ArrayList<T>(all.subList(0, c));
			}
		}

		/**
		 * 打乱列表
		 */
		public static void upset(java.util.List all) {

			java.util.List ls = new ArrayList(all);

			all.clear();

			while (ls.size() > 0) {

				Object o = ls.remove(Util.R.nextInt(ls.size()));

				all.add(o);
			}

			// Collections.sort(all, new Comparator<Object>() {
			//
			// @Override
			// public int compare(Object o1, Object o2) {
			// return Util.getRandomInt(-5, +5);
			// }
			// });

		}

		/**
		 * 用分隔符sp将 s的每个元素连接起来
		 * 
		 * @param sp
		 * @param s
		 * @return
		 */
		public static <T> String linkWith(String sp, T... s) {
			Iterator<T> it = s.iterator();
			StringBuilder sb = new StringBuilder();
			while (it.hasNext()) {
				T id = it.next();
				sb.append(id);
				if (it.hasNext()) {
					sb.append(sp);
				}
			}
			return sb.toString();
		}

		/**
		 * 合并两个列表
		 * 
		 * @param a
		 * @param b
		 * @return
		 */
		public static <T> java.util.List<T> merge(java.util.List<T> a,
				java.util.List<T> b) {

			java.util.List<T> ls = new ArrayList<T>();

			ls.addAll(a);

			ls.addAll(b);

			return ls;
		}

		/**
		 * 
		 * 将except中的元素, 从all中剔除后返回
		 * 
		 * @param except
		 *            被剔除的
		 * @param all
		 *            所有的
		 * @return
		 */
		public static <T> java.util.List<T> reject(
				java.util.Collection<T> except, java.util.Collection<T> all1) {

			java.util.List<T> all = new ArrayList<T>(all1);

			Iterator<T> it = all.iterator();

			while (it.hasNext()) {

				T hero = it.next();

				if (except.contains(hero)) {

					it.remove();
				}
			}

			return all;
		}

		/**
		 * 找出两个列表当中相同的元素
		 * 
		 * @param all1
		 * @param all2
		 * @return
		 */
		public static <T> java.util.List<T> getSame(java.util.List<T> all1,
				java.util.List<T> all2) {

			java.util.List<T> list = new ArrayList<T>(all1);

			Iterator<T> it = list.iterator();

			while (it.hasNext()) {

				T next = it.next();

				if (!all2.contains(next)) {

					it.remove();
				}
			}

			return list;
		}

		/**
		 * 随机返回列表中的一个对象
		 * 
		 * @param ls
		 * @return
		 */
		public static <T> T getRandomOne(java.util.Collection<T> ls) {

			if (ls.isEmpty()) {
				throw new IllegalArgumentException("列表不能为空!");
			}

			ArrayList<T> arrayList = new ArrayList<T>(ls);
			return arrayList.get(Util.R.nextInt(arrayList.size()));
		}

		/**
		 * 获得最大值 注意列表不要为空
		 */
		public static int getMax(java.util.Collection<Integer> allKey) {

			if (allKey.isEmpty()) {
				throw new IllegalArgumentException("列表不能为空!");
			}

			int max = Integer.MIN_VALUE;

			for (Integer value : allKey) {

				if (value > max) {

					max = value;
				}
			}

			return max;
		}

		/**
		 * 
		 * 注意列表不要为空
		 * 
		 * @param allKey
		 * @return
		 */
		public static int getMin(java.util.Collection<Integer> allKey) {

			if (allKey.isEmpty()) {
				throw new IllegalArgumentException("列表不能为空!");
			}

			int min = Integer.MAX_VALUE;

			for (Integer value : allKey) {

				if (value < min) {

					min = value;
				}
			}

			return min;
		}

		/**
		 * 第一个元素
		 * 
		 * @param f
		 * @return
		 */
		public static <T> T getFirst(java.util.Collection<T> f) {
			for (T t : f) {
				return t;
			}
			throw new IllegalArgumentException("容器不可为空");
		}

		public static <T> List<T> toList(T[] info) {
			List<T> ls = Lists.newArrayList();
			for (T t : info) {
				ls.add(t);
			}
			return ls;
		}

		/**
		 * 吧null列表转为空列表
		 * 
		 * @param ts
		 * @return
		 */
		public static <T> List<T> nullToEmpty(List<T> ts) {
			if (ts == null) {
				ts = Lists.newArrayList();
			}
			return ts;
		}

		/**
		 * 将ls中的所有元素的某个值, 作为列表返回
		 * 
		 * @param fetcher
		 * @param ls
		 * @return
		 */
		public static <T, R> java.util.List<R> getListByOneFields(
				Fetcher<T, R> fetcher, java.util.Collection<T> ls) {
			java.util.List<R> list = Lists.newArrayList();
			for (T t : ls) {
				list.add(fetcher.get(t));
			}
			return list;
		}

		/**
		 * 将用某种分隔符分隔的字符串, 转换成整数列表
		 * 
		 * @param string
		 * @return
		 */
		public static java.util.List<Integer> getIntegers(String string) {
			StringTokenizer s = new StringTokenizer(string, " ,\\|/\\.-=+':");
			ArrayList<Integer> ls = Lists.newArrayList();

			while (s.hasMoreElements()) {
				String object = (String) s.nextElement();
				String trim = object.trim();
				if (trim.isEmpty()) {
					continue;
				}
				ls.add(new Integer(trim));
			}
			return ls;
		}

		/**
		 * 将all用sp分隔符连接起来
		 * 
		 * @param sp
		 * @param all
		 * @param fetcher
		 * @return
		 */
		public static <T, R> String linkWith(String sp,
				java.util.Collection<T> all, Fetcher<T, R> fetcher) {
			StringBuilder sb = new StringBuilder();
			Iterator<T> it = all.iterator();
			while (it.hasNext()) {
				T s = (T) it.next();
				sb.append(fetcher.get(s));
				if (it.hasNext()) {
					sb.append(sp);
				}
			}
			return sb + "";
		}

		/**
		 * 将列表ls中的所有元素的某个整型值 装载一个列表中返回
		 * 
		 * @param fetcher
		 * @param ls
		 * @return
		 */
		public static <T> int[] getArrayByOneFields(IntegerFetcher<T> fetcher,
				java.util.Collection<T> ls) {

			int[] a = new int[ls.size()];
			int i = 0;
			for (T t : ls) {
				a[i] = fetcher.get(t);
				i++;
			}

			return a;
		}

		public static <T> String linkWith(String sp, T[] info,
				Fetcher<T, String> fetcher) {
			List<T> list = toList(info);
			return linkWith(sp, list, fetcher);
		}

		/**
		 * 用分隔符sp将 s的每个元素连接起来
		 * 
		 * @param sp
		 * @param s
		 * @return
		 */
		public static <T> String linkWith(String sp, java.util.Collection<T> s) {
			
			Iterator<T> it = s.iterator();
			StringBuilder sb = new StringBuilder();
			while (it.hasNext()) {
				T id = it.next();
				sb.append(id);
				if (it.hasNext()) {
					sb.append(sp);
				}
			}
			return sb.toString();
		}
		
		public static<T> String linkWith(List<T> ids, String text) {
			Iterator<T> it = ids.iterator();
			StringBuilder sb = new StringBuilder();
			while (it.hasNext()) {
				T id = it.next();
				sb.append(id);
				if (it.hasNext()) {
					sb.append(text);
				}
			}
			return sb.toString();
		}

	}

	public static final class Array {

		/**
		 * 将一个列表转换成数组
		 * 
		 * @param ls
		 * @return
		 */
		public static final int[] toArray(java.util.List<Integer> ls) {
			int[] a = new int[ls.size()];
			for (int i = 0; i < ls.size(); i++) {
				a[i] = ls.get(i);
			}
			return a;
		}

		/**
		 * 将一个数组转换成列表
		 * 
		 * @param ls
		 * @return
		 */
		public static final List<Integer> toList(int[] ls) {
			List<Integer> a = Lists.newArrayList();
			for (int i : ls) {
				a.add(i);
			}
			return a;
		}

		/**
		 * 判断数组中所有元素是否相等
		 * 
		 * @param suitNo
		 * @return
		 */
		public static boolean allIsTheSame(int... suitNo) {
			for (int i = 0; i < suitNo.length - 1; i++) {
				if (suitNo[i] != suitNo[i + 1]) {
					return false;
				}
			}
			return true;
		}

		/**
		 * 将一个逗号分隔的字符串列表转换为整型数组<br>
		 * 前提intList格式合法.<br>
		 * 比如 1111,213,646,674987,16546,333<br>
		 * 
		 * 将用逗号分隔的int字符串, 转换成int数组<br>
		 * <br>
		 * 如果其中有空白, 那么那个值就等于 -1<br>
		 * 1111,213,646,,16546,333 ------> 1111,213,646,-1,16546,333
		 * 1111,213,646,,16546,333, ------> 1111,213,646,-1,16546,333,-1
		 */
		public static int[] asArray(String intList) {

			if (intList == null || intList.equals("")) {
				return new int[0];
			}

			intList = intList.replaceAll(",,", ",-1,");
			intList = intList.endsWith(",") ? intList += "-1" : intList;
			String[] idListString = intList.split(",");
			int[] r = new int[idListString.length];
			for (int i = 0; i < r.length; i++) {
				if (idListString[i].equals("")) {
					r[i] = -1;
				} else {
					r[i] = Integer.parseInt(idListString[i].trim());
				}
			}
			return r;

		}

		/**
		 * 计算数组前n项和
		 * 
		 * @param is
		 *            被计算数组
		 * @param n
		 *            前n项
		 * @return 前n项和
		 */
		public static int sumOfSubArray(int[] is, int n) {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				sum += is[i];
			}
			return sum;
		}

		/**
		 * 将整型数值列表转成字符串形式, 以逗号分隔
		 * 
		 * @param idList
		 * @return
		 */
		public static String toStringSplitByComma(java.util.List<Integer> idList) {
			if (idList == null || idList.size() == 0) {
				return "";
			}
			String temp = "";
			for (int i = 0; i < idList.size(); i++) {
				Integer id = idList.get(i);
				if (i == 0) {
					temp += id;
				} else {
					temp += "," + id;
				}
			}
			return temp;
		}

		/**
		 * 把用逗号分隔的字符串转换为整形数组
		 * 
		 * @param str
		 * @return
		 */
		public static int[] toIntegerArray(String str) {
			String[] arr = str.split(",");
			int[] res = new int[arr.length];
			for (int i = 0; i < arr.length; i++) {
				res[i] = Integer.parseInt(arr[i]);
			}
			return res;
		}

		/**
		 * 获得最大值 注意列表不要为空
		 */
		public static int getMax(int[] a) {
			if (a.length == 0) {
				throw new IllegalArgumentException("列表不能为空!");
			}

			List<Integer> array = toList(a);

			if (array.isEmpty()) {
				return 0;
			}

			return Util.Collection.getMax(array);
		}

		/**
		 * 获得最小值 注意列表不要为空
		 * 
		 * @param a
		 * @return
		 */
		public static int getMin(int[] a) {
			if (a.length == 0) {
				throw new IllegalArgumentException("列表不能为空!");
			}
			List<Integer> array = toList(a);
			return Util.Collection.getMin(array);
		}

		/**
		 * 和
		 * 
		 * @param a
		 * @return
		 */
		public static int sum(int... a) {
			int sum = 0;
			for (int i : a) {
				sum += i;
			}
			return sum;
		}

		/**
		 * 兩個數組內容是否完全星等
		 * 
		 * @param vs
		 * @param values
		 * @return
		 */
		public static boolean equals(int[][] vs, int[][] values) {
			try {
				for (int i = 0; i < values.length; i++) {
					int[] js = values[i];
					for (int j = 0; j < js.length; j++) {
						if (js[j] != vs[i][j]) {
							return true;
						}
					}
				}
				return false;
			} catch (java.lang.Exception e) {
				return false;
			}
		}

		/**
		 * 拷贝一个二维数组
		 * 
		 * @param values
		 * @return
		 */
		public static int[][] copy(int[][] values) {
			int[][] vs = new int[values.length][];
			for (int i = 0; i < values.length; i++) {
				vs[i] = Arrays.copyOf(values[i], values[i].length);

			}
			return vs;
		}

	}

	public static final class Thread {

		/**
		 * Thread.sleep
		 * 
		 * @param m
		 */
		public static void sleep(long m) {

			try {

				java.lang.Thread.sleep(m);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

	/**
	 * 随机工具
	 * 
	 * @author 林岑
	 */
	public static final class Random {

		/**
		 * 随机返回列表中的一个对象
		 * 
		 * @param ls
		 * @return
		 */
		public static <T> T getRandomOne(java.util.Collection<T> ls) {

			return Collection.getRandomOne(ls);
		}

		/**
		 * 传入概率序列 , 随机生成对应int数值(索引位置) 通过概率序列随机生成 0 - (length - 1) 这些整型数字,
		 * 其各自的概率为is[0] --- is[length - 1]
		 * 
		 * 例如:产生 3 的概率就是is[2]
		 * 
		 * @param is
		 *            概率序列 数组
		 * @return 该数组某个下标
		 */
		public static int getRandomIndex(int... is) {
			if (is.length == 0) {
				throw new IllegalArgumentException("数组长度不能为0");
			}
			synchronized (Util.R) {
				int v;
				try {
					v = Util.R.nextInt(Array.sumOfSubArray(is, is.length));
				} catch (java.lang.Exception e) {
					System.err.println("Util.Random.getRandomIndex()"
							+ Arrays.toString(is));
					throw Util.Exception.toRuntimeException(e);
				}
				for (int i = 0; i < is.length; i++) {
					if (v < Array.sumOfSubArray(is, i + 1)) {
						return i;
					}
				}
				return -1;
			}
		}

		/**
		 * 传入概率序列 , 随机生成对应int数值(索引位置) 通过概率序列随机生成 0 - (length - 1) 这些整型数字,
		 * 其各自的概率为is[0] --- is[length - 1]
		 * 
		 * 例如:产生 3 的概率就是is[2]
		 * 
		 * @param is
		 *            概率序列 数组
		 * @return 该数组某个下标
		 */
		public static int getRandomIndex(java.util.List<Integer> s) {
			int[] is = Array.toArray(s);
			return getRandomIndex(is);
		}

		/**
		 * 随机产生 min - max中的数值
		 * 
		 * 比如 (min = -1, max = 3) 那么就会产生 {-1, 0, 1, 2, 3}
		 * 
		 * @param min
		 * @param max
		 * @return
		 */
		public static int get(int min, int max) {

			int n = max - min + 1;

			return Util.R.nextInt(n) + min;
		}

		/**
		 * 返回 min - max 之间的一个随机数
		 * 
		 * @param min
		 * @param max
		 * @return
		 */
		public static double get(double min, double max) {
			return Util.R.nextDouble() * (max - min) + min;
		}

		/**
		 * 以一定几率发生某件事发生 前置条件: 0 <= percent <= 1 如果 percent >= 1, 那么事件就始终发生 如果
		 * percent <= 0, 那么时间就从不发生
		 * 
		 * @param percent
		 *            发生的几率
		 * @return true: 发生, false: 未发生
		 */
		public static boolean isHappen(float percent) {
			synchronized (Util.R) {
				return percent > Util.R.nextFloat();
			}
		}

		/**
		 * 在players中随机获取一个元素, 不可能获得player
		 * 
		 * @param player
		 * @param players
		 * @return
		 */
		public static <T> T getRandomOneWithOut(T player, List<T> players) {
			List<T> ls = Lists.newArrayList(players);
			ls.remove(player);
			return getRandomOne(ls);
		}

		/**
		 * 在最大值最小值之间随机
		 * 
		 * @param string
		 *            min,max
		 * @return
		 */
		public static int getRandomByMinMax(String string) {
			String[] split = string.split(",");
			int max = new Integer(split[1]);
			int min = new Integer(split[0]);
			return get(min, max);
		}

		/**
		 * 随机移除一个
		 */
		public static <T> T removeRandomOne(List<T> all) {
			T one = getRandomOne(all);
			all.remove(one);
			return one;
		}
	}

	public static class Clazz {

		/**
		 * 从包package中获取所有的Class信息
		 * 
		 * @param pack
		 *            包路径,类似于game.packages.BasePacket
		 * @return
		 */
		public static List<Class<?>> getClasses(String pack) {

			// 第一个class类的集合
			List<Class<?>> classes = new ArrayList<Class<?>>();

			boolean recursive = true;// 是否循环迭代

			String packageName = pack;// 获取包的名字 并进行替换
			String packageDirName = packageName.replace('.', '/');// 定义一个枚举的集合
			// 并进行循环来处理这个目录下的things

			Enumeration<URL> dirs;
			try {
				dirs = java.lang.Thread.currentThread().getContextClassLoader()
						.getResources(packageDirName);
				while (dirs.hasMoreElements()) {

					URL url = dirs.nextElement();
					String protocol = url.getProtocol();// 得到协议的名称

					if ("file".equals(protocol)) {// 如果是以文件的形式保存在服务器上
						// System.err.println("file类型的扫描");
						String filePath = URLDecoder.decode(url.getFile(),
								"UTF-8");// 获取包的物理路径
						findAndAddClassesInPackageByFile(packageName, filePath,
								recursive, classes);// 以文件的方式扫描整个包下的文件并添加到集合中

					} else if ("jar".equals(protocol)) {

						// System.err.println("jar类型的扫描");
						JarFile jar;
						try {

							jar = ((JarURLConnection) url.openConnection())
									.getJarFile();// 从此jar包
							// 得到一个枚举类

							Enumeration<JarEntry> entries = jar.entries();// 同样的进行循环迭代

							while (entries.hasMoreElements()) {

								JarEntry entry = entries.nextElement();
								String name = entry.getName();

								if (name.charAt(0) == '/') {// 如果是以/开头的
									name = name.substring(1);
								}
								// 如果前半部分和定义的包名相同
								if (name.startsWith(packageDirName)) {
									int idx = name.lastIndexOf('/');
									// 如果以"/"结尾 是一个包
									if (idx != -1) {
										// 获取包名 把"/"替换成"."
										packageName = name.substring(0, idx)
												.replace('/', '.');
									}
									// 如果可以迭代下去 并且是一个包
									if ((idx != -1) || recursive) {
										// 如果是一个.class文件 而且不是目录
										if (name.endsWith(".class")
												&& !entry.isDirectory()) {
											String className = name.substring(
													packageName.length() + 1,
													name.length() - 6);// 去掉后面的".class"
											// 获取真正的类名

											String string = null;
											try {
												// 添加到classes
												string = packageName + '.'
														+ className;
												// + string);
												classes.add(Class
														.forName(string));
											} catch (ClassNotFoundException e) {
												e.printStackTrace();
											}
										}
									}
								}
							}
						} catch (IOException e) {
							// log.error("在扫描用户定义视图时从jar包获取文件出错");
							e.printStackTrace();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return classes;
		}

		/**
		 * 以文件的形式来获取包下的所有Class
		 * 
		 * @param packageName
		 * @param packagePath
		 * @param recursive
		 * @param classes
		 */
		private static void findAndAddClassesInPackageByFile(
				String packageName, String packagePath,
				final boolean recursive, List<Class<?>> classes) {

			packageName = replaceFirstDot(packageName);

			java.io.File dir = new java.io.File(packagePath);// 获取此包的目录 建立一个File

			if (!dir.exists() || !dir.isDirectory()) {
				// log.warn("用户定义包名 " + packageName + " 下没有任何文件");
				return;
			}

			java.io.File[] dirfiles = dir.listFiles();
			for (java.io.File file : dirfiles) {
				String name = file.getName();

				if (file.isDirectory()) {
					findAndAddClassesInPackageByFile(packageName + "." + name,
							file.getAbsolutePath(), recursive, classes);
				} else {
					if (!name.endsWith(".class") && !name.endsWith(".java")) {
						continue;
					}

					String className = name.substring(0, name.length() - 6);// 如果是java类文件,
					// 去掉后面的.class
					// 只留下类名

					try {
						ClassLoader ld = java.lang.Thread.currentThread()
								.getContextClassLoader();
						String string = packageName + '.' + className;

						string = replaceFirstDot(string);
						classes.add(ld.loadClass(string));
					} catch (ClassNotFoundException e) {
						throw Util.Exception.toRuntimeException(e);
					}
				}
			}
		}

		/**
		 * 去掉第一个点号
		 * 
		 * @param packageName
		 */
		private static String replaceFirstDot(String packageName) {
			if (packageName.startsWith(".")) {
				return packageName.substring(1, packageName.length());
			}
			return packageName;
		}

	}

	/**
	 * 字节处理
	 * 
	 * @author 林岑
	 * 
	 */
	public static class Byte {

		/**
		 * 合并两个数组
		 * 
		 * @param data1
		 * @param data2
		 * @return
		 */
		public static byte[] merge(byte[] data1, byte[] data2) {
			byte[] r = new byte[data1.length + data2.length];
			System.arraycopy(data1, 0, r, 0, data1.length);
			System.arraycopy(data2, 0, r, data1.length, data2.length);
			return r;
		}

		public static int toInt(byte b1, byte b2, byte b3, byte b4) {
			int intValue = 0;

			intValue |= (b1 & 0xff) << 24;
			intValue |= (b2 & 0xff) << 16;
			intValue |= (b3 & 0xff) << 8;
			intValue |= (b4 & 0xff);

			return intValue;
		}

		public static short toShort(byte b1, byte b2) {
			short value = 0;

			value |= (b1 & 0xff) << 8;
			value |= (b2 & 0xff);

			return value;
		}
	}

}
