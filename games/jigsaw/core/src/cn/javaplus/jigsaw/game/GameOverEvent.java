package cn.javaplus.jigsaw.game;


public class GameOverEvent {

	private GameStage stage;
	private long time;
	private Cubes cubes;

	public GameOverEvent(Cubes cubes, GameStage stage, long time) {
		this.cubes = cubes;
		this.stage = stage;
		this.time = time;
	}

	public Cubes getCubes() {
		return cubes;
	}

	public GameStage getStage() {
		return stage;
	}

	public long getTime() {
		return time;
	}

}
