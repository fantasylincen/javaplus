package cn.mxz.bossbattle;

import java.util.List;

public interface IRankInfo {

	
	/**
	 * 前10名排行榜
	 * @return
	 */
	List<ISimpleChallenger> getTopChallenger();

	ISimpleChallenger getKiller();
	ISimpleChallenger getMyself();
	
}
