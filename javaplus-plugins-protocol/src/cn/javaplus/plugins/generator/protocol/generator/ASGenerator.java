package cn.javaplus.plugins.generator.protocol.generator;

import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;

public class ASGenerator implements Generator {

	private GeneratorConfig	config;

	public ASGenerator(GeneratorConfig config) {
		this.config = config;
	}

	@Override
	public void generator() {

		Generator g;

		g = new ASActionFactoryGenerator(config);
		g.generator();

		g = new ASActionInterfaceGenerator(config);
		g.generator();

		g = new ASActionImplGenerator(config);
		g.generator();

		g = new ASEventsGenerator(config);
		g.generator();

		g = new ASDataHandlerGenerator(config);
		g.generator();

	}

}
