import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.type.Type;

import java.util.List;
import java.util.Set;

import cn.javaplus.generator.protocol.ImplementConfig;
import cn.javaplus.generator.protocol.generator.util.Utils;
import cn.javaplus.generator.protocol.util.ProtocolClassFilter;
import cn.javaplus.generator.protocol.util.ValueObjectClassFilter;
import cn.javaplus.java.JavaFile;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Checker {

	private static Set<String> allKeyWords;

	static {

		List<String> ls = Util.File
				.getLines("src/main/resources/keywords.properties");

		allKeyWords = Sets.newHashSet(ls);
	}

	public void check(List<JavaFile> cs) {
		for (JavaFile f : cs) {
			check(f);
		}
	}

	private void check(JavaFile f) {
		checkMethodsName(f); // 不可有同名方法
		checkSetUserMethod(f);
		checkVo(f);
		checkImpl(f);
		checkHasMethod(f);
		checkNotBaseType(f);
	}

	private void checkNotBaseType(JavaFile f) {
		if (!new ProtocolClassFilter().containsAnnotation(f)) {
			return;
		}
		List<MethodDeclaration> ms = f.getMethods();
		for (MethodDeclaration m : ms) {
			Type t = m.getType();
			String name = t + "";

			boolean isList = name.equals("List") || name.startsWith("List<");

			boolean isBaseType = Util.JavaType.isBaseType(name);

			if (isBaseType) {
				throw new RuntimeException(
						" 往后拉----> 通信接口中的方法的返回值, 不能是基本数据类型! 只能是一个自定义的 通信层Dto: "
								+ f.getClassFullName() + "." + m.getName());
			}

			if (isList) {
				throw new RuntimeException(
						" 往后拉----> 通信接口中的方法的返回值 ! 不能是List, 只能是一个自定义的 通信层Dto: "
								+ f.getClassFullName() + "." + m.getName());
			}
		}
	}

	private void checkHasMethod(JavaFile f) {
		if (!new ProtocolClassFilter().containsAnnotation(f)) {
			return;
		}
		List<MethodDeclaration> ms = f.getMethods();
		for (MethodDeclaration m : ms) {
			if (m.getName().startsWith("has")) {
				System.err.println("警告: 不支持把has方法转换为字段! 请使用 getHas开头吧:" + f.getClassFullName() + ":" + m.getName());
			}
		}

	}

	private void checkSetUserMethod(JavaFile f) {
		if (!new ProtocolClassFilter().containsAnnotation(f)) {
			return;
		}
		List<MethodDeclaration> ms = f.getMethods();
		for (MethodDeclaration m : ms) {
			String name = m.getName();
			if (name.equals("setUser")) {
				List<Parameter> ts = m.getParameters();
				if (ts != null && ts.size() == 1) {
					return;
				}
			}
		}
		throw new RuntimeException(
				" 接口中必须要定义一个 void setUser(? extends ProtocolUser user) 方法!"
						+ f.getClassFullName());
	}

	private void checkImpl(JavaFile f) {
		if (!new ProtocolClassFilter().containsAnnotation(f)) {
			return;
		}

		JavaFile f2 = ImplementConfig.get(f);
		if (f2 == null) {
			throw new RuntimeException("么有找到" + f.getClassFullName() + "的实现!");
		}
	}

	private void checkVo(JavaFile f) {
		if (!new ValueObjectClassFilter().isValueObjectClass(f)) {
			return;
		}
		List<MethodDeclaration> methods = f.getMethods();
		for (MethodDeclaration m : methods) {
			if (Utils.isSetUserMethod(m)) {
				continue;
			}
			List<Parameter> parameters = m.getParameters();
			if (parameters == null) {
				parameters = Lists.newArrayList();
			}
			if (!parameters.isEmpty()) {
				throw new RuntimeException("通信层传输对象的方法, 必须是无参方法!  "
						+ f.getType().getName() + "." + m.getName());
			}
		}
	}

	private static void checkMethodName(MethodDeclaration m, JavaFile c) {

		String name = m.getName();

		if (name.startsWith("is")) {
			name = name.replaceFirst("is", "");
			name = Util.Str.firstToLowerCase(name);
		} else if (name.startsWith("has")) {
			name = name.replaceFirst("has", "");
			name = Util.Str.firstToLowerCase(name);
		}

		if (allKeyWords.contains(name)) {
			throw new RuntimeException("方法名字中, 不能有关键字! 请换一个名字!" + "  class:"
					+ c.getClassFullName() + name);
		}
	}

	private void checkMethodsName(JavaFile f) {
		List<MethodDeclaration> ms = f.getMethods();

		Set<String> set = Sets.newHashSet();

		for (MethodDeclaration m : ms) {
			if (!isPublic(m)) {
				continue;
			}
			checkMethodName(m, f);
			String name = Utils.getBaseFiledName(m);
			if (set.contains(name)) {
				throw new RuntimeException("有同名方法:" + name
						+ " 注意如果 get has is 后的名字, 不可一样!" + f.getClassFullName());
			}
			set.add(name);
		}
	}

	/**
	 * 是否是公共方法
	 * 
	 * @param m
	 * @return
	 */
	private boolean isPublic(MethodDeclaration m) {
		return (m + "").trim().startsWith("public");
	}

}
