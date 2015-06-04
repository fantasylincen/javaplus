package cn.javaplus.mongodao.generator;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.type.Type;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.db.mongo.Index;
import cn.javaplus.db.mongo.Key;
import cn.javaplus.util.Util;

public class Field {

	private String name;
	private String type;
	private boolean hasIndex;

	public Field(MethodDeclaration m) {

		name = m.getName();
		if (name.startsWith("get")) {
			name = name.replaceFirst("get", "");
			name = Util.Str.firstToLowerCase(name);
		}

		Type type = m.getType();
		this.type = type + "";

		if (isList()) {
			String temp = getElementType();
			this.type = "List<" + toDtoType(temp) + ">";
		} else if (isMongoMap()) {
			String temp = getElementType();
			this.type = "MongoMap<" + toDtoType(temp) + ">";
		} else {
			this.type = toDtoType(this.type);
		}

		initHasIndex(m);
	}

	private void initHasIndex(MethodDeclaration m) {
		List<AnnotationExpr> annotations = m.getAnnotations();
		if (annotations != null) {
			for (AnnotationExpr a : annotations) {
				String n1 = a.getName().getName();
				String n2 = Index.class.getSimpleName();
				String n3 = Key.class.getSimpleName();

				if (n1.equals(n2) || n1.equals(n3)) {
					hasIndex = true;
					break;
				}
			}
		}
	}

	/**
	 * 如果是基本类型类型, 原样返回, 如果是自定义类型, 追加 "Dto"
	 * 
	 * @param temp
	 * @return
	 */
	private String toDtoType(String temp) {
		if (Util.JavaType.isBaseTypeArray(temp)) {
			return temp;
		}
		if (!Util.JavaType.isBaseType(temp)) {
			return Dto.toDtoName(temp);
		}
		return temp;
	}

	public boolean isList() {
		if (this.type.equals("List")) {
			throw new RuntimeException("返回值为List的方法中, 必须要用泛型指定List中的元素类型!");
		}
		return this.type.startsWith("List<");
	}

	public boolean isMongoMap() {
		if (this.type.equals("MongoMap")) {
			throw new RuntimeException("返回值为Map的方法中, 必须要用泛型指定Map中的元素类型!");
		}
		return this.type.startsWith("MongoMap<");
	}

	/**
	 * 获得列表中的元素类型
	 * 
	 * @return
	 */
	public String getElementType() {
		Pattern c = Pattern.compile("<.+>");
		String temp = this.type;
		Matcher m = c.matcher(temp);
		boolean find = m.find();
		if(!find)
			throw new RuntimeException("element type not found " + type + " " + name);
		String g = m.group();
		String r = g.replaceAll("<", "").replaceAll(">", "");
		return r;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public boolean hasIndex() {
		return hasIndex;
	}

	/**
	 * 是否是基本数据类型 int long ...boolean.. int[] byte[] ...
	 * 
	 * @return
	 */
	public boolean isBaseType() {
		return Util.JavaType.isBaseType(getType()) || isBaseArray();
	}

	private boolean isBaseArray() {
		return Util.JavaType.isBaseTypeArray(getType());
	}

	public String getTypeGetterName() {

		if (getType().equals("byte[]")) {
			return "getBytes";
		}

		String type = Util.JavaType.toPackagingType(getType());
		return "get" + Util.Str.firstToUpperCase(type);
	}

}
