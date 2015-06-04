package cn.mxz.tower;


class TowerFightingRewardImpl implements TowerFightingReward {

	private int	score;

	private int	calcCatchScore;

	TowerFightingRewardImpl(int score) {

		this.score = score;
	}

	@Override
	public int getScore() {

		return score;
	}

	@Override
	public int getCatchScore() {

		return calcCatchScore;
	}

	@Override
	public void addCatchScore(int calcCatchScore) {

		this.calcCatchScore += calcCatchScore;
	}

}
