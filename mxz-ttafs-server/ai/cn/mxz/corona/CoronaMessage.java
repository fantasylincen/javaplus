package cn.mxz.corona;

import cn.mxz.bossbattle.Prize;

/**
 * 保存中奖信息广播到客户端
 * @author Administrator
 *
 */
class CoronaMessage {

	CoronaMessage(String id, Prize p) {
		uname = id;
		prize = p;
	}
	private final String		uname;
	private final Prize		prize;
	public String getUname() {
		return uname;
	}
	public Prize getPrize() {
		return prize;
	}


}
