package cn.mxz.bossbattle;

import java.util.List;

import cn.mxz.bossbattle.ui.RankInfoWithKillerImpl;

/**
 * boss战的历史记录
 * @author Administrator
 *
 */
public class BossBattleHistory {

	private static final String		KEY = "BossBattleActivity";

	private IRankInfoWithKiller		history;
	private final int				bossHpMax;


	BossBattleHistory( int bossHpMax ){
		this.bossHpMax = bossHpMax;
		loadHistory();
	}
	/**
	 * 先保存到档期表，完成之后再换
	 */
	void saveHistory( List<Challenger> topTen, Challenger killer ) {

		history = new RankInfoWithKillerImpl( topTen, killer, bossHpMax);
	}

	/**
	 * 如果数据库不存在，则返回空
	 */
	void loadHistory() {
		
		history = new RankInfoWithKillerImpl();
	}

	public IRankInfoWithKiller get() {
		return history;
	}

	public static void main(String[] args) {
		//SimpleChallengerImpl i = new SimpleChallengerImpl(c);
	}
	/**
	 * @return bossHpMax
	 */
	public int getBossHpMax() {
		return bossHpMax;
	}
}
