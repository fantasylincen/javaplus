package cn.javaplus.game.shhz.assets;

public class RInCompilerGenerator extends AbstractRGenerator {

	@Override
	protected String getRConfigTemp() {
		return "CompilerRConfig.temp";
	}

	@Override
	protected String getRConfigClassTemp() {
		return "CompilerRConfigClass.temp";
	}

	@Override
	protected String getPath() {
		return "src/main/java/cn/javaplus/game/shhz/gen/R.java";
	}

	@Override
	protected String getRoot() {
		return "src/main/resources/";
	}

}
