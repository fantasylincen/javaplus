package cn.javaplus.jigsaw.compiler;

import cn.javaplus.build.code.EventsGenerator;

public class CodeCompiler {

	public void compiler() {

		new EventsGenerator()
				.generate(
						"C:/Users/Administrator/git/games/jigsaw/core/src/cn/javaplus/jigsaw/events/Events.java",
						"C:/Users/Administrator/git/games/jigsaw/core/src/",
						"cn.javaplus.jigsaw.events");
	}
}
