package cn.javaplus.build.code;

import cn.javaplus.util.Util;

public class GitHomeChanger {

	public void generate() {
		String getenv = System.getenv("GIT_HOME");
		if (getenv == null) {
			getenv = "C:/Users/Administrator/git";
		}
		getenv = getenv.replaceAll("\\\\", "/");
		String content = Util.File.getContent(".project");
		content = content.replaceAll("<value>file.+git</value>",
				"<value>file:/" + getenv + "</value>");
		Util.File.write(".project", content);
	}

}
