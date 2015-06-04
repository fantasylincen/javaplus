package cn.javaplus.plugins.generator.protocol.generator;

import java.io.File;

import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.GeneratorConfigImpl;

public class Caller {

	public void generate(File file) {

		GeneratorConfig config = new GeneratorConfigImpl();

		Generator generator = new ProtocolGenerator(config);

		generator.generator();
	}

}
