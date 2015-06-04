package cn.javaplus.stock;

import cn.javaplus.mongodao.App;


public class Build {

	public static void main(String[] args) {
		String[] ags = new String[] { 
				"-dtoPath",
				"src/main/java/cn/javaplus/stock/gen/dtodefine", 
				"-packageName",
				"cn.javaplus.stock.gen.dto", 
				"-dstPath",
				"src/main/java/cn/javaplus/stock/gen/dto", };

		App.main(ags);
	}
}
