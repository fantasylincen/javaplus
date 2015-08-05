package cn.javaplus.game.shhz.events;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.game.shhz.Templet;
import cn.javaplus.shhz.collections.Lists;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.string.StringPrinter;
import cn.javaplus.shhz.util.Util;

public class EventsGenerator {

	public class ClassComparator implements Comparator<ClassTwain> {

		public int compare(ClassTwain o1, ClassTwain o2) {
			Class<?> e1 = o1.getEventClass();
			Class<?> e2 = o2.getEventClass();
			String n1 = e1.getName();
			String n2 = e2.getName();
			int b = n1.length() - n2.length();
			if (b == 0) {
				int c = n1.compareTo(n2);
				return c;
			}
			return b;
		}

	}

	public void generate() {
		List<Class<?>> all = Util.Clazz.getClasses("cn.javaplus");
		filter(all);

		List<ClassTwain> classes = getClasses(all);

		Templet temp = new Templet("Events.temp");
		temp.set("LOADS", buildLoads(classes));
		temp.writeToFile("../core/gen/cn/javaplus/game/shhz/Events.java");
	}

	private List<ClassTwain> getClasses(List<Class<?>> all) {
		List<ClassTwain> ls = Lists.newArrayList();
		for (Class<?> c : all) {
			ClassTwain cc = new ClassTwain();
			cc.setEventClass(getListenedEventClass(c));
			cc.setListenerClass(c);
			ls.add(cc);
		}
		return ls;
	}

	private static class ClassTwain {
		Class<?> eventClass;
		Class<?> listenerClass;

		public Class<?> getEventClass() {
			return eventClass;
		}

		public void setEventClass(Class<?> eventClass) {
			this.eventClass = eventClass;
		}

		public Class<?> getListenerClass() {
			return listenerClass;
		}

		public void setListenerClass(Class<?> listenerClass) {
			this.listenerClass = listenerClass;
		}

	}

	private String buildLoads(List<ClassTwain> classes) {
		StringPrinter sp = new StringPrinter();
		Collections.sort(classes, new ClassComparator());
		for (ClassTwain c : classes) {
			Class<?> listenedEventClass = c.getEventClass();
			String a0 = listenedEventClass.getName() + ".class";
			String a1 = c.getListenerClass().getName();
			sp.println(MessageFormat.format("		add({0}, new {1}());", a0, a1));
		}
		return sp.toString();
	}

	private static Class<?> getListenedEventClass(Class<?> c) {
		Type is = c.getGenericInterfaces()[0];
		ParameterizedType interfac = (ParameterizedType) is;
		Type[] actualTypeArguments = interfac.getActualTypeArguments();
		return (Class<?>) actualTypeArguments[0];
	}

	private void filter(List<Class<?>> all) {
		Iterator<Class<?>> it = all.iterator();
		while (it.hasNext()) {
			Class<?> c = (Class<?>) it.next();
			if (!hasInterface(c)) {
				it.remove();
			}
		}
	}

	private boolean hasInterface(Class<?> c) {

		Class<?>[] interfaces = c.getInterfaces();
		for (Class<?> cc : interfaces) {
			if (cc.equals(Listener.class)) {
				return true;
			}
		}
		return false;
	}
}
