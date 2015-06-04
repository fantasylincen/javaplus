package cn.javaplus.build.protocol.util;

import japa.parser.ast.Comment;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.type.ReferenceType;
import japa.parser.ast.type.Type;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.build.protocol.generator.JavaFile;
import cn.javaplus.comunication.annotations.ServerToClientOnly;
import cn.javaplus.string.BaseTypeConfig;
import cn.javaplus.util.Util;

import com.google.common.collect.Sets;

public class Utils {

	public static String getFiledName(MethodDeclaration met) {
		String name = met.getName();
		if (name.startsWith("get")) {
			name = name.replaceFirst("get", "");
			name = Util.Str.firstToLowerCase(name);
		} else if (name.startsWith("has")) {
			name = name.replaceFirst("has", "");
			name = Util.Str.firstToLowerCase(name);
		} else if (name.startsWith("is")) {
			name = name.replaceFirst("is", "");
			name = Util.Str.firstToLowerCase(name);
		}
		return "_" + name;
	}

	public static String getGetterName(MethodDeclaration m) {
		String name = m.getName();
		if (name.startsWith("get")) {
			name = name.replaceFirst("get", "");
			name = "get" + Util.Str.firstToUpperCase(name);
			return name;
		}
		if (name.startsWith("is")) {
			name = "get" + Util.Str.firstToUpperCase(name);
			return name;
		}

		if (name.startsWith("has")) {
			name = "get" + Util.Str.firstToUpperCase(name);
			return name;
		}

		throw new RuntimeException("实体接口的方法名字必须以get 或者 is 或者 has 开头!"
				+ m.getParentNode());
	}

	public static String getBaseFiledName(MethodDeclaration met) {
		String filedName = getFiledName(met);
		return filedName.replaceFirst("_", "");
	}

	/**
	 * 是否是容器类
	 * 
	 * @param met
	 * @return
	 */
	public static boolean isCollection(MethodDeclaration met) {
		Type type = met.getType();
		return isCollection(type);
	}

	public static boolean isCollection(Type type) {
		String o = type + "";
		o = o.replaceAll("<.*>", "");
		if (type instanceof ReferenceType) {
			Set<String> sets = Sets.newHashSet("List", "Collection", "Set");
			return sets.contains(o);
		}
		return false;
	}

	public static boolean isCollection(String type) {
		type = type.replaceAll("<.*>", "");
		HashSet<String> s = Sets.newHashSet("List", "Collection", "Set");
		return s.contains(type);
	}

	public static String getEventClassName(JavaFile class1,
			MethodDeclaration method) {
		return class1.getClassSimpleName()
				+ Util.Str.firstToUpperCase(method.getName()) + "Event";
	}

	public static String getFiledType(MethodDeclaration m) {
		japa.parser.ast.type.Type t = m.getType();
		return getFiledType(t);
	}

	public static String getFiledType(japa.parser.ast.type.Type t) {
		if (isCollection(t)) {
			String v = t + "";
			return v;
		}
		return t + "";
	}

	/**
	 * 
	 * 获得方法返回值里面装的是什么对象 返回该对象类名
	 * 
	 * @param method
	 *            该方法的返回值必须要是容器对象
	 * @return
	 */
	public static String getElementTypeName(MethodDeclaration method) {

		if (isCollection(method)) {
			japa.parser.ast.type.Type t = method.getType();

			String v = t + "";
			Pattern compile = Pattern.compile("<.*>");
			Matcher matcher = compile.matcher(v);

			if (matcher.find()) {
				return matcher.group().replaceAll("<|>", "");
			}
		}

		throw new RuntimeException(method + " 返回值必须要是容器对象");
	}

	/**
	 * 获取AS字段类型名字
	 * 
	 * @param m
	 * @return
	 */
	public static String getJavaFiledType(MethodDeclaration m) {
		String filedName = getFiledType(m);
		String parseASType = BaseTypeConfig.parseASType(filedName);
		if (parseASType == null) {
			return filedName;
		}
		return parseASType;
	}

	/**
	 * 方法返回值类型的包路径
	 * 
	 * @param method
	 * @return
	 */
	public static String getReturnTypePackage(MethodDeclaration method) {

		ClassOrInterfaceDeclaration _class = (ClassOrInterfaceDeclaration) method
				.getType().getParentNode().getParentNode();

		CompilationUnit javaFile = (CompilationUnit) _class.getParentNode();

		PackageDeclaration p = javaFile.getPackage();

		String pp = getPacketName(p);

		return pp + "." + method.getType();
	}

	/**
	 * 包名
	 * 
	 * @param p
	 * @return
	 */
	public static String getPacketName(PackageDeclaration p) {
		return (p + "").replaceAll("package", "").replaceAll(";", "").trim();
	}

	/**
	 * 类的注释
	 * 
	 * @param c
	 * @return
	 */
	public static String getComment(JavaFile c) {
		Comment comment = c.getType().getComment();
		String cc = comment != null ? comment + "" : "";
		return cc;
	}

	/**
	 * 方法的注释
	 * 
	 * @param m
	 * @return
	 */
	public static String getComment(MethodDeclaration m) {
		Comment comment = m.getComment();
		if (comment != null) {
			return comment + "";
		}
		return "";
	}

	/**
	 * 获得方法返回值的 类型的全路径
	 * 
	 * @param type
	 * @param c
	 * @return
	 */
	public static String getFullPath(MethodDeclaration m) {
		CompilationUnit n = (CompilationUnit) m.getParentNode().getParentNode();
		List<ImportDeclaration> imports = n.getImports();

		Type type = m.getType();
		for (ImportDeclaration i : imports) {
			String string = (i + "").trim();
			String suffix = type + ";";

			if (Utils.isCollection(type)) {
				suffix = suffix.replaceAll("<.*>", "");
				if (string.endsWith(suffix)) {
					return string.replaceFirst("import ", "").replace(";", "");
				}
			} else {
				if (string.endsWith(suffix)) {
					return string.replaceFirst("import ", "").replace(";", "")
							+ "." + type;
				}
			}
		}

		String name = getPacketName(n.getPackage());
		if (name.isEmpty()) {
			return type + "";
		} else {
			return name + "." + type;
		}
	}

	public static boolean isBaseType(Type type) {
		return BaseTypeConfig.contains(type + "");
	}

	public static String getActionClassName(JavaFile class1,
			MethodDeclaration method) {
		return class1.getClassSimpleName()
				+ Util.Str.firstToUpperCase(method.getName()) + "Action";
	}

	public static String getActionClassName(MethodDeclaration method) {
		return Util.Str.firstToUpperCase(method.getName()) + "Action";
	}

	public static String getListenerName(MethodDeclaration method) {
		String name = method.getName();
		return name + "Listener";
	}

	public static String getHandlerField(JavaFile class1) {
		return Util.Str.firstToLowerCase(class1.getClassSimpleName());
	}

	public static String getListenerClassName(MethodDeclaration m) {
		return "Abstract" + Util.Str.firstToUpperCase(m.getName()) + "CallBack";
	}

	public static String getListenersClassName(JavaFile c) {
		String name = c.getClassSimpleName();
		return name + "Listeners";
	}

	public static boolean containsAnnotation(MethodDeclaration method,
			Class<?> c) {
		List<AnnotationExpr> es = method.getAnnotations();
		if (es == null) {
			return false;
		}
		for (AnnotationExpr e : es) {
			String name = e.getName() + "";
			String simpleName = c.getSimpleName();
			if (name.equals(simpleName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 查找类型 type 的全路径
	 * 
	 * @param class1
	 * @param type
	 * @return
	 */
	public static String getFullPath(JavaFile class1, String type) {
		TypeDeclaration t = class1.getType();
		CompilationUnit n = (CompilationUnit) t.getParentNode();
		List<ImportDeclaration> imports = n.getImports();

		for (ImportDeclaration i : imports) {
			String string = (i + "").trim();
			String suffix = type + ";";

			if (isCollection(type)) {
				suffix = suffix.replaceAll("<.*>", "");
				if (string.endsWith(suffix)) {
					return string.replaceFirst("import ", "").replace(";", "");
				}
			} else {
				if (string.endsWith(suffix)) {
					return string.replaceFirst("import ", "").replace(";", "")
							+ "." + type;
				}
			}
		}

		String name = getPacketName(n.getPackage());
		if (name.isEmpty()) {
			return type + "";
		} else {
			return name + "." + type;
		}
	}

	public static boolean isServerToClientOnly(MethodDeclaration m) {
		return containsAnnotation(m, ServerToClientOnly.class);
	}

}
