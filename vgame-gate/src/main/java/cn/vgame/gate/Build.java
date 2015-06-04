package cn.vgame.gate;

import cn.javaplus.mongodao.App;

public class Build {

	public static void main(String[] args) {
		String[] ags = new String[] { 
				"-dtoPath",
				"src/main/java/cn/vgame/gate/gen/dtodefine", 
				"-packageName",
				"cn.vgame.gate.gen.dto", 
				"-dstPath",
				"src/main/java/cn/vgame/gate/gen/dto", };

		App.main(ags);
	}
}
