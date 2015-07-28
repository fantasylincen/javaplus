package cn.javaplus.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import com.google.common.collect.Lists;

/**
 * ¹¤¾ßÀà
 *
 * @author ÁÖá¯
 * @since 2012Äê1ÔÂ7ÈÕ 09:55:08
 */
public class Util {

	public static final java.util.Random R = new java.util.Random();

	public static final class File {

		/**
		 * »ñÈ¡ÎÄ¼þ, ¿ÉÄÜ»ñÈ¡µÄÊÇÒ»¸ö²»´æÔÚµÄÎÄ¼þ
		 *
		 * @param dir
		 *            Ä¿Â¼Ãû×Ö
		 * @param f
		 *            Ä¿Â¼ÏÂµÄÎÄ¼þÃû
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

		//
		// public static void main(String[] args) {
		// System.out.println(File.separator);
		// File f = getFile(new File("C:/windows"), "centOS.zip");
		// System.out.println(f);
		// }

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

		public static String getContent(String string) {
			return getContent(new java.io.File(string));
		}
	}

	public static final class Time {

		/** Ò»·ÖÖÓµÄºÁÃëÊý */
		public static final long MILES_ONE_MIN = 60 * 1000;

		/** Ò»Ð¡Ê±µÄºÁÃëÊý */
		public static final long MILES_ONE_HOUR = 60 * MILES_ONE_MIN;

		/** Ò»ÌìµÄºÁÃëÊý */
		public static final long MILES_ONE_DAY = 24 * MILES_ONE_HOUR;

		/**
		 * 14:00 MONDAY to 14:00 FRIDAY (ÐÇÆÚÒ»µ½ÐÇÆÚÎå 14:00) MONDAY TUESDAY WEDNESDAY
		 * THURSDAY FRIDAY SATURDAY SUNDAY
		 *
		 * @author ÁÖá¯
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

		}

		/**
		 * 19:30 to 20:00 (Ã¿Ìì 19:30 µ½ 20:00)
		 *
		 * @author ÁÖá¯
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
		 * 2013-09-25|14:00 to 2013-10-25|14:00 (2013-09-25|14:00 µ½
		 * 2013-10-25|14:00)
		 *
		 * @author ÁÖá¯
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
		 * ½ñÌìÔÚ±¾ÔÂµÄµÚ¼¸Ìì(½ñÌìÊÇ¼¸ºÅ)
		 *
		 * @return
		 */
		public static int getDayOfMonthNow() {

			Calendar c = Calendar.getInstance();

			int dayNow = c.get(Calendar.DAY_OF_MONTH);

			return dayNow;
		}

		/**
		 * ±¾ÔÂ¹²ÓÐ¼¸Ìì
		 *
		 * @return
		 */
		public static int getDaysOfMonth() {

			Calendar c = Calendar.getInstance();

			return c.getActualMaximum(Calendar.DAY_OF_MONTH);
		}

		/**
		 * ¼ÆËãÁ½¸öÊ±¼äµÄÏà¾ÛµÄÌìÊý£¨×ÔÈ»ÌìÊý£©
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
		 * ·µ»Øµ±Ç°Ê±¼ä¾àÀëÖ¸¶¨Ê±¼äµãµÄÃëÊý
		 *
		 * @param h
		 *            Ê±
		 * @param m
		 *            ·Ö (¿ÉÊ¡ÂÔ)
		 * @param s
		 *            Ãë (¿ÉÊ¡ÂÔ)
		 */
		public static int getRemainSec(int... hms) {
			Calendar now = Calendar.getInstance();
			Calendar last = Calendar.getInstance();
			last.set(Calendar.HOUR_OF_DAY, hms[0]);// Èç¹ûÊÇ9µã¾ÍÒª¼ÆËã8:59:59Ãë£¬ËùÒÔÒª£­1
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
		 * ·µ»Øµ±Ç°Ê±¼ä¾àÀëÎçÒ¹12µãµÄÃëÊý
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
		 * ÊÇ·ñÊÇ±¾ÔÂµÚÒ»Ìì
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
		 * ÓÃÓÚÅÐ¶ÏdateÊÇ·ñÔÚÄ³¸öÊ±¼ä¶ÎÄÚ
		 *
		 * ËùÖ§³ÖµÄ²ÎÊý¸ñÊ½:
		 *
		 * 14:00 MONDAY to 14:00 FRIDAY (ÐÇÆÚÒ»µ½ÐÇÆÚÎå 14:00) MONDAY TUESDAY WEDNESDAY
		 * THURSDAY FRIDAY SATURDAY SUNDAY
		 *
		 * 19:30 to 20:00 (Ã¿Ìì 19:30 µ½ 20:00)
		 *
		 * 2013-09-25|14:00 to 2013-10-25|14:00 (2013-09-25|14:00 µ½
		 * 2013-10-25|14:00)
		 *
		 * @param time
		 * @return
		 */
		public static boolean isIn(Date date, String time) {

			// 14:00 MONDAY to 14:00 FRIDAY (ÐÇÆÚÒ»µ½ÐÇÆÚÎå 14:00) MONDAY TUESDAY
			// WEDNESDAY
			// THURSDAY FRIDAY SATURDAY SUNDAY
			String r1 = "[0-9]{2}:[0-9]{2} [A-Z]{6,6} to [0-9]{2}:[0-9]{2} [A-Z]{6,9}";

			// 19:30 to 20:00 (Ã¿Ìì 19:30 µ½ 20:00)
			String r2 = "[0-9]{2}:[0-9]{2} to [0-9]{2}:[0-9]{2}";

			// 2013-09-25|14:00 to 2013-10-25|14:00 (2013-09-25|14:00 µ½
			// 2013-10-25|14:00)
			String r3 = "[0-9]{4}-[0-9]{2}-[0-9]{2}|[0-9]{2}:[0-9]{2} to [0-9]{4}-[0-9]{2}-[0-9]{2}|[0-9]{2}:[0-9]{2}";

			TimeChecker c;

			if (time.matches(r1)) {

				c = new TimeChecker1();

			} else if (time.matches(r2)) {

				c = new TimeChecker2();

			} else if (time.matches(r3)) {

				c = new TimeChecker3();

			} else {

				throw new UnknownDateException();
			}

			return c.isIn(date, time);
		}

		/**
		 * ÊÇ·ñÊÇ±¾ÔÂµÄ×îºóÒ»Ìì
		 *
		 * @return
		 */
		public static boolean isLastDayOfMonth() {

			Calendar c = Calendar.getInstance();

			int day = c.get(Calendar.DAY_OF_MONTH);

			c.setTimeInMillis(c.getTimeInMillis() + Time.MILES_ONE_DAY);

			int nextDay = c.get(Calendar.DAY_OF_MONTH);

			return nextDay < day;
		}

		/**
		 * ÅÐ¶Ït1ÈÕÆÚºÍµ±Ç°Ê±¼äÊÇ·ñÍ¬Ò»Ìì<br>
		 * ¼øÓÚÓÎÏ·Ã¿¸öÀñ°Ý»áÖØÆôÒ»´Î£¬Òò´ËÃ»ÅÐ¶ÏÄê·ÝÊÇ·ñÏàÍ¬
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

		public static void main(String[] args) {
			// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//
			// ÉèÖÃÄãÏëÒªµÄ¸ñÊ½
			//
			// Calendar start = Calendar.getInstance();
			// System.out.println( start );
			// String startStr = df.format(start.getTime());
			// System.out.println(startStr);
			// start.add(Calendar.DAY_OF_MONTH, -5);
			// System.out.println(df.format(start.getTime()));
			//
			// int second = (int) (start.getTimeInMillis() / 1000l);
			// Calendar now = Calendar.getInstance();
			//
			// start.setTimeInMillis( second * 1000l );
			// System.out.println(df.format(start.getTime()));
			// System.out.println( now );
			//
			//
			//
			// startStr = df.format(start.getTime());
			// System.out.println(startStr);
			//
			// String nowStr = df.format( now.getTime());
			// System.out.println(nowStr);
			//
			//
			// System.out.println( "ÌìÊý " + getElapsedDays( start, now ) );
		}

		/**
		 * ºó¼¸ÌìµÄÕâ¸öÊ±ºò
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
		 * ºÁÃë×ª»»ÎªÊ±·ÖÃë
		 */
		public cn.javaplus.common.util.Time parse(long ms) {
			return new cn.javaplus.common.util.Time(ms);
		}

	}

	public static final class Str {

		/**
		 *
		 * ½«ÒÔ¶ººÅ·Ö¸ôµÄ×Ö·û´®equipmentsµÄµÚindex¸öÖµÐÞ¸ÄÎª value<br>
		 * <br>
		 * ÀýÈç: 456564,649871,69871,6464,645 index = 3, value = xxxxxxÊ±, ÐÞ¸Äºó¾ÍµÃµ½
		 * 456564,649871,69871,xxxxxx,645<br>
		 * <br>
		 * Èç¹û´«ÈëµÄ×Ö·û´©µ±ÖÐ°üº¬ÁË-1, ÄÇÃ´¾Í×Ô¶¯½«-1ÖÃÎª¿Õ<br>
		 * ÀýÈç: "-1, 123, -1" alter 1, "xx" ----> ",xx,"
		 *
		 * @param equipments
		 *            ´ýÐÞ¸ÄµÄ×Ö·û´®
		 * @param index
		 *            ÐÞ¸ÄµÚindex¸ö¶ººÅ ºÍ index + 1 ¸ö¶ººÅËù¼ÐµÄ×Ö·û´®
		 * @param value
		 *            ÐÞ¸ÄÖµ
		 */
		public static String alter(String equipments, int index, String value) {

			equipments = equipments.replaceAll(",,", ",-1,");
			equipments = equipments.replace(" ", "");// Ïû³ý×Ö·û´®ÖÐËùÓÐ¿Õ¸ñ
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
		 * ¶ÁÈ¡×Ö·û´®, ´ÓdataµÄµÚi¸ö×Ö½Ú¿ªÊ¼¶ÁÈ¡length¸ö³¤¶È
		 */
		public static final String subString(byte[] data, int i, int length) {
			return new String(Arrays.copyOfRange(data, i, length + i));
		}

		/**
		 * ½«IDÁÐ±í×ª³É×Ö·û´®ÐÎÊ½, ÒÔ¶ººÅ·Ö¸ô
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
		 * ½«µÚÒ»¸ö×Ö·û×ª»»³É´óÐ´
		 *
		 * @param src
		 * @return
		 */
		public static final String firstToUpperCase(String src) {
			return src.replaceFirst(src.substring(0, 1), src.substring(0, 1)
					.toUpperCase());
		}

		/**
		 * ½«Ð¡Ð´µÄÃüÃû¸ÄÎª³£Á¿ÃüÃû¹æÔò
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
		 * ËùÓÐ´óÐ´×ÖÄ¸µÄË÷ÒýÎ»ÖÃ
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
		 * ½«×Ö·û´®µÄÊ××ÖÄ¸´óÐ´£¬Ò»°ãÓÃÓÚ½«±íÃû×ª»»ÎªÀàÃû
		 *
		 * @param tableName
		 *            ÓÃÏÂ»®ÏßÁ¬½ÓµÄÈ«Ð¡Ð´×Ö¶ÎÃû, ÀýÈç"aaa_bbb_ccc" ±íÃû
		 * @return ÀàÃû "AaaBbbCcc"
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
		 * ½«ÀàÃû×ª»»Îª¶ÔÏó ²ÎÊýÃû£¬¼´µÚÒ»¸ö×ÖÄ¸Ð¡Ð´£¬Èç£ºUser - user
		 *
		 * @param tableName
		 *            ±íÃû
		 * @return ²ÎÊýÃû
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
		 * ½«ÆÕÍ¨×Ö·û´®, °üº¬ÏÂ»®ÏßµÄ, ×ª»»³ÉÍÕ·å±êÊ¶ µÄ×Ö·û´®
		 *
		 * @param srcText
		 * @return
		 */
		public static String hump(String srcText) {

			// °ÑÏÂ»®ÏßÖ®ºóµÄÒ»¸ö×Ö·û¶¼±äÎª´óÐ´
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
		 * ²¹Æð×Ö·û´®, ÓÃ¿Õ¸ñ²¹Æë, ±£Ö¤·µ»ØÖµ³¤¶È´óÓÚµÈÓÚweigth
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
		 * ÏÂ»®ÏßºóµÄÒ»¸ö×Ö·û×ªÎª´óÐ´, Í¬Ê±½«ÏÂ»®ÏßÉ¾³ý
		 *
		 * @param f
		 * @return
		 */
		public static String parseAfter_ToUpperCase(String f) {
			// °ÑÏÂ»®ÏßÖ®ºóµÄÒ»¸ö×Ö·û¶¼±äÎª´óÐ´
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
		 * ÅÐ¶¨ÊÇ·ñÓÉ´¿´âµÄÎ÷·½×Ö·û×é³É
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
		 * ·Ö½â×Ö·û´®
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
		 * ¹ýÂËÖ¸¶¨×Ö·û´®
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
		 * ²»Æ¥Åä´óÐ¡Ð´µÄ¹ýÂËÖ¸¶¨×Ö·û´®
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
		 * ²»Æ¥Åä´óÐ¡Ð´µÄ¹ýÂËÖ¸¶¨×Ö·û´®
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
		 * ÒÔÖ¸¶¨Ìõ¼þ¹ýÂË×Ö·û´®
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
		 * ¹ýÂË\n±ê¼Ç
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
		 * ¼ì²éÒ»×é×Ö·û´®ÊÇ·ñÍêÈ«ÓÉÖÐÎÄ×é³É
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
		 * ÅÐ¶ÏÊÇ·ñÎªnull
		 *
		 * @param param
		 * @return
		 */
		public static boolean isEmpty(String param) {
			return param == null || param.length() == 0
					|| param.trim().equals("");
		}

		/**
		 * ÏÔÊ¾Ö¸¶¨±àÂëÏÂµÄ×Ö·û³¤¶È
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
		 * ×ª»¯Ö¸¶¨×Ö·û´®ÎªÖ¸¶¨±àÂë¸ñÊ½
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
		 * ¼ì²éÖ¸¶¨×Ö·û´®ÖÐÊÇ·ñ´æÔÚÖÐÎÄ×Ö·û¡£
		 *
		 * @param checkStr
		 *            Ö¸¶¨ÐèÒª¼ì²éµÄ×Ö·û´®¡£
		 * @return Âß¼­Öµ£¨True Or False£©¡£
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
		 * ¼ì²éÊÇ·ñÎª´¿×ÖÄ¸
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
		 * ¼ì²éÊÇ·ñÎª×ÖÄ¸ÓëÊý×Ö»ìºÏ
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
		 * ¹ýÂËÊ××Ö·û
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
		 * Ìæ»»Ö¸¶¨×Ö·û´®
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
		 * ÒÔ" "³äÂúÖ¸¶¨×Ö·û´®
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
		 * µÃµ½¶¨×Ö½Ú³¤µÄ×Ö·û´®£¬Î»Êý²»×ãÓÒ²¹¿Õ¸ñ
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
		 * ·µ»ØÖ¸¶¨×Ö·û´®³¤¶È
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
		 * »ñµÃÌØ¶¨×Ö·û×ÜÊý
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

	}

	public static final class Secure {

		public static String md5(String text) {
			StringBuffer md5 = new StringBuffer();
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(text.getBytes());
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
		 * ½«×Ö·û´®·ÅÈëÒ»¸öByteBufferÖÐ
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

		public static RuntimeException toRuntimeException(java.lang.Exception e) {
			return new RuntimeException(e);
		}

		public static RuntimeException toRuntimeException(String message,
				java.lang.Exception e) {
			return new RuntimeException(message, e);
		}

	}

	public static final class Collection {

		/**
		 * ÔÚÁÐ±íÖÐ½ØÈ¡ÔªËØ, Èç¹ûÊýÁ¿´óÓÚc, ÄÇÃ´È¡Ç°c¸öÔªËØ, Èç¹û²»×ã, ·µ»ØÈ«²¿ÔªËØ
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
		 * ´òÂÒÁÐ±í
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
		 * ºÏ²¢Á½¸öÁÐ±í
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
		 * ½«exceptÖÐµÄÔªËØ, ´ÓallÖÐÌÞ³ýºó·µ»Ø
		 *
		 * @param except
		 *            ±»ÌÞ³ýµÄ
		 * @param all
		 *            ËùÓÐµÄ
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
		 * ÕÒ³öÁ½¸öÁÐ±íµ±ÖÐÏàÍ¬µÄÔªËØ
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
		 * Ëæ»ú·µ»ØÁÐ±íÖÐµÄÒ»¸ö¶ÔÏó
		 *
		 * @param ls
		 * @return
		 */
		public static <T> T getRandomOne(java.util.Collection<T> ls) {

			if (ls.isEmpty()) {
				throw new IllegalArgumentException("ÁÐ±í²»ÄÜÎª¿Õ!");
			}

			ArrayList<T> arrayList = new ArrayList<T>(ls);
			return arrayList.get(Util.R.nextInt(arrayList.size()));
		}

		/**
		 * »ñµÃ×î´óÖµ ×¢ÒâÁÐ±í²»ÒªÎª¿Õ
		 */
		public static int getMax(java.util.Collection<Integer> allKey) {

			if (allKey.isEmpty()) {
				throw new IllegalArgumentException("ÁÐ±í²»ÄÜÎª¿Õ!");
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
		 * ×¢ÒâÁÐ±í²»ÒªÎª¿Õ
		 *
		 * @param allKey
		 * @return
		 */
		public static int getMin(java.util.Collection<Integer> allKey) {

			if (allKey.isEmpty()) {
				throw new IllegalArgumentException("ÁÐ±í²»ÄÜÎª¿Õ!");
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
		 * ½«lsÖÐµÄËùÓÐÔªËØµÄÄ³¸öÖµ, ×÷ÎªÁÐ±í·µ»Ø
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
		 * ½«ÓÃÄ³ÖÖ·Ö¸ô·û·Ö¸ôµÄ×Ö·û´®, ×ª»»³ÉÕûÊýÁÐ±í
		 *
		 * @param string
		 * @return
		 */
		public static java.util.List<Integer> getIntegers(String string) {
			StringTokenizer s = new StringTokenizer(string, " ,\\|/\\.-=+'");
			List<Integer> ls = Lists.newArrayList();

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
		 * ½«allÓÃsp·Ö¸ô·ûÁ¬½ÓÆðÀ´
		 *
		 * @param sp
		 * @param all
		 * @param fetcher
		 * @return
		 */
		public static <T, R> String linkWith(String sp, java.util.List<T> all,
				Fetcher<T, R> fetcher) {
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
		 * ½«ÁÐ±ílsÖÐµÄËùÓÐÔªËØµÄÄ³¸öÕûÐÍÖµ ×°ÔØÒ»¸öÁÐ±íÖÐ·µ»Ø
		 *
		 * @param fetcher
		 * @param ls
		 * @return
		 */
		public static <T> int[] getArrayByOneFields(IntegerFetcher<T> fetcher,
				java.util.List<T> ls) {
			int[] a = new int[ls.size()];
			for (int i = 0; i < a.length; i++) {
				a[i] = fetcher.get(ls.get(i));
			}
			return a;
		}

		/**
		 * µÚÒ»¸öÔªËØ
		 *
		 * @param f
		 * @return
		 */
		public static <T> T getFirst(java.util.Collection<T> f) {
			for (T t : f) {
				return t;
			}
			throw new IllegalArgumentException("ÈÝÆ÷²»¿ÉÎª¿Õ");
		}

		public static String linkWith(String sp, Object[] info,
				Fetcher<Object, String> fetcher) {
			List<Object> list = toList(info);
			return linkWith(sp, list, fetcher);
		}

		public static <T> List<T> toList(T[] info) {
			List<T> ls = Lists.newArrayList();
			for (T t : info) {
				ls.add(t);
			}
			return ls;
		}

		public static float getMin(List<Float> a) {

			Float min = Float.MAX_VALUE;

			for (Float value : a) {

				if (value < min) {

					min = value;
				}
			}

			return min;
		}

		public static Float getMax(List<Float> allKey) {
			if (allKey.isEmpty()) {
				throw new IllegalArgumentException("ÁÐ±í²»ÄÜÎª¿Õ!");
			}

			float max = Float.MIN_VALUE;

			for (Float value : allKey) {

				if (value > max) {

					max = value;
				}
			}

			return max;
		}

	}

	public static final class Array {

		/**
		 * ½«Ò»¸öÁÐ±í×ª»»³ÉÊý×é
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
		 * ½«Ò»¸öÊý×é×ª»»³ÉÁÐ±í
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
		 * ÅÐ¶ÏÊý×éÖÐËùÓÐÔªËØÊÇ·ñÏàµÈ
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
		 * ½«Ò»¸ö¶ººÅ·Ö¸ôµÄ×Ö·û´®ÁÐ±í×ª»»ÎªÕûÐÍÊý×é<br>
		 * Ç°ÌáintList¸ñÊ½ºÏ·¨.<br>
		 * ±ÈÈç 1111,213,646,674987,16546,333<br>
		 *
		 * ½«ÓÃ¶ººÅ·Ö¸ôµÄint×Ö·û´®, ×ª»»³ÉintÊý×é<br>
		 * <br>
		 * Èç¹ûÆäÖÐÓÐ¿Õ°×, ÄÇÃ´ÄÇ¸öÖµ¾ÍµÈÓÚ -1<br>
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
		 * ¼ÆËãÊý×éÇ°nÏîºÍ
		 *
		 * @param is
		 *            ±»¼ÆËãÊý×é
		 * @param n
		 *            Ç°nÏî
		 * @return Ç°nÏîºÍ
		 */
		public static int sumOfSubArray(int[] is, int n) {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				sum += is[i];
			}
			return sum;
		}

		/**
		 * ½«ÕûÐÍÊýÖµÁÐ±í×ª³É×Ö·û´®ÐÎÊ½, ÒÔ¶ººÅ·Ö¸ô
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
		 * °ÑÓÃ¶ººÅ·Ö¸ôµÄ×Ö·û´®×ª»»ÎªÕûÐÎÊý×é
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
		 * »ñµÃ×î´óÖµ ×¢ÒâÁÐ±í²»ÒªÎª¿Õ
		 */
		public static int getMax(int[] a) {
			if (a.length == 0) {
				throw new IllegalArgumentException("ÁÐ±í²»ÄÜÎª¿Õ!");
			}

			List<Integer> array = toList(a);

			if (array.isEmpty()) {
				return 0;
			}

			return Util.Collection.getMax(array);
		}

		/**
		 * »ñµÃ×îÐ¡Öµ ×¢ÒâÁÐ±í²»ÒªÎª¿Õ
		 *
		 * @param a
		 * @return
		 */
		public static int getMin(int[] a) {
			if (a.length == 0) {
				throw new IllegalArgumentException("ÁÐ±í²»ÄÜÎª¿Õ!");
			}
			List<Integer> array = toList(a);
			return Util.Collection.getMin(array);
		}

		/**
		 * ºÍ
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
	}

	public static final class Property {

		/**
		 * »ñµÃÖ¸¶¨Â·¾¶µÄÅäÖÃ
		 *
		 * @param fileName
		 *            ÅäÖÃÎÄ¼þÂ·¾¶
		 * @return
		 */
		public static Properties getProperties(String fileName) {

			Properties p = new Properties();

			try {

				p.load(new FileInputStream(new java.io.File(fileName)));

			} catch (java.lang.Exception e) {

				throw new RuntimeException(e);
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
	 * Ëæ»ú¹¤¾ß
	 *
	 * @author ÁÖá¯
	 */
	public static final class Random {

		/**
		 * ¸ù¾ÝlsÖÐÃ¿¸öÔªËØµÄÄ³Ò»ÁÐ×÷ÎªÈ¨ÖØ, Ëæ»úÒ»¸ö¶ÔÏó³öÀ´
		 *
		 * @param ls
		 * @param weightAble
		 * @return
		 */
		public static <T> T getRandomOneByWeight(java.util.List<T> ls,
				WeightFetcher<T> weightAble) {
			int[] ws = Collection.getArrayByOneFields(weightAble, ls);
			int index = Random.getRandomIndex(ws);
			return ls.get(index);
		}

		/**
		 * Ëæ»ú·µ»ØÁÐ±íÖÐµÄÒ»¸ö¶ÔÏó
		 *
		 * @param ls
		 * @return
		 */
		public static <T> T getRandomOne(java.util.Collection<T> ls) {

			return Collection.getRandomOne(ls);
		}

		/**
		 * ¸ù¾ÝlsÖÐÃ¿¸öÔªËØµÄÈ¨ÖØ, Ëæ»úÒ»¸ö¶ÔÏó³öÀ´
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
		 * ´«ÈëÈ¨ÖØÐòÁÐ , ·µ»ØËæ»ú²úÉúµÄÈ¨ÖØË÷ÒýÎ»ÖÃ
		 *
		 *
		 *
		 * ±ÈÈç´«Èë[1000,12,456,34,67,89], 2
		 *
		 * ÄÇÃ´·µ»Ø[0,2]µÄ¿ÉÄÜÐÔ¾Í×î´ó... ÆäÖÐ0Îª1000µÄË÷ÒýÎ»ÖÃ, 2Îª456µÄË÷ÒýÎ»ÖÃ
		 *
		 *
		 * @param count
		 *            ·µ»ØµÄÊý×éµÄ³¤¶È
		 * @param weights
		 *            È¨ÖØÐòÁÐ
		 * @return
		 */
		public static java.util.Collection<Integer> getRandomIndexs(int count,
				int[] ws) {
			if (ws.length < count) {
				throw new IllegalArgumentException("count²»¿É´óÓÚwsµÄ³¤¶È!"
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
		 * ´«Èë¸ÅÂÊÐòÁÐ , Ëæ»úÉú³É¶ÔÓ¦intÊýÖµ(Ë÷ÒýÎ»ÖÃ) Í¨¹ý¸ÅÂÊÐòÁÐËæ»úÉú³É 0 - (length - 1) ÕâÐ©ÕûÐÍÊý×Ö,
		 * Æä¸÷×ÔµÄ¸ÅÂÊÎªis[0] --- is[length - 1]
		 *
		 * ÀýÈç:²úÉú 3 µÄ¸ÅÂÊ¾ÍÊÇis[2]
		 *
		 * @param is
		 *            ¸ÅÂÊÐòÁÐ Êý×é
		 * @return ¸ÃÊý×éÄ³¸öÏÂ±ê
		 */
		public static int getRandomIndex(int... is) {
			if (is.length == 0) {
				throw new IllegalArgumentException("Êý×é³¤¶È²»ÄÜÎª0");
			}
			synchronized (Util.R) {
				int v = Util.R.nextInt(Array.sumOfSubArray(is, is.length));
				for (int i = 0; i < is.length; i++) {
					if (v < Array.sumOfSubArray(is, i + 1)) {
						return i;
					}
				}
				return -1;
			}
		}

		/**
		 * ´«Èë¸ÅÂÊÐòÁÐ , Ëæ»úÉú³É¶ÔÓ¦intÊýÖµ(Ë÷ÒýÎ»ÖÃ) Í¨¹ý¸ÅÂÊÐòÁÐËæ»úÉú³É 0 - (length - 1) ÕâÐ©ÕûÐÍÊý×Ö,
		 * Æä¸÷×ÔµÄ¸ÅÂÊÎªis[0] --- is[length - 1]
		 *
		 * ÀýÈç:²úÉú 3 µÄ¸ÅÂÊ¾ÍÊÇis[2]
		 *
		 * @param is
		 *            ¸ÅÂÊÐòÁÐ Êý×é
		 * @return ¸ÃÊý×éÄ³¸öÏÂ±ê
		 */
		public static int getRandomIndex(java.util.List<Integer> s) {
			int[] is = Array.toArray(s);
			return getRandomIndex(is);
		}

		/**
		 * Ëæ»ú²úÉú min - maxÖÐµÄÊýÖµ
		 *
		 * ±ÈÈç (min = -1, max = 3) ÄÇÃ´¾Í»á²úÉú {-1, 0, 1, 2, 3}
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
		 * ·µ»Ø min - max Ö®¼äµÄÒ»¸öËæ»úÊý
		 *
		 * @param min
		 * @param max
		 * @return
		 */
		public static double get(double min, double max) {
			return Util.R.nextDouble() * (max - min) + min;
		}

		/**
		 * ÒÔÒ»¶¨¼¸ÂÊ·¢ÉúÄ³¼þÊÂ·¢Éú Ç°ÖÃÌõ¼þ: 0 <= percent <= 1 Èç¹û percent >= 1, ÄÇÃ´ÊÂ¼þ¾ÍÊ¼ÖÕ·¢Éú Èç¹û
		 * percent <= 0, ÄÇÃ´Ê±¼ä¾Í´Ó²»·¢Éú
		 *
		 * @param percent
		 *            ·¢ÉúµÄ¼¸ÂÊ
		 * @return true: ·¢Éú, false: Î´·¢Éú
		 */
		public static boolean isHappen(float percent) {
			synchronized (Util.R) {
				return percent > Util.R.nextFloat();
			}
		}
	}

	//900像素: 228毫米
	private static final float	S	= 200;

	public static float getStageToWorld(float v) {
		return v / S;
	}

	public static float getWorldToStage(float v) {
		return v * S;
	}

}
