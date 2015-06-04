package cn.mxz.compiler;

import cn.javaplus.build.code.EventsGenerator;
import cn.javaplus.mongodao.generator.MongoGenerator;

public class CodeCompiler {

	public void compiler() {
		new EventsGenerator()
				.generate(
						"C:/Users/Administrator/git/mxz/mxz-game-server/src/main/java/cn/mxz/server/events/Events.java",
						"C:/Users/Administrator/git/mxz/mxz-game-server/src/main/java/",
						"cn.mxz.server.events");

		new EventsGenerator()
				.generate(
						"C:/Users/Administrator/git/games/shooter/core/src/cn/javaplus/shooter/events/Events.java",
						"C:/Users/Administrator/git/games/shooter/core/src/",
						"cn.javaplus.shooter.events");

		new MongoGenerator()
				.generate(
						"C:/Users/Administrator/git/mxz/mxz-game-server/src/main/java/cn/mxz/server/mongodefine/",
						"C:/Users/Administrator/git/mxz/mxz-game-server/src/main/java/cn/mxz/server/mongo/",
						"cn.mxz.server.mongo");
	}

}
