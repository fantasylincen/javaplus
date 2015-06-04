package cn.javaplus.java;

import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;

import java.util.List;

/**
 * Java 文件, 这个Java文件里面只能有1 个类的定义
 * @author 林岑
 *
 */
public interface JavaFile {

	/**
	 * 类
	 * @return
	 */
	TypeDeclaration getType();

	/**
	 * 包
	 * @return
	 */
	String getPackage();

	/**
	 * 方法列表
	 * @return
	 */
	List<MethodDeclaration> getMethods();

	/**
	 * 类名
	 * @return
	 */
	String getClassSimpleName();

	/**
	 * 完整类名 包含了包路径
	 * @return
	 */
	String getClassFullName();

	List<ImportDeclaration> getImports();
}
