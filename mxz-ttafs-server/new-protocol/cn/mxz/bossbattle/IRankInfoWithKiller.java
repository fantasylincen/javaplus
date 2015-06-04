package cn.mxz.bossbattle;

import java.util.List;

public interface IRankInfoWithKiller {
	/**
	 * 前10名排行榜
	 * @return
	 */
	List<ISimpleChallenger> getTopChallenger();

	/**
	 * boss击杀者的信息
	 * @return
	 */
	ISimpleChallenger getKiller();
	
	int getBossHpMax();
}
