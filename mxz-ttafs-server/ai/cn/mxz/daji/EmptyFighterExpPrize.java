package cn.mxz.daji;

import cn.mxz.FighterExpPrize;

class EmptyFighterExpPrize implements FighterExpPrize {

	@Override
	public String getBossId() {
		return "";
	}

	@Override
	public String getBossDropOut() {
		return "";
	}

	@Override
	public int getSingleCoins() {
		return 0;
	}

	@Override
	public String getLineBossDropOut() {
		return "";
	}

	@Override
	public String getMonsterDropOut() {
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
