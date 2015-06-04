package cn.mxz.task.achieve.tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import cn.javaplus.random.Fetcher;
import cn.javaplus.util.Util;
import cn.mxz.AchieveTaskTemplet;
import cn.mxz.AchieveTaskTempletConfig;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class _code_generator {

	public static void main(String[] args) throws IOException {

		AchieveTaskTempletConfig.load();
		List<Integer> keys = AchieveTaskTempletConfig.getKeys();

		createAchieveTaskPlayer();

		for (Integer id : keys) {

			createFile(id);
		}
	}

	private static void createAchieveTaskPlayer() throws IOException {

		StringBuilder sb = new StringBuilder();
		sb.append("package cn.mxz.task.achieve;\r");
		sb.append("public interface AchieveTaskPlayer {\r");
		sb.append(createMethods());
		sb.append("}\r");

		// &gt;
		// &gt;
		String name = System.getProperty("user.dir") + "/ai/cn/mxz/task/achieve/AchieveTaskPlayer.java";

		File f = new File(name);

		if (f.exists()) {

			f.createNewFile();
		}

		FileWriter fileWriter = new FileWriter(f);

		fileWriter.write(sb + "");

		fileWriter.flush();

		fileWriter.close();
	}

	private static String createMethods() {

		StringBuilder sb = new StringBuilder();

		List<AchieveTaskTemplet> all = AchieveTaskTempletConfig.getAll();

		Set<Method> ms = findMethods(all);

		for (Method method : ms) {
			sb.append("/**" + method.getDoc() + "*/\r");
			sb.append(method.getReturnType());
			sb.append(" " + method.getName() + "(" + build(method.getArgs()) + ");\r");
		}

		return sb + "";
	}

	private static Set<Method> findMethods(List<AchieveTaskTemplet> all) {

		Set<Method> set = Sets.newHashSet();

		for (AchieveTaskTemplet temp : all) {
			String method = temp.getArg1();

			String name = getName(method);
			List<String> args = getArgs(method);

			String returnType = getReturnType(method, temp);

			String doc = temp.getDescription();

			set.add(new MethodImpl(name, returnType, args, doc));

		}

		return set;
	}

	private static List<String> getArgs(String method) {
		method = method.replaceAll(".*\\(", "").replaceAll("\\).*", "");

		String[] split = method.split(",");

		List<String> ls = Lists.newArrayList();
		int index = 0;
		for (String string : split) {
			if (!string.isEmpty()) {
				ls.add("int a" + index++);
			}
		}

		return ls;
	}

	private static String getReturnType(String method, AchieveTaskTemplet temp) {
		if (method.startsWith("int:")) {
			return "int";
		}
		if (method.contains("&gt;")) {
			return "int";
		}
		if (method.contains("=")) {
			return "int";
		}
		if (method.contains("&lt;")) {
			return "int";
		}
		return temp.getReturnType();
	}

	private static String getName(String method) {
		return method.replaceAll("\\(.*", "").replaceAll("int:", "");
	}

	private static String build(List<String> args) {

		String linkWith = Util.Collection.linkWith(",", args, new Fetcher<String, String>() {

			@Override
			public String get(String t) {
				return t + " ";
			}
		});
		return linkWith;
	}

	@SuppressWarnings("resource")
	private static void createFile(Integer id) throws IOException {

		AchieveTaskTemplet temp = AchieveTaskTempletConfig.get(id);

		String code = temp.getCode().replaceAll("\\[newline\\]", "\r").replaceAll("\\&gt;", ">").replaceAll("\\&lt;", "<");

		code = "/**\r * " + temp.getDescription()  + "\r */\r\r"+ code;

		// &gt;
		// &gt;
		String name = System.getProperty("user.dir") + "/ai/" + _code_generator.class.getName();

		name = name.replaceAll("\\.", "/");

		name = name.replaceAll(_code_generator.class.getSimpleName(), "");

		name = name + "T" + id + ".java";

		name = name.replaceAll("/", "\\\\");

		File f = new File(name);

		if (f.exists()) {

			f.createNewFile();
		}

		FileWriter fileWriter = new FileWriter(f);

		if (code.isEmpty()) {
			code = "error";
		}

		fileWriter.write(code);

		fileWriter.flush();
	}
}
