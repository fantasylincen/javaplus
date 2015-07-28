package cn.javaplus.game.shhz;

import java.io.IOException;

import cn.javaplus.game.shhz.assets.AssetsLoaderGenerator;
import cn.javaplus.game.shhz.assets.CopyTextureShapes;
import cn.javaplus.game.shhz.assets.RInCompilerGenerator;
import cn.javaplus.game.shhz.assets.RInCoreGenerator;
import cn.javaplus.game.shhz.consts.ConstsGenerator;
import cn.javaplus.game.shhz.events.EventsGenerator;
import cn.javaplus.game.shhz.message.MessageGenerator;

public class App {

	public static void main(String[] args) throws IOException {
		new CopyTextureShapes().copy(); // 拷贝 texture_shanpes.json
		new EventsGenerator().generate();// 事件
		new ConstsGenerator().generate();// 常量
		new MessageGenerator().generate();//消息
		new AssetsLoaderGenerator().generate();// 资源
		new RInCompilerGenerator().generate();// shhz-compiler R.java
		new RInCoreGenerator().generate();// shhz-core R.java
	}
}
