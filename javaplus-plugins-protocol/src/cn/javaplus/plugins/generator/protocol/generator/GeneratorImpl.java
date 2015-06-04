package cn.javaplus.plugins.generator.protocol.generator;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import _util.StringUtil;
import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.IClass;
import cn.javaplus.plugins.generator.protocol.config.IField;
import cn.javaplus.plugins.generator.protocol.config.IMethod;
import cn.javaplus.plugins.generator.protocol.config.IReturn;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;
import cn.javaplus.plugins.generator.protocol.util.Store;

public abstract class GeneratorImpl implements Generator {

	protected GeneratorConfig	config;
	private Paths				path;
	private String				childPackageName;

	public GeneratorImpl(GeneratorConfig config, Paths path, String childPackageName) {
		this.config = config;
		this.path = path;
		this.childPackageName = childPackageName;
	}

	/**
	 * 获得包路径
	 */
	protected String getPackageName() {
		String s = Store.getString(Paths.PACKAGE_NAME);
		if (childPackageName.isEmpty()) {
			return s;
		}
		return s + "." + childPackageName;
	}

	/**
	 * 生成的文件夹路径
	 * 
	 * @return
	 */
	protected String getSrcPath() {
		String s = getPackageName().replaceAll("\\.", "\\" + File.separator);
		return Store.getString(path) + File.separator + s;
	}

	protected String buildImports(IClass c) {

		List<IMethod> ms = c.getMethods();

		StringBuilder sb = new StringBuilder();

		Set<String> mark = new HashSet<String>();

		for (IMethod m : ms) {

			IReturn rt = m.getReturn();

			if (!mark.contains(rt.getType()) && !rt.isVoid() && !rt.isBaseType()) {

				mark.add(rt.getType());

				sb.append("	import " + rt.getType() + ";\r");
			}
		}

		return sb.toString();
	}

	protected String buildImports(List<IClass> all) {
		StringBuilder sb = new StringBuilder();

		for (IClass c : all) {
			sb.append(buildImports(c));
		}
		return sb.toString();
	}

	protected String buildWriteFields(IMethod m) {
		StringBuilder sb = new StringBuilder();

		sb.append("			p.writeInt(" + m.getId() + ");\r");
		for (IField f : m.getArgs()) {
			String type;
			if (f.getType().equals("String")) {
				type = "UTF";
			} else {
				type = StringUtil.firstToUpperCase(f.getType());
			}
			sb.append("			p.write" + type + "(" + f.getName() + ");\r");
		}
		return sb.toString();
	}
}
