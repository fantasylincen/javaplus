package cn.mxz.bossbattle;

import java.util.List;

public interface IRankInfoWithMyself {
	/**
	 * 前10名排行榜
	 * @return
	 */
	List<ISimpleChallenger> getTopChallenger();

	/**
	 * 玩家自己的信息
	 * @return
	 */
	ISimpleChallenger getMyself();
}
