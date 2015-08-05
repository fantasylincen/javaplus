package cn.javaplus.twolegs.game;

public class GameOverEvent {

	private float x;
	private String scoreText;
	private GameStage stage;
	private int touchTimes;

	public GameOverEvent(GameStage stage, float x, String scoreText, int touchTimes) {
		this.stage = stage;
		this.touchTimes = touchTimes;
		this.x = Math.max(0, x);
		this.scoreText = scoreText;
	}

	/**
	 * 机器人走了多少米
	 * 
	 * @return
	 */
	public float getScore() {
		return x;
	}

	public String getScoreText() {
		return scoreText;
	}

	public GameStage getStage() {
		return stage;
	}

	public int getTouchTimes() {
		return touchTimes;
	}
}
