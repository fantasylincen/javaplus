package cn.javaplus.clickscreen.compiler;

import cn.javaplus.build.code.EventsGenerator;

public class CodeCompiler {

	public void compiler() {

		new EventsGenerator()
				.generate(
						"C:/Users/Administrator/git/games/click-screen/core/src/cn/javaplus/clickscreen/events/Events.java",
						"C:/Users/Administrator/git/games/click-screen/core/src/",
						"cn.javaplus.clickscreen.events");
	}
}
