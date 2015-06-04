package cn.javaplus.mongodao;

import cn.javaplus.mongodao.generator.MongoGenerator;
import cn.javaplus.util.MainArgs;

public class App {

	/**
	 * @param args
	 * 
	 *            例子: 若: 执行 java App PACKAGE_NAME mongo.gen A=1 B=2 则:
	 *            Config.get("PACKAGE_NAME") == mongo.gen Config.get("A") == 1
	 *            Config.get("B") == 2
	 * 
	 */
	// 若找不到参数, 请到该项目config文件中找寻
	public static void main(String[] args) {
		MainArgs.set(args);
		String find = MainArgs.find("dtoPath");
		String find2 = MainArgs.find("dstPath");
		String find3 = MainArgs.find("packageName");
		new MongoGenerator().generate(find, find2, find3);
	}
}
