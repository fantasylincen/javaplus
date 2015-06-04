package cn.javaplus.generator.protocol.util;

import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.expr.NameExpr;

import java.util.List;

import cn.javaplus.comunication.annotations.Communication;
import cn.javaplus.java.JavaFile;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

/**
 * 通信类 过滤器
 *
 * @author 林岑
 */
public class ProtocolClassFilter implements ClassFilter {

	@Override
	public List<JavaFile> filter(List<JavaFile> cs) {
		List<JavaFile> ls = Lists.newArrayList();
		for (JavaFile c : cs) {
			if (containsAnnotation(c)) {
				ls.add(c);
			}
		}
		return ls;
	}

	public boolean containsAnnotation(JavaFile c) {
		TypeDeclaration type = c.getType();
		List<AnnotationExpr> as = type.getAnnotations();
		as = Util.Collection.nullToEmpty(as);
		for (AnnotationExpr annotation : as) {
			NameExpr name = annotation.getName();
			String n = name.getName();
			if (n.equals(Communication.class.getSimpleName())) {
				return true;
			}
		}
		return false;
	}
}
