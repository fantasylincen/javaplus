package cn.javaplus.plugins.generator.protocol.generator;

import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.MethodIDManager;

public class ProtocolGenerator implements Generator {

	private GeneratorConfig	config;

	public ProtocolGenerator(GeneratorConfig config) {
		this.config = config;
	}

	@Override
	public void generator() {

		MethodIDManager.reset();

		Generator gAS = new ASGenerator(config);
		Generator gJava = new JavaGenerator(config);

		gAS.generator();
		gJava.generator();

	}

}
