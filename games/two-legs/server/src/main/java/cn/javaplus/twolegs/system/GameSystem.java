package cn.javaplus.twolegs.system;

public class GameSystem {

	private static SystemCounter systemCounter;

	public static SystemCounter getCounter() {
		if (systemCounter == null) {
			systemCounter = new SystemCounter();
		}
		return systemCounter;
	}

}
