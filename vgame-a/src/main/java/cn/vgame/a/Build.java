package cn.vgame.a;

import japa.parser.JavaParser;
import japa.parser.ast.Comment;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.common.collect.Lists;

import cn.javaplus.build.code.EventsGenerator;
import cn.javaplus.java.JavaFileImpl;
import cn.javaplus.mongodao.App;
import cn.javaplus.random.Fetcher;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Resources;
import cn.javaplus.util.Util;

public class Build {

	public static void main(String[] args) {
		generateMongoDB();
//		generateProtocols();
		generateEvents();
	}

	private static void generateEvents() {
		new EventsGenerator().generate("src/main/java/cn/vgame/a/events/Events.java", "src/main/java/cn/vgame/", "cn.vgame.a.events");
	}

//	@SuppressWarnings("unchecked")
//	private static void generateProtocols() {
//		String temp = Util.File.getContent(Resources.getResource("protocolDoc.temp"));
//		try {
//			SAXReader saxReader = new SAXReader();
//			Document document = saxReader.read(Resources.getResource("struts.xml"));
//			Element employees = document.getRootElement();
//			StringPrinter tables = new StringPrinter();
//			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {
//				Element e = i.next();
//				String name = e.getName();
//				if (name.equals("package")) {
//
//					String tempTable = Util.File.getContent(Resources.getResource("table.temp"));
//					
//					StringPrinter r = new StringPrinter();
//					String nameSpace = e.attributeValue("namespace");
//					r.println("<br>" + nameSpace);
//					tempTable = tempTable.replaceAll("HEAD", r.toString());
//					List<Element> actions = e.elements("action");
//
//					StringPrinter tableContent = new StringPrinter();
//					for (Element action : actions) {
//						tableContent.println("<tr>");
//						String aName = action.attributeValue("name");
//						String className = action.attributeValue("class");
//
//						try {
//							JavaFileImpl file = new JavaFileImpl(
//									JavaParser.parse(new File("src/main/java/"
//											+ toPath(className))));
//							List<MethodDeclaration> methods = file.getMethods();
//							TypeDeclaration type = file.getType();
//
//							List<MethodDeclaration> getMethods = getGetters(methods);
//
//							String comment = getCommet(type.getComment());
//
//							tableContent.println("<td><a " +
//									" title=\" " + getTitle(type.getComment())+ "\"" +
//									" href=\" " + buildLink(getMethods, nameSpace, aName)
//									+ "\">");
//							tableContent.println(comment);
//							tableContent.println("</a></td>");
//
//							tableContent.println("<td>");
//							tableContent.println(aName);
//							tableContent.println("</td>");
//
//							tableContent.println("<td>");
//							for (MethodDeclaration m : getMethods) {
//								String argName = Util.Str.firstToLowerCase(m
//										.getName().replaceFirst("get", ""));
//								Comment mc = m.getComment();
//								tableContent.println(argName + ":"
//										+ getCommet(mc) + "<br>");
//							}
//							tableContent.println("</td>");
//						} catch (Exception ee) {
//							throw Util.Exception.toRuntimeException(ee);
//						}
//
//						tableContent.println("</tr>");
//					}
//					tempTable = tempTable.replaceAll("TABLE_CONTENT",
//							tableContent.toString());
//					tables.println(tempTable);
//				}
//			}
//
//			temp = temp.replaceAll("PROTOCOLS", tables.toString());
//			String dstPath = "../vchd/src/main/webapp/gm/protocolDoc.jsp";
//			Util.File.write(dstPath, temp);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

	private static String buildLink(List<MethodDeclaration> getMethods,
			String nameSpace, String aName) {
		String head = "http://19vc.com/vgame-a" + nameSpace + "/"
				+ aName;
		if (getMethods.size() > 0) {
			head += "?";
		}

		Fetcher<MethodDeclaration, Object> f = new Fetcher<MethodDeclaration, Object>() {

			@Override
			public Object get(MethodDeclaration m) {

				String argName = Util.Str.firstToLowerCase(m.getName()
						.replaceFirst("get", ""));
				Comment mc = m.getComment();
				// tableContent.println(argName + ":" + getCommet(mc) + "<br>");
				return argName + "=XXXXXX";
			}
		};

		return head + Util.Collection.linkWith("&", getMethods, f);
	}

	private static String getCommet(Comment mc) {
		if (mc == null)
			return "未知";
		String content = mc.getContent();
		String[] split = content.split("[\\-]{10,100}");
		content = split[0];
		String replaceAll = content.replaceAll("\\*", "");
		return replaceAll.trim();
	}
	
	private static String getTitle(Comment mc) {
		if (mc == null)
			return "未知";
		String content = mc.getContent();
		String[] split = content.split("\\-{10,100}");
		try {
			content = split[1];
		} catch (Exception e) {
			throw new RuntimeException("请加入返回值说明  用 -------------分隔:" + mc);
		}
		String replaceAll = content.replaceAll("\\*", "");
		return replaceAll.trim();
	}

	private static List<MethodDeclaration> getGetters(
			List<MethodDeclaration> methods) {
		ArrayList<MethodDeclaration> cp = Lists.newArrayList(methods);

		ArrayList<MethodDeclaration> ls = Lists.newArrayList();
		for (MethodDeclaration m : methods) {
			String name = m.getName();
			if (name.startsWith("get") && hasSet(cp, name)) {
				ls.add(m);
			}
		}
		return ls;
	}

	private static boolean hasSet(ArrayList<MethodDeclaration> cp, String name) {
		name = name.replaceFirst("get", "set");
		for (MethodDeclaration m : cp) {
			if (m.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	private static String toPath(String className) {
		return className.replaceAll("\\.", "/") + ".java";
	}

	private static void generateMongoDB() {
		String[] ags = new String[] { "-dtoPath",
				"src/main/java/cn/vgame/a/gen/dtodefine", "-packageName",
				"cn.vgame.a.gen.dto", "-dstPath",
				"src/main/java/cn/vgame/a/gen/dto", };

		App.main(ags);
	}
}
