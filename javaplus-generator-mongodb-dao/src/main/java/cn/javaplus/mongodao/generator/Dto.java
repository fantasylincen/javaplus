package cn.javaplus.mongodao.generator;

import japa.parser.JavaParser;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.SingleMemberAnnotationExpr;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.db.mongo.Key;
import cn.javaplus.java.JavaFile;
import cn.javaplus.java.JavaFileImpl;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

/**
 * 数据库 传输层对象
 * 
 * @author 林岑
 * 
 */
public class Dto {

	private static final String DTO_TAIL = "Dto";
	private JavaFile file;

	/**
	 * @param file
	 *            某个Dto的java文件
	 */
	public Dto(File file) {
		try {
			this.file = new JavaFileImpl(JavaParser.parse(file));
		} catch (Exception e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}

	/**
	 * 这个类上 是否有指定类型名字的 注解
	 * 
	 * @param className
	 * @return
	 */
	public boolean hasAnnotation(String className) {
		TypeDeclaration type = file.getType();
		List<AnnotationExpr> annotations = type.getAnnotations();
		if (annotations != null) {
			for (AnnotationExpr a : annotations) {
				String name = a.getName().getName();
				if (name.equals(className)) {
					return true;
				}
			}
		}
		return false;
	}

	public List<Annotation> getAnnotations(String annotationName) {
		List<AnnotationExpr> as = file.getType().getAnnotations();
		as = Util.Collection.nullToEmpty(as);
		Iterator<AnnotationExpr> it = as.iterator();
		while (it.hasNext()) {
			AnnotationExpr a = it.next();
			if (!a.getName().getName().equals(annotationName)) {
				it.remove();
			}
		}
		return build(as);
	}

	private List<Annotation> build(List<AnnotationExpr> as) {
		List<Annotation> ls = Lists.newArrayList();
		for (AnnotationExpr a : as) {
			if (a instanceof SingleMemberAnnotationExpr) {
				SingleMemberAnnotationExpr aa = (SingleMemberAnnotationExpr) a;
				Expression mv = aa.getMemberValue();
				ls.add(new Annotation(a.getName().getName(), (mv + "").replaceAll("\"", "")));
			}
		}
		return ls;
	}

	public String getDaoClassName() {
		return getSimpleClassName() + "Dao";
	}

	public String getDtoClassName() {
		return getSimpleClassName() + DTO_TAIL;
	}

	public List<Field> getFields() {
		List<MethodDeclaration> ms = file.getMethods();
		List<Field> ls = Lists.newArrayList();
		for (MethodDeclaration m : ms) {
			ls.add(new Field(m));
		}
		return ls;
	}

	public String getSimpleClassName() {
		return file.getClassSimpleName();
	}

	/**
	 * 转换为Dto名字
	 * 
	 * @param type
	 * @return
	 */
	public static String toDtoName(String type) {
		return type + DTO_TAIL;
	}

	public List<Field> getKeys() {
		List<MethodDeclaration> ms = file.getMethods();
		List<Field> ls = Lists.newArrayList();
		for (MethodDeclaration m : ms) {
			if (hasAnnotation(m, Key.class.getSimpleName())) {
				ls.add(new Field(m));
			}
		}
		return ls;
	}

	/**
	 * 判断某个方法是否有annotationName注解
	 * 
	 * @param m
	 * @param annotationName
	 * @return
	 */
	private boolean hasAnnotation(MethodDeclaration m, String annotationName) {
		List<AnnotationExpr> annotations = m.getAnnotations();
		if (annotations != null) {
			for (AnnotationExpr a : annotations) {
				String name = a.getName().getName();
				if (name.equals(annotationName)) {
					return true;
				}
			}
		}
		return false;
	}

}
