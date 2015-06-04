package cn.mxz.base.world;

public class WorldFactory {

	static GameWorld	world;

	public static World getWorld() {

		if (world == null) {
			world = new GameWorld();
		}

		return world;
	}

}
