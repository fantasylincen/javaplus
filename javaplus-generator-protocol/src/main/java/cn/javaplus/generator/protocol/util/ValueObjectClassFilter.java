package cn.javaplus.generator.protocol.util;

import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.expr.AnnotationExpr;

import java.util.List;

import cn.javaplus.comunication.annotations.Communication;
import cn.javaplus.java.JavaFile;

import com.google.common.collect.Lists;

/**
 * 实体类 过滤器
 *
 * @author 林岑
 */
public class ValueObjectClassFilter implements ClassFilter {

	@Override
	public List<JavaFile> filter(List<JavaFile> cs) {
		List<JavaFile> ls = Lists.newArrayList();
		for (JavaFile c : cs) {
			if (isValueObjectClass(c)) {
				ls.add(c);
			}
		}
		return ls;
	}

	public boolean isValueObjectClass(JavaFile f) {
		TypeDeclaration c = f.getType();

		if (c instanceof ClassOrInterfaceDeclaration) {
			ClassOrInterfaceDeclaration type = (ClassOrInterfaceDeclaration) c;

			if (type.isInterface()) {
				return !containsCommunicationAnnotation(type);
			}
		}

		return false;

	}

	/**
	 * 是否包含了通信类注解
	 *
	 * @param type
	 * @return
	 */
	private boolean containsCommunicationAnnotation(
			ClassOrInterfaceDeclaration type) {
		List<AnnotationExpr> annotations = type.getAnnotations();
		if (annotations == null) {
			annotations = Lists.newArrayList();
		}
		for (AnnotationExpr at : annotations) {
			String name = at.getName().getName();
			if (name.equals(Communication.class.getSimpleName())) {
				return true;
			}
		}
		return false;
	}
}
