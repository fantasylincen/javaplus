package cn.javaplus.build.protocol.generator;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.expr.AnnotationExpr;

import java.io.File;
import java.util.List;

import cn.javaplus.java.TooManyClassException;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class JavaFile {

	private CompilationUnit parse;

	public JavaFile(CompilationUnit parse) {
		this.parse = parse;
	}

	public JavaFile(String filePath) {
		this(path(filePath));
	}

	private static CompilationUnit path(String filePath) {
		try {
			return JavaParser.parse(new File(filePath));
		} catch (Exception e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}

	public TypeDeclaration getType() {
		List<TypeDeclaration> types = getTypes();
		if (types.isEmpty()) {
			return null;
		}
		if (types.size() != 1) {
			throw new TooManyClassException("一个文件只能有1个类" + parse);
		}
		return types.get(0);
	}

	public List<TypeDeclaration> getTypes() {
		List<TypeDeclaration> types = parse.getTypes();
		types = Util.Collection.nullToEmpty(types);
		return types;
	}

	public String getPackage() {
		return getPacketName(parse.getPackage());
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

	public String toString() {
		return getType().getName();
	}

	public List<MethodDeclaration> getMethods() {
		List<MethodDeclaration> ls = Lists.newArrayList();
		TypeDeclaration t = getType();
		List<BodyDeclaration> all = t.getMembers();
		for (BodyDeclaration b : all) {
			if (b instanceof MethodDeclaration) {
				MethodDeclaration m = (MethodDeclaration) b;
				ls.add(m);
			}
		}
		return ls;
	}

	public String getClassSimpleName() {
		return getType().getName();
	}

	public String getClassFullName() {
		if (getPackage().isEmpty()) {
			return getClassSimpleName();
		}
		return getPackage() + "." + getClassSimpleName();
	}

	public List<ImportDeclaration> getImports() {
		return parse.getImports();
	}

	public boolean containsAnnotation(Class<?> cas) {
		TypeDeclaration c = getType();
		if (c instanceof ClassOrInterfaceDeclaration) {
			ClassOrInterfaceDeclaration type = (ClassOrInterfaceDeclaration) c;

			if (type.isInterface()) {
				List<AnnotationExpr> annotations = type.getAnnotations();
				if (annotations == null) {
					annotations = Lists.newArrayList();
				}
				for (AnnotationExpr at : annotations) {
					String name = at.getName().getName();
					if (name.equals(cas.getSimpleName())) {
						return true;
					}
				}
				return false;
			}
		}
		return false;

	}

}
