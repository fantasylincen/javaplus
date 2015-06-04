package cn.mxz.generator.formula;

import java.util.List;
import java.util.Set;

import cn.javaplus.collections.map.ListMap;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;
import cn.mxz.IFormula;

import com.google.common.collect.Lists;

public class FormulaGenerator {

	public static void generate(List<IFormula> a) {
		List<ScriptClass> ls = classify(a); // 按类型进行分类
		StringPrinter sp = new StringPrinter();
		head(sp);
		for (ScriptClass s : ls) {
			sp.println(s.toString());
		}
		sp.println("}");
		Util.File.write("gen/cn/mxz/script/Script.java", sp.toString());
	}

	private static List<ScriptClass> classify(List<IFormula> a) {

		ListMap<String, IFormula> lm = new ListMap<String, IFormula>();
		for (IFormula f : a) {
			lm.add(f.getType().trim(), f);
		}

		Set<String> ks = lm.keySet();

		List<ScriptClass> ls = Lists.newArrayList();
		for (String s : ks) {
			List<IFormula> all = lm.get(s);
			ls.add(new ScriptClass(s, all));
		}
		return ls;

	}

	private static void head(StringPrinter sp) {
		sp.println("package cn.mxz.script;");
		sp.println("import static java.lang.Math.*;");
		sp.println("import java.util.List;");
		sp.println("import cn.javaplus.util.Util;");
		sp.println("public class Script extends ScriptBase {");
	}

}
