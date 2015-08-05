package cn.javaplus.twolegs.game;

public class GetBestScoreEvent {

	private float oldScore;
	private float newScore;

	public GetBestScoreEvent(float newScore, float oldScore) {
		this.newScore = newScore;
		this.oldScore = oldScore;
	}

	public float getNewScore() {
		return newScore;
	}

	public float getOldScore() {
		return oldScore;
	}
}
