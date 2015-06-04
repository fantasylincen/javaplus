package cn.vgame.b;

import cn.javaplus.build.code.EventsGenerator;
import cn.javaplus.mongodao.App;

public class Build {

	public static void main(String[] args) {
		generateMongoDB();
		generateEvents();
	}

	private static void generateEvents() {
		new EventsGenerator().generate("src/main/java/cn/vgame/b/events/Events.java", "src/main/java/cn/vgame/", "cn.vgame.b.events");
	}

	private static void generateMongoDB() {
		String[] ags = new String[] { "-dtoPath",
				"src/main/java/cn/vgame/b/gen/dtodefine", "-packageName",
				"cn.vgame.b.gen.dto", "-dstPath",
				"src/main/java/cn/vgame/b/gen/dto", };

		App.main(ags);
	}
}
