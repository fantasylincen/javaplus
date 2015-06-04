package cn.mxz;

public interface FighterExpPrize {

	/**
	 * Boss物品掉落
	 *
	 * @return
	 */
	String getBossDropOut();

	/**
	 * 支线Boss掉落
	 *
	 * @return
	 */
	String getLineBossDropOut();

	/**
	 * 小怪
	 *
	 * @return
	 */
	String getMonsterDropOut();

	String getBossId();

//	int getSingleExp();

	int getSingleCoins();

	String getLineMonsterDropOut();

	float getLineWilsonParam();
}
