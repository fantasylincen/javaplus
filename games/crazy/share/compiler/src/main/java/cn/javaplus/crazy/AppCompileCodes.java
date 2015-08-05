package cn.javaplus.crazy;

import cn.javaplus.build.code.ConstsGenerator;
import cn.javaplus.build.code.EventsGenerator;
import cn.javaplus.build.code.GitHomeChanger;
import cn.javaplus.build.code.MessageGenerator;
import cn.javaplus.build.code.MongoGenerator;
import cn.javaplus.build.protocol.generator.AssetsLoaderGenerator;
import cn.javaplus.build.protocol.generator.ProtocolGenerator;
import cn.javaplus.build.protocol.generator.RInCoreGenerator;

/**
 * 编译代码
 */
public class AppCompileCodes {
	public static final String CLIENT_PROTOCOLS_PATH = "../../client/core/src/cn/javaplus/crazy/Protocols.java";
	public static final String SERVER_PROTOCOLS_PATH = "../../server/core/src/main/java/cn/javaplus/crazy/protocol/Protocols.java";
	public static final String USER_TYPE = "cn.javaplus.crazy.user.User";
	public static final String JAVA_SRC_PATH = "../../server/core/src/main/java/";

	public static void main(String args[]) throws Exception {
		new GitHomeChanger().generate();// 修改 compiler GIT_HOME
		generateEvents();// 事件

		new ConstsGenerator().generate();// 常量
		new MessageGenerator().generate();// 消息

		new ProtocolGenerator().generate(JAVA_SRC_PATH, USER_TYPE,
				SERVER_PROTOCOLS_PATH, CLIENT_PROTOCOLS_PATH);// 协议
		new RInCoreGenerator().generate();// shhz-core R.java
		new AssetsLoaderGenerator().generate();// 资源
		new MongoGenerator().generate();
		// new UisDuplicator().duplicate(); //拷贝ui资源
	}

	private static void generateEvents() {
		EventsGenerator c = new EventsGenerator();

		String dst;
		String p;

		dst = "../../server/core/src/main/java/cn/javaplus/crazy/events/Events.java";
		p = "../../server/core/src/main/java/cn/javaplus/crazy";
		c.generate(dst, p);

		dst = "../../client/core/src/cn/javaplus/crazy/events/Events.java";
		p = "../../client/core/src/cn/javaplus/crazy";
		c.generate(dst, p);

		dst = "../../server/gate/src/main/java/cn/javaplus/crazy/events/Events.java";
		p = "../../server/gate/src/main/java/cn/javaplus/crazy";
		c.generate(dst, p);
	}

}
