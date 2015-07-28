package cn.javaplus.game.shhz.assets;

public class RInCoreGenerator extends AbstractRGenerator {

	protected String getRConfigTemp() {
		return "CoreRConfig.temp";
	}

	protected String getRConfigClassTemp() {
		return "CoreRConfigClass.temp";
	}

	protected String getPath() {
		return "../core/gen/cn/javaplus/game/shhz/R.java";
	}

	protected String getRoot() {
		return "../android/assets/";
	}

}
