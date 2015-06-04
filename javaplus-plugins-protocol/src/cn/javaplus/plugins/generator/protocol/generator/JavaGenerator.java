package cn.javaplus.plugins.generator.protocol.generator;

import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;

public class JavaGenerator implements Generator {

	private GeneratorConfig	config;

	public JavaGenerator(GeneratorConfig config) {
		this.config = config;
	}

	@Override
	public void generator() {
		Generator g;

		g = new JavaDataHandlerGenerator(config);
		g.generator();

		g = new JavaPacketInterfaceGenerator(config);
		g.generator();

		g = new JavaMessageFactoryGenerator(config);
		g.generator();

		g = new JavaMessageSenderGenerator(config);
		g.generator();

		g = new JavaMessageSenderImplGenerator(config);
		g.generator();

		g = new JavaPacketNumberDefineGenerator(config);
		g.generator();
	}

}
