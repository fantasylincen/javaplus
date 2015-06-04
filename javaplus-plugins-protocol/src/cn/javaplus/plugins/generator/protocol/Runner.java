package cn.javaplus.plugins.generator.protocol;

import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.GeneratorConfigImpl;
import cn.javaplus.plugins.generator.protocol.generator.Generator;
import cn.javaplus.plugins.generator.protocol.generator.ProtocolGenerator;

public class Runner {

	public static void main(String[] args) {

		GeneratorConfig config = new GeneratorConfigImpl();

		Generator generator = new ProtocolGenerator(config);

		generator.generator();
	}
}
