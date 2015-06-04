import java.util.List;

import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;
import cn.mxz.FunctionOpenTemplet;
import cn.mxz.FunctionOpenTempletConfig;
import cn.mxz.city.FunctionOpenManager;

/*
/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
 */
/**
 * 功能开启
 * @author 林岑
 *
 */
public class FunctionOpenManagerBuilder {

	private static List<FunctionOpenTemplet> all;

	public static void build() {
		String temp = Util.File
				.getContent("res/build/FunctionOpenManager.temp");
		FunctionOpenTempletConfig.load();
		all = FunctionOpenTempletConfig.getAll();

		temp = temp.replaceAll("MODULES", buildModules());
		temp = temp.replaceAll("GET_TYPE", buildGetTypes());
		temp = temp.replaceAll("MODULE_TYPE_CASES", buildModuleTypeCases());
		temp = temp.replaceAll("INT_CASES", buildIntCases());

		String string = FunctionOpenManager.class.getSimpleName() + ".java";

		Util.File.write("ai\\cn\\mxz\\city\\" + string, temp);
	}

	private static String buildIntCases() {
		StringPrinter sp = new StringPrinter();
		for (FunctionOpenTemplet t : all) {
			sp.println(buildIntCase(t));
			sp.println("");
		}
		return sp.toString();
	}

	private static String buildIntCase(FunctionOpenTemplet t) {
		StringPrinter sp = new StringPrinter();
		sp.println("		case " + t.getId() + ":");
		sp.println("				return " + t.getLevel() + ";");
		return sp.toString();
	}

	private static String buildGetTypes() {
		StringPrinter sp = new StringPrinter();
		for (FunctionOpenTemplet t : all) {
			sp.println(buildIf(t));
			sp.println("");
		}
		return sp.toString();
	}

	private static String buildIf(FunctionOpenTemplet t) {
		StringPrinter sp = new StringPrinter();
		sp.println("			if(id == " + t.getId() + ") {");
		sp.println("				return " + buildTypeName(t) + ";");
		sp.println("			}");
		return sp.toString();
	}

	private static String buildModuleTypeCases() {
		StringPrinter sp = new StringPrinter();
		for (FunctionOpenTemplet t : all) {
			sp.println(buildCase(t));
			sp.println("");
		}
		return sp.toString();
	}

	private static String buildCase(FunctionOpenTemplet t) {
		StringPrinter sp = new StringPrinter();
		sp.println("		case " + buildTypeName(t) + ":");
		sp.println("				return " + t.getLevel() + ";");
		return sp.toString();
	}

	private static String buildModules() {
		StringPrinter sp = new StringPrinter();
		for (FunctionOpenTemplet t : all) {
			sp.println("		/**");
			sp.println("		 * " + buildTypeDoc(t));
			sp.println("		 */");
			sp.println("		" + buildTypeName(t) + ",");
			sp.println("");
		}
		return sp.toString();
	}

	private static String buildTypeDoc(FunctionOpenTemplet t) {
		return t.getName();
	}

	private static String buildTypeName(FunctionOpenTemplet t) {
		String name = t.getName();
		name = name.replaceAll("模組", "模块");
		name = name.replaceAll("成長計畫", "成长计划");
		name = name.replaceAll("限时商店", "限时黑市");
		
		
		String pinYinHump = Util.Chinese.getPinYinHump(name);
		pinYinHump = pinYinHump.replaceAll("ChengChangJiHua", "ChengZhangJiHua");
		return pinYinHump;
	}

}
