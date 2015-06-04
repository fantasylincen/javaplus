package cn.mxz.enemy;

import cn.mxz.FighterExpPrize;

public final class EmptyExpPrize implements FighterExpPrize {

	@Override
	public int getSingleCoins() {
		return 0;
	}

	@Override
	public String getMonsterDropOut() {
		return "";
	}

	@Override
	public String getLineBossDropOut() {
		return "";
	}

	@Override
	public String getBossId() {
		return "";
	}

	@Override
	public String getBossDropOut() {
		return "";
	}

	@Override
	public String getLineMonsterDropOut() {
		return "";
	}

	@Override
	public float getLineWilsonParam() {
		return 0;
	}
}