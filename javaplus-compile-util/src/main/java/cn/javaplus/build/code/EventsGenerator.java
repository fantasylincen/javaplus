package cn.javaplus.build.code;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.file.Templet;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class EventsGenerator {

	private static final String MARK = "implements Listener<[A-Za-z0-9_]+>";

	public class ClassComparator implements Comparator<ClassTwain> {

		public int compare(ClassTwain o1, ClassTwain o2) {
			String n1 = o1.getEventClass();
			String n2 = o2.getEventClass();
			int b = n1.length() - n2.length();
			if (b == 0) {
				int c = n1.compareTo(n2);
				return c;
			}
			return b;
		}

	}

	/**
	 * @param dst   Events.java 生成路径  比如 src/main/java/cn/vgame/a/events/Events.java
	 * @param lPath listeners 路径
	 * @param packageName 包名
	 */
	public void generate(String dst, String lPath, String packageName) {

		Templet temp = Templet.createByFilePath("Events.temp");
		temp.set("PACKAGE_NAME", packageName);
		temp.set("LOADS", buildLoads(dst, lPath));
		temp.writeToFile(dst);
	}

	private String buildLoads(String dst, String lPath) {
		StringPrinter sp = new StringPrinter();
		List<ClassTwain> classes = getClasses(lPath);
		Collections.sort(classes, new ClassComparator());
		for (ClassTwain c : classes) {
			String listenedEventClass = c.getEventClass();
			String a0 = listenedEventClass + ".class";
			String a1 = c.getListenerClass();
			sp.println(MessageFormat.format("		add({0}, new {1}());", a0, a1));
		}
		return sp.toString();
	}

	private List<ClassTwain> getClasses(String lPath) {

		ArrayList<ClassTwain> ls = Lists.newArrayList();
		List<File> all = Util.File.getFiles(lPath);
		for (File file : all) {
			String content = Util.File.getContent(file);
			content = content.replaceAll("\\s", " ");
			Pattern c = Pattern.compile(MARK);
			Matcher m = c.matcher(content);
			if (m.find()) {
				ClassTwain ct = new ClassTwain();
				String eventName = getEventName(content);
				ct.setEventClass(eventName);
				ct.setListenerClass(getListenerName(content));
				ls.add(ct);
			}
		}
		return ls;
	}

	private String getListenerName(String content) {
		String listenerName = extracted(content);

		String packageName = getPackageName(content);
		return packageName + "." + listenerName;
	}

	private String extracted(String content) {
		Pattern c2 = Pattern.compile("public class .+ implements Listener");
		Matcher m2 = c2.matcher(content);
		m2.find();
		String g2 = m2.group();
		String listenerName = g2.replaceAll("public class ", "").replaceAll(
				" implements.*", "");
		return listenerName;
	}

	private String getPackageName(String content) {
		Pattern c1 = Pattern.compile("package [a-zA-Z0-9_\\.]+;");
		Matcher m1 = c1.matcher(content);
		m1.find();
		String g1 = m1.group();
		String packageName = g1.replaceAll("package ", "").replaceAll(";", "");
		return packageName;
	}

	private String getEventName(String content) {

		Pattern c1 = Pattern.compile(MARK);
		Matcher m1 = c1.matcher(content);
		m1.find();
		String g1 = m1.group();
		String eventName = g1.replaceAll("implements Listener<", "")
				.replaceAll(">", "");

		String eventFullName = findFullName(content, eventName);
		if (eventFullName != null) {
			return eventFullName;
		}

		String packageName = getPackageName(content);
		return packageName + "." + eventName;
	}

	private String findFullName(String content, String eventName) {
		Pattern c1 = Pattern.compile("import [a-zA-Z0-9_\\.]+" + eventName
				+ ";");
		Matcher m1 = c1.matcher(content);
		if (m1.find()) {

			String g1 = m1.group();
			return g1.replaceAll("import ", "").replaceAll(";", "");
		}
		return null;
	}

	private static class ClassTwain {
		String eventClass;
		String listenerClass;

		public String getEventClass() {
			return eventClass;
		}

		public void setEventClass(String eventClass) {
			this.eventClass = eventClass;
		}

		public String getListenerClass() {
			return listenerClass;
		}

		public void setListenerClass(String listenerClass) {
			this.listenerClass = listenerClass;
		}

	}
}
