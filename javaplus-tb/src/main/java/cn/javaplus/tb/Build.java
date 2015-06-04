package cn.javaplus.tb;

import cn.javaplus.mongodao.App;

public class Build {

	public static void main(String[] args) {
		String[] ags = new String[] { 
				"-dtoPath",
				"src/main/java/cn/javaplus/tb/gen/dtodefine", 
				"-packageName",
				"cn.javaplus.tb.gen.dto", 
				"-dstPath",
				"src/main/java/cn/javaplus/tb/gen/dto", };

		App.main(ags);
	}
}
