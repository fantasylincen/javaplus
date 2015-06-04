package com.cnbizmedia;

import cn.javaplus.mongodao.App;

public class Build {

	public static void main(String[] args) {
		String[] ags = new String[] { 
				"-dtoPath",
				"src/main/java/com/cnbizmedia/gen/dtodefine", 
				"-packageName",
				"com.cnbizmedia.gen.dto", 
				"-dstPath",
				"src/main/java/com/cnbizmedia/gen/dto", };

		App.main(ags);
	}
}
