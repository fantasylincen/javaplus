package cn.javaplus.twolegs.compiler;

import cn.javaplus.build.code.EventsGenerator;
import cn.javaplus.mongodao.generator.MongoGenerator;

public class CodeCompiler {

	public void compiler() {

		new EventsGenerator()
				.generate(
						"C:/Users/Administrator/git/games/two-legs/core/src/cn/javaplus/twolegs/events/Events.java",
						"C:/Users/Administrator/git/games/two-legs/core/src/",
						"cn.javaplus.twolegs.events");

		new EventsGenerator()
				.generate(
						"C:/Users/Administrator/git/games/two-legs/server/src/cn/javaplus/twolegs/events/Events.java",
						"C:/Users/Administrator/git/games/two-legs/server/src/",
						"cn.javaplus.twolegs.events");

		new MongoGenerator()
				.generate(
						"C:/Users/Administrator/git/games/two-legs/server/src/main/java/cn/javaplus/twolegs/mongodefine",
						"C:/Users/Administrator/git/games/two-legs/server/src/main/java/cn/javaplus/twolegs/mongo",
						"cn.javaplus.twolegs.mongo");

	}
}
