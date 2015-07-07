package cn.javaplus.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import cn.javaplus.code.KeyGenerator;
import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.set.Sets;
import cn.javaplus.comunication.ProtocolUser;
import cn.javaplus.exception.FileNotFoundRuntimeException;
import cn.javaplus.exception.IORuntimeException;
import cn.javaplus.exception.SQLRuntimeException;
import cn.javaplus.file.FileLinesReader;
import cn.javaplus.file.IProperties;
import cn.javaplus.io.LinesReader;
import cn.javaplus.log.Log;
import cn.javaplus.math.MathExpress;
import cn.javaplus.random.Fetcher;
import cn.javaplus.random.IntegerFetcher;
import cn.javaplus.random.WeightFetcher;
import cn.javaplus.random.Weightable;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.time.TimeChecker;
import cn.javaplus.time.UnknownDateException;
import cn.javaplus.time.UnknownWeek;
import cn.javaplus.web.WebContentFethcer;

/**
 * 工具类
 * 
 * @author 林岑
 * @since 2012年1月7日 09:55:08
 */
public class Util {

	public static class Token {

		private static final int KEY_LEN = 8;

		public static String generate(String tokenKey, String uname) {
			String key = KeyGenerator.getRandomString(KEY_LEN).toLowerCase();
			key = Util.Secure.md5(uname + tokenKey + key) + key;
			return key;
		}

		/**
		 * @param uname
		 * @param token
		 * @return 是否正确
		 */
		public static boolean isTokenRight(String tokenKey, String uname,
				String token) {

			String key = token.substring(token.length() - KEY_LEN,
					token.length());
			String m = token.substring(0, token.length() - KEY_LEN);

			String md5 = Util.Secure.md5(uname + tokenKey + key);
			return m.equals(md5);
		}

	}

	public static class ID {
		private static final int INIT_ID = 1000000;
		private static AtomicInteger ID = new AtomicInteger(INIT_ID);

		/**
		 * 创建一个绝对唯一的ID
		 */
		public static final String createId() {
			int id = ID.addAndGet(1);
			if (id >= INIT_ID * 10 - 1) {
				id = INIT_ID;
				ID.set(id);
			}
			return System.currentTimeMillis() + "" + id;
		}
	}

	public static class FileWithRelativePath {

		private java.io.File file;
		private String path;

		public FileWithRelativePath(java.io.File file, String path) {
			this.file = file;
			this.path = path;
		}

		public java.io.File getFile() {
			return file;
		}

		public String getRelativePath() {
			return path;
		}

		public String getName() {
			return file.getName();
		}
	}

	private static class MyProperty implements IProperties {

		private Properties properties;

		public MyProperty(Properties properties) {
			this.properties = properties;
		}

		@Override
		public String getProperty(Object key) {
			return properties.getProperty(key.toString());
		}

		@Override
		public int getInt(Object key) {
			return new Integer(getProperty(key));
		}

		@Override
		public Object get(String key) {
			return properties.get(key);
		}

		@Override
		public Object setProperty(String key, String value) {
			return properties.setProperty(key, value);
		}

		@Override
		public void load(Reader reader) throws IOException {
			properties.load(reader);
		}

		@Override
		public int size() {
			return properties.size();
		}

		@Override
		public boolean isEmpty() {
			return properties.isEmpty();
		}

		@Override
		public Enumeration<Object> keys() {
			return properties.keys();
		}

		@Override
		public Enumeration<Object> elements() {
			return properties.elements();
		}

		@Override
		public boolean contains(Object value) {
			return properties.contains(value);
		}

		@Override
		public boolean containsValue(Object value) {
			return properties.containsValue(value);
		}

		@Override
		public boolean containsKey(Object key) {
			return properties.containsKey(key);
		}

		@Override
		public Object get(Object key) {
			return properties.get(key);
		}

		@Override
		public void load(InputStream inStream) throws IOException {
			properties.load(inStream);
		}

		@Override
		public Object put(Object key, Object value) {
			return properties.put(key, value);
		}

		@Override
		public Object remove(Object key) {
			return properties.remove(key);
		}

		@Override
		public void putAll(Map<? extends Object, ? extends Object> t) {
			properties.putAll(t);
		}

		@Override
		public void clear() {
			properties.clear();
		}

		@Override
		public Object clone() {
			return properties.clone();
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			Set<Object> set = properties.keySet();
			set = new TreeSet<Object>(set);
			for (Object k : set) {
				sb.append(k + "=" + getProperty(k) + "\r");
			}
			return sb.toString();
		}

		@Override
		public Set<Object> keySet() {
			return properties.keySet();
		}

		@Override
		public Set<Entry<Object, Object>> entrySet() {
			return properties.entrySet();
		}

		@Override
		public java.util.Collection<Object> values() {
			return properties.values();
		}

		@Override
		public boolean equals(Object o) {
			return properties.equals(o);
		}

		@SuppressWarnings("deprecation")
		@Override
		public void save(OutputStream out, String comments) {
			properties.save(out, comments);
		}

		@Override
		public int hashCode() {
			return properties.hashCode();
		}

		@Override
		public void store(Writer writer, String comments) throws IOException {
			properties.store(writer, comments);
		}

		@Override
		public void store(OutputStream out, String comments) throws IOException {
			properties.store(out, comments);
		}

		@Override
		public void loadFromXML(InputStream in) throws IOException,
				InvalidPropertiesFormatException {
			properties.loadFromXML(in);
		}

		@Override
		public void storeToXML(OutputStream os, String comment)
				throws IOException {
			properties.storeToXML(os, comment);
		}

		@Override
		public void storeToXML(OutputStream os, String comment, String encoding)
				throws IOException {
			properties.storeToXML(os, comment, encoding);
		}

		@Override
		public String getProperty(String key) {
			return properties.getProperty(key);
		}

		@Override
		public String getProperty(String key, String defaultValue) {
			return properties.getProperty(key, defaultValue);
		}

		@Override
		public Enumeration<?> propertyNames() {
			return properties.propertyNames();
		}

		@Override
		public Set<String> stringPropertyNames() {
			return properties.stringPropertyNames();
		}

		@Override
		public void list(PrintStream out) {
			properties.list(out);
		}

		@Override
		public void list(PrintWriter out) {
			properties.list(out);
		}

	}

	public static final java.util.Random R = new java.util.Random();

	/**
	 * 中文工具
	 * 
	 * @author 林岑
	 * 
	 */
	public static final class Chinese {
		static java.util.Map<String, Integer> unitMap = new java.util.HashMap<String, Integer>();
		static java.util.Map<String, Integer> numMap = new java.util.HashMap<String, Integer>();
		static {
			unitMap.put("十", 10);
			unitMap.put("百", 100);
			unitMap.put("千", 1000);
			unitMap.put("万", 10000);
			unitMap.put("亿", 100000000);

			numMap.put("零", 0);
			numMap.put("一", 1);
			numMap.put("二", 2);
			numMap.put("三", 3);
			numMap.put("四", 4);
			numMap.put("五", 5);
			numMap.put("六", 6);
			numMap.put("七", 7);
			numMap.put("八", 8);
			numMap.put("九", 9);
		}

		public static void main(String[] args) {
			System.out.println(chineseToDigit("一百四十二"));
		}

		/**
		 * 汉字转数字 不支持小数
		 * 
		 * @param chinese
		 */
		public static long chineseToDigit(String chinese) {

			chinese = chinese.replaceAll("1", "一");
			chinese = chinese.replaceAll("2", "二");
			chinese = chinese.replaceAll("3", "三");
			chinese = chinese.replaceAll("4", "四");
			chinese = chinese.replaceAll("5", "五");
			chinese = chinese.replaceAll("6", "六");
			chinese = chinese.replaceAll("7", "七");
			chinese = chinese.replaceAll("8", "八");
			chinese = chinese.replaceAll("9", "九");
			chinese = chinese.replaceAll("0", "零");

			if (chinese.contains("点")) {
				throw new RuntimeException("不支持小数");
			}

			// 队列
			List<Long> queue = new ArrayList<Long>();
			long tempNum = 0;
			for (int i = 0; i < chinese.length(); i++) {
				char bit = chinese.charAt(i);
				// 数字
				if (numMap.containsKey(bit + "")) {

					tempNum = tempNum + numMap.get(bit + "");

					// 一位数、末位数、亿或万的前一位进队列
					if (chinese.length() == 1
							| i == chinese.length() - 1
							| (i + 1 < chinese.length() && (chinese
									.charAt(i + 1) == '亿' | chinese
									.charAt(i + 1) == '万'))) {
						queue.add(tempNum);
					}
				}
				// 单位
				else if (unitMap.containsKey(bit + "")) {

					// 遇到十 转换为一十、临时变量进队列
					if (bit == '十') {
						if (tempNum != 0) {
							tempNum = tempNum * unitMap.get(bit + "");
						} else {
							tempNum = 1 * unitMap.get(bit + "");
						}
						queue.add(tempNum);
						tempNum = 0;
					}

					// 遇到千、百 临时变量进队列
					if (bit == '千' | bit == '百') {
						if (tempNum != 0) {
							tempNum = tempNum * unitMap.get(bit + "");
						}
						queue.add(tempNum);
						tempNum = 0;
					}

					// 遇到亿、万 队列中各元素依次累加*单位值、清空队列、新结果值进队列
					if (bit == '亿' | bit == '万') {
						long tempSum = 0;
						if (queue.size() != 0) {
							for (int j = 0; j < queue.size(); j++) {
								tempSum += queue.get(j);
							}
						} else {
							tempSum = 1;
						}
						tempNum = tempSum * unitMap.get(bit + "");
						queue.clear();// 清空队列
						queue.add(tempNum);// 新结果值进队列
						tempNum = 0;
					}
				}
			}

			// output
			long sum = 0;
			for (Long i : queue) {
				sum += i;
			}
			return sum;
		}

		/**
		 * 获得这段文本的汉语拼音, 驼峰标识
		 * 
		 * 比如 "重庆市" 返回 "ChongQingShi"
		 * 
		 * @param text
		 * @return
		 */
		public static String getPinYinHump(String text) {
			String[] split = text.split("");

			StringPrinter sp = new StringPrinter();
			for (String string : split) {
				if (string.isEmpty()) {
					continue;
				}

				char c = string.toCharArray()[0];
				String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);

				if (pinyinArray == null || pinyinArray.length == 0) {
					continue;
				}

				sp.print(getPinYinUpper(pinyinArray[0]));
			}
			return sp.toString();
		}

		private static Object getPinYinUpper(String src) {
			String firstToUpperCase = Util.Str.firstToUpperCase(src);
			return firstToUpperCase.replaceAll("[0-9]", "");
		}

	}

	/**
	 * 数学
	 * 
	 * @author 林岑
	 * 
	 */
	public static final class Math {

		/**
		 * 计算一个表达式的值
		 * 
		 * @param exp
		 *            表达式
		 * @param precision
		 *            精度
		 * @return 计算结果
		 */
		public static String calc(String exp, int precision) {
			MathExpress me = new MathExpress(exp, precision);
			return me.caculate();
		}

		/**
		 * 计算一个表达式的值
		 * 
		 * @param exp
		 *            表达式
		 * @return 计算结果(精度默认为20)
		 */
		public static String calc(String exp) {
			return calc(exp, 20);
		}

		public static int min(int... x) {
			int min = Integer.MAX_VALUE;
			for (int v : x) {
				if (v < min)
					min = v;
			}
			return min;
		}

		public static long sum(long... v) {
			long sum = 0;
			for (long i : v) {
				sum += i;
			}
			return sum;
		}
	}

	public static final class JavaType {

		private static Map<String, String> map = new HashMap<String, String>();
		private static Set<String> digitTypes = Sets.newHashSet();
		private static Map<Class<?>, Class<?>> SAME_TYPES = new HashMap<Class<?>, Class<?>>();

		static {
			map.put("int", "Integer");
			map.put("boolean", "Boolean");
			map.put("Boolean", "Boolean");
			map.put("float", "Float");
			map.put("byte", "Byte");
			map.put("double", "Double");
			map.put("long", "Long");
			map.put("String", "String");
			map.put("short", "Short");

			add(digitTypes, "int", "Integer");
			add(digitTypes, "float", "Float");
			add(digitTypes, "byte", "Byte");
			add(digitTypes, "double", "Double");
			add(digitTypes, "long", "Long");
			add(digitTypes, "short", "Short");

			SAME_TYPES.put(Boolean.class, boolean.class);
			SAME_TYPES.put(Character.class, char.class);
			SAME_TYPES.put(Byte.class, byte.class);
			SAME_TYPES.put(Short.class, short.class);
			SAME_TYPES.put(Integer.class, int.class);
			SAME_TYPES.put(Long.class, long.class);
			SAME_TYPES.put(Float.class, float.class);
			SAME_TYPES.put(Double.class, double.class);

		}

		/**
		 * 基本数据类型转换为 包装数据类型
		 * 
		 * @param type
		 * @return
		 */
		public static String toPackagingType(String type) {
			return map.get(type);
		}

		private static void add(Set<String> a, String b, String c) {
			a.add(b);
			a.add(c);
		}

		/**
		 * 包装数据类型, 转换为基本数据类型
		 * 
		 * @param type
		 * @return
		 */
		public static String toBaseType(String type) {

			for (Entry<String, String> e : map.entrySet()) {

				if (e.getValue().equals(type)) {

					return e.getKey();
				}
			}

			return null;
		}

		/**
		 * 是否是基本数据类型, 或者包装数据类型
		 * 
		 * @param type
		 * @return
		 */
		public static boolean isBaseType(String type) {
			if (map.containsKey(type)) {
				return true;
			}
			if (map.containsValue(type)) {
				return true;
			}
			return false;
		}

		/**
		 * 返回result是否是t的实例
		 * 
		 * 如果 result == Integer t == int 那么返回true
		 * 
		 * 如果 result == int t == Integer 返回true (float short double byte long
		 * boolean char 同样支持)
		 * 
		 * @param t1
		 * @param result
		 * @return
		 */
		public static boolean isInstance(Class<?> t1, Object result) {
			Class<?> t2 = result.getClass();
			if (isSameBaseType(t1, t2)) {
				return true;
			}
			return t1.equals(t2);
		}

		/**
		 * 判断2者是不是同类 int == Integer Float == float....
		 * 
		 * @param t1
		 * @param t2
		 * @return
		 */
		public static boolean isSameBaseType(Class<?> t1, Class<?> t2) {
			if (t1.equals(t2)) {
				return true;
			}
			Class<?> temp = SAME_TYPES.get(t1);
			if (t2.equals(temp)) {
				return true;
			}

			temp = SAME_TYPES.get(t2);
			if (t1.equals(temp)) {
				return true;
			}

			return false;
		}

		/**
		 * 是否是基本数据类型的
		 * 
		 * @param temp
		 * @return
		 */
		public static boolean isBaseTypeArray(String temp) {
			String r = temp.replaceFirst("\\[\\]", "");
			boolean t = isBaseType(r);
			if (t) {
				return temp.contains("[]");
			}
			return false;
		}

		/**
		 * 是否是数字类型
		 * 
		 * @param type
		 * @return
		 */
		public static boolean isDigitType(String type) {
			return digitTypes.contains(type);
		}

	}

	public static final class IP {

		/**
		 * 指定IP地址对应的值
		 * 
		 * @param ip
		 * @return
		 */
		public static int getValue(String ip) {
			ip = ip.replaceAll("/", "").replaceAll("\\\\", "");
			String[] s = ip.split("\\.");
			int a = new Integer(s[0]);
			int b = new Integer(s[1]);
			int c = new Integer(s[2]);
			int d = new Integer(s[3]);
			return a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d * 256;
		}

		public static String getMyIp() {

			Pattern c = Pattern.compile("您的IP地址是：\\[[0-9\\.]+\\]");
			String json = WebContentFethcer.get("gb2312",
					"http://www.ip138.com/ips138.asp");
			Matcher m = c.matcher(json);
			m.find();
			String group = m.group();
			group = group.replaceAll("您的IP地址是：\\[", "").replaceAll("\\]", "")
					.trim();
			return group;
		}
	}

	/**
	 * 14:00 MONDAY to 14:00 FRIDAY (星期一到星期五 14:00) MONDAY TUESDAY WEDNESDAY
	 * THURSDAY FRIDAY SATURDAY SUNDAY
	 * 
	 * @author 林岑
	 * 
	 */
	private static class TimeChecker1 implements TimeChecker {

		private class MyDate {

			private Date date;

			private int dayInWeek;

			public MyDate(String time) {

				String[] split = time.split(" ");

				SimpleDateFormat f = new SimpleDateFormat("HH:mm");

				try {

					date = f.parse(split[0].trim());

					dayInWeek = getDayInWeek(split[1].trim());

				} catch (ParseException e) {

					throw new RuntimeException(e);
				}
			}

			private int getDayInWeek(String trim) {

				if (trim.equals("MONDAY"))

					return 1;

				if (trim.equals("TUESDAY"))

					return 2;

				if (trim.equals("WEDNESDAY"))

					return 3;

				if (trim.equals("THURSDAY"))

					return 4;

				if (trim.equals("FRIDAY"))

					return 5;

				if (trim.equals("SATURDAY"))

					return 6;

				if (trim.equals("SUNDAY"))

					return 7;

				throw new UnknownWeek(trim);
			}

			public MyDate(Date date) {

				Calendar c = Calendar.getInstance();

				c.setTime(date);

				dayInWeek = c.get(Calendar.DAY_OF_WEEK);

				if (dayInWeek == 1) {

					dayInWeek = 7;

				} else {

					dayInWeek--;
				}

				this.date = new Date(date.getTime() % Time.MILES_ONE_DAY);
			}

			public boolean after(MyDate st) {

				return date.after(st.date) && dayInWeek >= st.dayInWeek;
			}

			public boolean before(MyDate st) {

				return date.before(st.date) && dayInWeek <= st.dayInWeek;
			}

		}

		@Override
		public boolean isIn(Date date, String time) {

			String[] split = time.split(" or ");

			for (String string : split) {
				time = string;
				if (time.isEmpty()) {
					continue;
				}

				String[] s = time.split(" to ");

				String start = s[0].trim();

				String end = s[1].trim();

				Calendar c = Calendar.getInstance();

				c.setTime(date);

				MyDate st = new MyDate(start);

				MyDate ed = new MyDate(end);

				MyDate d = new MyDate(date);

				if (d.before(ed) && d.after(st)) {
					return true;
				}
			}

			return false;

		}

	}

	/**
	 * 19:30 to 20:00 (每天 19:30 到 20:00)
	 * 
	 * @author 林岑
	 * 
	 */
	private static class TimeChecker2 implements TimeChecker {

		@Override
		public boolean isIn(Date date, String time) {

			String[] s = time.split(" to ");

			String start = s[0].trim();

			String end = s[1].trim();

			Calendar c = Calendar.getInstance();

			c.setTime(date);

			MyDate st = new MyDate(start);

			MyDate ed = new MyDate(end);

			MyDate d = new MyDate(date);

			return d.before(ed) && d.after(st);
		}

		private class MyDate {

			private Date date;

			public MyDate(Date date) {

				this.date = date;

				this.date.setTime(this.date.getTime() % Time.MILES_ONE_DAY);
			}

			public MyDate(String t) {

				SimpleDateFormat f = new SimpleDateFormat("HH:mm");

				try {

					date = f.parse(t.trim());

				} catch (ParseException e) {

					throw new RuntimeException(e);
				}
			}

			public boolean before(MyDate ed) {

				return date.before(ed.date);
			}

			public boolean after(MyDate st) {

				return date.after(st.date);
			}

		}
	}

	/**
	 * 2013-09-25|14:00 to 2013-10-25|14:00 (2013-09-25|14:00 到
	 * 2013-10-25|14:00)
	 * 
	 * @author 林岑
	 * 
	 */
	private static class TimeChecker3 implements TimeChecker {

		@Override
		public boolean isIn(Date date, String time) {

			String[] split = time.split(" to ");

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd|HH:mm");

			try {

				Date start = df.parse(split[0].trim());

				Date end = df.parse(split[1].trim());

				return date.after(start) && date.before(end);

			} catch (ParseException e) {

				throw new RuntimeException(e);
			}
		}

	}

	/**
	 * 文件工具
	 * 
	 * @author 林岑
	 * 
	 */
	public static final class File {

		/**
		 * 获取文件, 可能获取的是一个不存在的文件
		 * 
		 * @param dir
		 *            目录名字
		 * @param f
		 *            目录下的文件名
		 * @return
		 */
		public static java.io.File getFile(java.io.File dir, String f) {
			try {
				String canonicalPath = dir.getCanonicalPath();
				if (!canonicalPath.endsWith(java.io.File.separator)) {
					canonicalPath += java.io.File.separator;
				}
				f = canonicalPath + f;
				java.io.File file = new java.io.File(f);
				return file;
			} catch (IOException e) {
				throw new IORuntimeException(e);
			}
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
			return getContent(new java.io.File(path));
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

		public static List<FileWithRelativePath> getFileWithRelativePaths(
				String path) {

			List<java.io.File> fs = getFiles(path);
			ArrayList<FileWithRelativePath> ls = Lists.newArrayList();

			for (java.io.File file : fs) {
				try {
					String pathC = file.getCanonicalPath().replaceAll("\\\\",
							"/");
					pathC = pathC.replaceFirst(path, "");
					pathC = replaceFirstSeparator(pathC);
					ls.add(new FileWithRelativePath(file, pathC));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return ls;

		}

		private static String replaceFirstSeparator(String pathC) {
			if (pathC.startsWith("/")) {
				return pathC.replaceFirst("/", "");
			}
			if (pathC.startsWith("\\")) {
				return pathC.replaceFirst("\\", "");
			}
			return pathC;
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
				Log.d(f.getCanonicalPath());
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

		public static List<String> getLines(java.io.File file) {
			FileLinesReader f;
			try {
				f = new FileLinesReader(new InputStreamReader(
						new FileInputStream(file), "utf8"));
			} catch (java.lang.Exception e) {
				throw Util.Exception.toRuntimeException(e);
			}
			return f.readLines();
		}

		public static String getContent(URL r) {
			InputStream s = null;
			BufferedReader bufferedReader = null;
			try {
				s = r.openStream();

				bufferedReader = new BufferedReader(new InputStreamReader(s,
						"utf8"));
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

		public static List<String> getLines(URL resource) {
			FileLinesReader f;
			InputStream input = null;
			try {

				input = resource.openStream();

				f = new FileLinesReader(new InputStreamReader(input, "utf8"));

				return f.readLines();

			} catch (java.lang.Exception e) {
				throw Util.Exception.toRuntimeException(e);
			} finally {
				Closer.close(input);
			}
		}

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
		 * 后缀名 返回值不包含"."
		 * 
		 * @param f
		 * @return
		 */
		public static String getSuffix(java.io.File f) {
			String name = f.getName();
			int l = name.lastIndexOf(".");
			name = name.substring(l + 1, name.length());
			return name;
		}

		public static byte[] getBytes(java.io.File file) {

			InputStream is = null;
			try {
				is = new FileInputStream(file);
				int a = is.available();

				byte[] bytes = new byte[a];

				is.read(bytes);
				return bytes;
			} catch (java.lang.Exception e) {
				throw new RuntimeException(e);
			} finally {
				Closer.close(is);
			}
		}

		/**
		 * 内容追加到指定文件
		 */
		public static void append(String file, String content) {
			FileWriter fw = null;
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

				fw = new FileWriter(f, true);
				fw.write(content);
				fw.flush();

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				Closer.close(fw);
			}
		}

		public static void mkdirs(String path) {
			java.io.File file = new java.io.File(path);
			if (!file.exists())
				file.mkdirs();
		}

	}

	/**
	 * 时间工具
	 * 
	 * @author 林岑
	 * 
	 */
	public static final class Time {

		/** 一分钟的毫秒数 */
		public static final long MILES_ONE_MIN = 60 * 1000;

		/** 一小时的毫秒数 */
		public static final long MILES_ONE_HOUR = 60 * MILES_ONE_MIN;

		/** 一天的毫秒数 */
		public static final long MILES_ONE_DAY = 24 * MILES_ONE_HOUR;

		/**
		 * 今天在本月的第几天(今天是几号)
		 * 
		 * @return
		 */
		public static int getDayOfMonthNow() {

			Calendar c = Calendar.getInstance();

			int dayNow = c.get(Calendar.DAY_OF_MONTH);

			return dayNow;
		}

		static final SimpleDateFormat FORMAT = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		/**
		 * 格式化成标准格式 yyyy-MM-dd HH:mm:ss
		 * 
		 * @param time
		 * @return
		 */
		public static String format(long time) {
			return FORMAT.format(new Date(time));
		}

		/**
		 * 本月共有几天
		 * 
		 * @return
		 */
		public static int getDaysOfMonth() {

			Calendar c = Calendar.getInstance();

			return c.getActualMaximum(Calendar.DAY_OF_MONTH);
		}

		/**
		 * 计算两个时间的相聚的天数（自然天数）
		 * 
		 * @param g1
		 * @param g2
		 * @return
		 */
		public static int getElapsedDays(Calendar g1, Calendar g2) {
			int elapsed = 0;
			GregorianCalendar gc1, gc2;
			// if (g2.after(g1)) {
			gc2 = (GregorianCalendar) g2.clone();
			gc1 = (GregorianCalendar) g1.clone();
			// } else {
			// gc2 = (GregorianCalendar) g1.clone();
			// gc1 = (GregorianCalendar) g2.clone();
			// }
			gc1.clear(Calendar.MILLISECOND);
			gc1.clear(Calendar.SECOND);
			gc1.clear(Calendar.MINUTE);
			gc1.clear(Calendar.HOUR_OF_DAY);

			gc2.clear(Calendar.MILLISECOND);
			gc2.clear(Calendar.SECOND);
			gc2.clear(Calendar.MINUTE);
			gc2.clear(Calendar.HOUR_OF_DAY);

			while (gc1.before(gc2)) {
				gc1.add(Calendar.DATE, 1);
				elapsed++;
			}
			return elapsed;
		}

		/**
		 * 
		 * 返回当前时间距离指定时间点的秒数
		 * 
		 * @param h
		 *            时
		 * @param m
		 *            分 (可省略)
		 * @param s
		 *            秒 (可省略)
		 */
		public static int getRemainSec(int... hms) {
			Calendar now = Calendar.getInstance();
			Calendar last = Calendar.getInstance();
			last.set(Calendar.HOUR_OF_DAY, hms[0]);// 如果是9点就要计算8:59:59秒，所以要－1
			last.set(Calendar.MINUTE, hms.length >= 2 ? hms[1] : 0);
			last.set(Calendar.SECOND, hms.length >= 3 ? hms[2] : 0);
			int result = (int) ((last.getTimeInMillis() - now.getTimeInMillis()) / 1000);
			if (result < 0) {
				result += 24 * 3600;
			}

			return result;
		}

		/**
		 * 
		 * 返回当前时间距离午夜12点的秒数
		 * 
		 * @return
		 */
		public static int getRemainSecondToday() {
			Calendar now = Calendar.getInstance();
			Calendar last = Calendar.getInstance();
			last.set(Calendar.HOUR_OF_DAY, 23);
			last.set(Calendar.MINUTE, 59);
			last.set(Calendar.SECOND, 59);
			return (int) ((last.getTimeInMillis() - now.getTimeInMillis()) / 1000);
		}

		/**
		 * 是否是本月第一天
		 * 
		 * @return
		 */
		public static boolean isFirstDayOfMonth() {

			Calendar c = Calendar.getInstance();

			int day = c.get(Calendar.DAY_OF_MONTH);

			return day == 1;
		}

		/**
		 * 
		 * 用于判断date是否在某个时间段内
		 * 
		 * 所支持的参数格式:
		 * 
		 * 14:00 MONDAY to 14:00 FRIDAY (星期一到星期五 14:00) MONDAY TUESDAY WEDNESDAY
		 * THURSDAY FRIDAY SATURDAY SUNDAY
		 * 
		 * 19:30 to 20:00 (每天 19:30 到 20:00)
		 * 
		 * 2013-09-25|14:00 to 2013-10-25|14:00 (2013-09-25|14:00 到
		 * 2013-10-25|14:00)
		 * 
		 * @param time
		 * @return
		 */
		public static boolean isIn(Date date, String time) {

			// 14:00 MONDAY to 14:00 FRIDAY (星期一到星期五 14:00) MONDAY TUESDAY
			// WEDNESDAY
			// THURSDAY FRIDAY SATURDAY SUNDAY
			String r1 = "[0-9]{2}:[0-9]{2} [A-Z]{6,6} to [0-9]{2}:[0-9]{2} [A-Z]{6,9}";

			// 19:30 to 20:00 (每天 19:30 到 20:00)
			String r2 = "[0-9]{2}:[0-9]{2} to [0-9]{2}:[0-9]{2}";

			// 2013-09-25|14:00 to 2013-10-25|14:00 (2013-09-25|14:00 到
			// 2013-10-25|14:00)
			String r3 = "[0-9]{4}-[0-9]{2}-[0-9]{2}\\|[0-9]{2}:[0-9]{2} to [0-9]{4}-[0-9]{2}-[0-9]{2}\\|[0-9]{2}:[0-9]{2}";

			TimeChecker c;

			if (time.matches(r1)) {

				c = new TimeChecker1();

			} else if (time.matches(r2)) {

				c = new TimeChecker2();

			} else if (time.matches(r3)) {

				c = new TimeChecker3();

			} else {

				throw new UnknownDateException(time);
			}

			return c.isIn(date, time);
		}

		/**
		 * 是否是本月的最后一天
		 * 
		 * @return
		 */
		public static boolean isLastDayOfMonth() {

			Calendar c = Calendar.getInstance();

			int day = c.get(Calendar.DAY_OF_MONTH);

			c.setTimeInMillis(c.getTimeInMillis()
					+ cn.javaplus.time.Time.MILES_ONE_DAY);

			int nextDay = c.get(Calendar.DAY_OF_MONTH);

			return nextDay < day;
		}

		/**
		 * 判断t1日期和当前时间是否同一天<br>
		 * 鉴于游戏每个礼拜会重启一次，因此没判断年份是否相同
		 * 
		 * @param t1
		 * @return
		 */
		public static boolean isSameDay(Date t1) {
			Calendar now = Calendar.getInstance();
			Calendar last = Calendar.getInstance();
			last.setTimeInMillis(t1.getTime());
			if (last.get(Calendar.MONTH) == now.get(Calendar.MONTH)
					&& last.get(Calendar.DATE) == now.get(Calendar.DATE)) {
				return true;
			}
			return false;
		}

		/**
		 * 后几天的这个时候
		 * 
		 * @param day
		 * @param start
		 * @return
		 */
		public static Date nextDay(int day, Date start) {
			Date end = new Date(start.getTime() + day * Time.MILES_ONE_DAY);
			return end;
		}

		/**
		 * 毫秒转换为时分秒
		 */
		public cn.javaplus.time.Time parse(long ms) {
			return new cn.javaplus.time.Time(ms);
		}

		/**
		 * 当前时间 是否在这个时间范围内
		 * 
		 * 例子 00:00 to 24:00 2014-09-06|00:00 to 2014-09-08|24:00
		 * 2014-09-25|00:00 to 2014-09-29|24:00
		 * 
		 * @param scope
		 * @return
		 */
		public static boolean isIn(String scope) {
			return isIn(new Date(), scope);
		}

		/**
		 * 获取 当前时间 到下一个time 所需要的秒
		 * 
		 * @param time
		 *            支持的格式: 比如下午十八点: 18:00:00 或者 18:00
		 * @return
		 */
		public static int getRemainSec(String time) {
			String[] split = time.split(":");

			int h;
			int m;
			int s = 0;

			if (split.length == 2) {

				h = new Integer(split[0]);
				m = new Integer(split[1]);

			} else if (split.length == 3) {

				h = new Integer(split[0]);
				m = new Integer(split[1]);
				s = new Integer(split[2]);

			} else {
				throw new RuntimeException("不支持的时间格式:" + time);
			}

			return getRemainSec(h, m, s);
		}

		/**
		 * 当前当地时间
		 * 
		 * @return
		 */
		public static long getCurrentTimeMillis() {
			int i = Calendar.getInstance().get(Calendar.ZONE_OFFSET);
			return System.currentTimeMillis() + i;
		}

		/**
		 * 当前当地时间 所在天
		 * 
		 * @return
		 */
		public static int getCurrentDay() {
			return getDay(getCurrentTimeMillis());
		}

		static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");

		/**
		 * 当前当地 日期 (格式化后的) yyyy-MM-dd
		 * 
		 * @return
		 */
		public static String getCurrentFormatDay() {
			Date date = new Date(System.currentTimeMillis());
			return dayFormat.format(date);
		}

		static SimpleDateFormat timeFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		/**
		 * 当前当地 日期 (格式化后的) yyyy-MM-dd HH:mm:ss
		 * 
		 * @return
		 */
		public static String getCurrentFormatTime() {
			Date date = new Date(System.currentTimeMillis());
			return timeFormat.format(date);
		}

		/**
		 * 当地时间 天
		 * 
		 * @param milis
		 *            去掉时差后的时间 某个时间点的毫秒
		 * @return
		 */
		public static int getDay(long milis) {
			return (int) (milis / MILES_ONE_DAY);
		}

		/**
		 * 当前当地时间 小时数
		 * 
		 * @return
		 */
		public static int getCurrentHour() {
			long t = getCurrentTimeMillis();
			return (int) ((t / Time.MILES_ONE_HOUR) % 24);
		}

		/**
		 * 当前时间(1970 - 今 秒) 当地时间
		 * 
		 * @return
		 */
		public static int getCurrentSec() {
			return (int) (getCurrentTimeMillis() / 1000);
		}

		/**
		 * 当前时间(1970 - 今 秒) 当地时间
		 * 
		 * @return
		 */
		public static int getCurrentMin() {
			return getCurrentSec() / 60;
		}

		/**
		 * 从一堆时间中, 获取最近的那个时间
		 * 
		 * 比如现在时间是 11:44
		 * 
		 * times = "12:00 11:00 13:00"
		 * 
		 * 则返回 : "12:00"
		 * 
		 * times = "12:00 11:43 13:00"
		 * 
		 * 返回:"12:00"
		 * 
		 * @param times
		 * @return
		 */
		public static String getNearest(String times) {

			String[] ts = times.split(" ");

			String min = null;
			int minSec = Integer.MAX_VALUE;
			for (String t : ts) {
				if (!t.isEmpty()) {
					int remainSec = getRemainSec(t);
					if (remainSec < minSec) {
						minSec = remainSec;
						min = t;
					}
				}
			}

			if (min == null) {
				throw new RuntimeException("传入列表不能为空 !   times = " + times);
			}
			return min;
		}

		/**
		 * 获得指定格式的当前时间
		 * 
		 * @param timeFormat
		 *            yyyy-MM-dd HH:mm:ss
		 * @return
		 */
		public static String getCurrentTime(String timeFormat) {
			SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
			return sdf.format(new Date(System.currentTimeMillis()));
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
			char c[] = input.toCharArray();
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

			char c[] = input.toCharArray();
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

		/*
		 * 读取字符串, 从data的第i个字节开始读取length个长度
		 */
		public static final String subString(byte[] data, int i, int length) {
			return new String(Arrays.copyOfRange(data, i, length + i));
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
			srcText = srcText.replaceAll("\\-", "_");
			srcText = srcText.replaceAll(" ", "_");
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
		 * 判定是否为半角符号
		 * 
		 * @param c
		 * @return
		 */
		public static boolean isSingle(final char c) {
			return (':' == c || '：' == c)
					|| (',' == c || '，' == c)
					|| ('"' == c || '“' == c)
					|| ((0x0020 <= c)
							&& (c <= 0x007E)
							&& !((('a' <= c) && (c <= 'z')) || (('A' <= c) && (c <= 'Z')))
							&& !('0' <= c) && (c <= '9'));

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
				char string2[] = string.toCharArray();
				char newString2[] = newString.toCharArray();
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
				char line2[] = line.toCharArray();
				char newString2[] = newString.toCharArray();
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
				String oldString, String newString, int count[]) {
			if (line == null)
				return null;
			String lcLine = line.toLowerCase();
			String lcOldString = oldString.toLowerCase();
			int i = 0;
			if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
				int counter = 1;
				char line2[] = line.toCharArray();
				char newString2[] = newString.toCharArray();
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
				String newString, int count[]) {
			if (line == null)
				return null;
			int i = 0;
			if ((i = line.indexOf(oldString, i)) >= 0) {
				int counter = 1;
				char line2[] = line.toCharArray();
				char newString2[] = newString.toCharArray();
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
			return param == null || param.length() == 0
					|| param.trim().equals("");
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
				byte bytes[] = str.getBytes(encoding);
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
				ByteArrayInputStream in = new ByteArrayInputStream(
						context.getBytes());
				InputStreamReader isr = new InputStreamReader(in, encoding);
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
				char line2[] = line.toCharArray();
				char newString2[] = newString.toCharArray();
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
		 * 确保 s的字节长度最大为maxLen 多余的截取掉
		 * 
		 * 如果包含了汉字, 那么, 可能返回的字符串的长度, 可能小于maxLen
		 * 
		 * @param s
		 * @param maxLen
		 * @return
		 */
		public static String ensureMaxLen(String s, int maxLen) {

			if (maxLen < 0) {
				throw new IllegalArgumentException("参数不可以小于0:" + maxLen);
			}

			byte[] hb = s.getBytes();
			if (hb.length > maxLen) {
				while (true) {

					hb = Arrays.copyOf(hb, maxLen);
					String ss = new String(hb);
					if (ss.getBytes().length <= maxLen) {
						return ss;
					}
					maxLen--;
				}
			} else {
				return s;
			}
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

		/**
		 * 解析一个字符串
		 * 
		 * @param s
		 * @return
		 */
		public static StringResolver resolve(String s) {
			return new StringResolver(s);
		}

		/**
		 * 用于遍历某文件所有行
		 * 
		 * @param string
		 * @return
		 */
		public static LinesReader getLinesIterator(String string) {
			try {
				return new LinesReader(new FileInputStream(string));
			} catch (FileNotFoundException e) {
				throw Util.Exception.toRuntimeException(e);
			}
		}

		/**
		 * 取出所有相邻的字符串词组
		 * 
		 * 比如 abcd 输出 a b c d ab bc cd abc bcd abcd
		 * 
		 * @param text
		 * @return
		 */
		public static List<String> splitAllWords(String text) {
			List<String> all = split(text);
			List<String> ls = Lists.newArrayList();
			for (int stringLen = 1; stringLen <= all.size(); stringLen++) {
				ls.addAll(buildGroup(all, stringLen));
			}
			return ls;
		}

		private static List<String> split(String text) {
			String[] all = text.split("");
			ArrayList<String> ls = Lists.newArrayList();
			for (String string : all) {
				if (!string.isEmpty()) {
					ls.add(string);
				}
			}
			return ls;
		}

		private static List<String> buildGroup(List<String> all, int stringLen) {
			ArrayList<String> ls = Lists.newArrayList();
			for (int i = 0; i <= all.size() - stringLen; i++) {
				ls.add(buildString(all, i, stringLen));
			}

			return ls;
		}

		private static String buildString(List<String> all, int startIndex,
				int stringLen) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < stringLen; j++) {
				sb.append(all.get(startIndex++));
			}
			return sb.toString();
		}

		public static String toUnicode(String str) {
			char[] arChar = str.toCharArray();
			int iValue = 0;
			String uStr = "";
			for (int i = 0; i < arChar.length; i++) {
				iValue = (int) str.charAt(i);
				if (iValue <= 256) {
					// uStr+="& "+Integer.toHexString(iValue)+";";
					uStr += "\\" + Integer.toHexString(iValue);
				} else {
					// uStr+="&#x"+Integer.toHexString(iValue)+";";
					uStr += "\\u" + Integer.toHexString(iValue);
				}
			}
			return uStr;
		}

		// public static void main(String[] args) {
		// String a = "123";
		// String unicode = toUnicode(a);
		// unicode = unicode.replaceAll("\\\\u", "").replaceAll("\\\\", "");
		// System.out.println(unicode);
		// }

		/**
		 * 判断是否是邮箱
		 */
		public static boolean isEmail(String input) {
			String pt = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
			return input.matches(pt);
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

		public static String md5WithOutLine(String s) {
			String replaceAll = s.replaceAll("\r\n", "").replaceAll("\r", "")
					.replaceAll("\n", "");
			return md5(replaceAll);
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

		public static RuntimeException toRuntimeException(SQLException e) {
			return new SQLRuntimeException(e);
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
		 * 返回列表中所有元素互相组合的结果
		 * 
		 * @param count
		 *            2 两两组合 3 三三组合 4 四四组合 .....
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public final static <T> List<List<T>> groups(List<T> all, int count) {
			if (count > all.size()) {
				throw new RuntimeException();
			}
			if (count < 1) {
				throw new RuntimeException();
			}
			if (count == 1) {
				ArrayList<List<T>> ss = Lists.newArrayList();
				for (T e : all) {
					ss.add(Lists.newArrayList(e));
				}
				return ss;
			}
			ArrayList<List<T>> ls = Lists.newArrayList();

			for (T t : all) {
				List<List<T>> gs = groups(all, count - 1);
				for (List<T> list : gs) {
					ls.add(merge(Lists.newArrayList(t), list));
				}
			}

			return ls;
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

		public final static <T> List<List<T>> page(List<T> values,
				int countEveryPage) {
			if (values.isEmpty()) {
				return Lists.newArrayList();
			}
			if (countEveryPage <= 0) {
				throw new RuntimeException(
						"countEveryPage must > 0!  countEveryPage = "
								+ countEveryPage);
			}
			List<List<T>> ls = Lists.newArrayList();

			int start = 0;
			int end = countEveryPage;
			final int step = countEveryPage;

			while (true) {

				boolean isToutchEnd = end >= values.size();
				if (isToutchEnd) {
					end = values.size();
				}

				List<T> subList = values.subList(start, end);
				ls.add(Lists.newArrayList(subList));

				if (isToutchEnd) {
					break;
				}

				start += step;
				end += step;
			}

			return ls;
		}

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
		@SuppressWarnings({ "rawtypes", "unchecked" })
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

		public static void main(String[] args) {
			ArrayList<Integer> all = Lists.newArrayList();
			all.add(1);
			all.add(2);
			all.add(3);
			all.add(4);
			all.add(5);
			all.add(6);
			List<List<Integer>> groups = groups(all, 6);
			for (List<Integer> list : groups) {
				System.out.println(list);
			}
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

		public static <T> String linkWith(String sp, T[] info,
				Fetcher<T, String> fetcher) {
			List<T> list = toList(info);
			return linkWith(sp, list, fetcher);
		}

		public static <T> List<T> toList(T[] info) {
			List<T> ls = Lists.newArrayList();
			for (T t : info) {
				ls.add(t);
			}
			return ls;
		}

		/**
		 * 用分隔符sp将 s的每个元素连接起来
		 * 
		 * @param sp
		 * @param s
		 * @return
		 */
		public static <T> String linkWith(String sp, java.util.Collection<T> s) {
			return linkWith(sp, s, new Fetcher<T, String>() {

				@Override
				public String get(T t) {
					return t + "";
				}
			});
		}

		/**
		 * 用分隔符sp将 s的每个元素连接起来
		 * 
		 * @param sp
		 * @param s
		 * @return
		 */
		public static <T> String linkWith(String sp, T... s) {
			return linkWith(sp, s, new Fetcher<T, String>() {

				@Override
				public String get(T t) {
					return t + "";
				}
			});
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
	}

	public static final class Property {

		/**
		 * 获得指定路径的配置
		 * 
		 * @param fileName
		 *            配置文件路径
		 * @return
		 */
		public static IProperties getProperties(String fileName) {

			InputStream is = getInputStream(fileName);

			Properties properties = getProperties(is);

			return new MyProperty(properties);
		}

		public static Properties getProperties(InputStream is) {
			Properties p = new Properties();
			try {
				p.load(is);
			} catch (Throwable e) {
				throw Exception.toRuntimeException(e);
			} finally {
				Closer.close(is);
			}
			return p;
		}

		private static InputStream getInputStream(String fileName) {
			InputStream is;

			try {
				is = new FileInputStream(new java.io.File(fileName));

			} catch (java.lang.Exception e) {

				throw Exception.toRuntimeException(e);
			}
			return is;
		}

		public static Properties getProperties(URL url) {
			InputStream is = null;
			try {
				is = url.openStream();
				return getProperties(new InputStreamReader(is, "utf8"));
			} catch (IOException e) {
				throw Exception.toRuntimeException(e);
			} finally {
				Closer.close(is);
			}
		}

		private static Properties getProperties(InputStreamReader is) {
			Properties p = new Properties();
			try {
				p.load(is);
			} catch (IOException e) {
				throw Exception.toRuntimeException(e);
			} finally {
				Closer.close(is);
			}
			return p;
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
		 * 根据ls中每个元素的某一列作为权重, 随机一个对象出来
		 * 
		 * @param ls
		 * @param weightAble
		 * @return
		 */
		public static <T> T getRandomOneByWeight(java.util.List<T> ls,
				WeightFetcher<T> weightAble) {

			int[] ws = Collection.getArrayByOneFields(weightAble, ls);
			if (ls.size() == 0 || ws.length == 0) {
				throw new RuntimeException("长度不能为0!  传入列表长度:" + ls.size()
						+ ", " + ws.length);
			}

			int sum = Array.sum(ws);
			if (sum == 0) {
				throw new RuntimeException("权重的和不可为0!");
			}

			int index = Random.getRandomIndex(ws);
			return ls.get(index);
		}

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
		 * 根据ls中每个元素的权重, 随机一个对象出来
		 * 
		 * @param ls
		 * @return
		 */
		public static <T extends Weightable> T getRandomOneByWeight(
				java.util.List<T> ls) {
			int[] ws = Collection.getArrayByOneFields(new WeightFetcher<T>() {

				@Override
				public Integer get(T t) {
					return t.getWeight();
				}
			}, ls);
			int index = Random.getRandomIndex(ws);
			return ls.get(index);
		}

		/**
		 * 
		 * 传入权重序列 , 返回随机产生的权重索引位置
		 * 
		 * 
		 * 
		 * 比如传入[1000,12,456,34,67,89], 2
		 * 
		 * 那么返回[0,2]的可能性就最大... 其中0为1000的索引位置, 2为456的索引位置
		 * 
		 * 
		 * @param count
		 *            返回的数组的长度
		 * @param weights
		 *            权重序列
		 * @return
		 */
		public static java.util.Collection<Integer> getRandomIndexs(int count,
				int[] ws) {
			if (ws.length < count) {
				throw new IllegalArgumentException("count不可大于ws的长度!"
						+ ws.length + ", " + count);
			}
			Set<Integer> s = Sets.newHashSet();
			while (s.size() < count) {
				s.add(getRandomIndex(ws));
			}
			return s;
		}

		// public static void main(String[] args) {
		// for (int i = 0; i < 800; i++) {
		// java.util.Collection<Integer> randomIndexs = getRandomIndexs(1, new
		// int[]{200000,800000});
		// System.out.println(randomIndexs);
		// }
		// }

		public static java.util.Collection<Integer> getRandomIndexs(int count,
				java.util.List<Integer> s) {
			int[] is = Array.toArray(s);
			return getRandomIndexs(count, is);
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
		public static boolean isHappen(double percent) {
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
		 * 随机长度为len的字符串
		 * 
		 * @param len
		 * @return
		 */
		public static String getRandomString(int len) {
			return KeyGenerator.getRandomString(len);
		}

		/**
		 * 随机移除一个
		 * 
		 * @param all
		 * @param w
		 * @return
		 */
		public static <T> T removeRandomOneByWeight(List<T> all,
				WeightFetcher<T> w) {
			T one = getRandomOneByWeight(all, w);
			all.remove(one);
			return one;
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
												// System.out.println("Util.Clazz.getClasses()"
												// + string);
												classes.add(Class
														.forName(string));
											} catch (ClassNotFoundException e) {
												Log.e("读取类出错: " + string);
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

		/**
		 * 判断c是不是i的子类
		 * 
		 * @param c
		 * @param i
		 * @return
		 */
		public static boolean isChild(Class<?> c, Class<ProtocolUser> i) {
			Class<?>[] interfaces = c.getInterfaces();
			for (Class<?> class1 : interfaces) {
				if (class1.equals(i)) {
					return true;
				}
			}
			Class<?> superclass = c.getSuperclass();
			if (superclass != null) {
				return superclass.equals(i);
			}
			return false;
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

	/**
	 * 敏感词配置
	 * 
	 * @author 林岑
	 * @time 2013-5-7
	 */
	public static class Sencitive {

		/**
		 * 是否是敏感词
		 * 
		 * @param k
		 * @return
		 */
		public static boolean isSencitive(String k) {
			return SencitiveWords.all.contains(k);
		}

		/**
		 * 判断改名字是否可以使用(是否不包含敏感词)
		 * 
		 * @param name
		 * @return
		 */
		public static boolean canUse(String name) {
			List<String> splits = Util.Str.splitAllWords(name);
			for (String s : splits) {
				if (SencitiveWords.all.contains(s)) {
					return false;
				}
			}
			return true;
		}

		/**
		 * 将一段正常的文本 把敏感词部分用星号替换 比如: 伟大的毛泽东同志和邓小平同志啊 返回: 伟大的***同志和***同志啊
		 * 
		 * @param message
		 * @return
		 */
		public static String sencitive(String message) {
			List<String> words = splits(message);
			for (String string : words) {
				if (Sencitive.isSencitive(string.replaceAll("\\s", ""))) {
					message = message.replaceAll(string,
							string.replaceAll(".", "*"));
				}
			}
			return message;
		}

		private static List<String> splits(String message) {
			String[] split = message.split("");
			List<String> ls = new ArrayList<String>();
			for (int i = 0; i < split.length; i++) {
				add(ls, i, split);
			}
			return ls;
		}

		private static void add(List<String> ls, int i, String[] split) {
			for (int j = i; j < split.length; j++) {
				ls.add(get(split, i, j));
			}
		}

		private static String get(String[] split, int start, int end) {
			StringBuilder sb = new StringBuilder();
			for (int k = start; k <= end; k++) {
				sb.append(split[k]);
			}
			return sb.toString();
		}

	}
}
