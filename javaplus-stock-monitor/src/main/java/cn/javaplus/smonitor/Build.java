package cn.javaplus.smonitor;

import cn.javaplus.mongodao.App;


public class Build {

	public static void main(String[] args) {
		String[] ags = new String[] { 
				"-dtoPath",
				"src/main/java/cn/javaplus/monichaogu/gen/dtodefine", 
				"-packageName",
				"cn.javaplus.monichaogu.gen.dto", 
				"-dstPath",
				"src/main/java/cn/javaplus/monichaogu/gen/dto", };

		App.main(ags);
	}
}
