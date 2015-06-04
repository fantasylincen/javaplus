package cn.mxz.loginreward;

import java.util.Collection;

import cn.mxz.bossbattle.Prize;

public interface OpenResult {

	int getContinuousDay();

	Collection<Prize> getReceived();

	Collection<Prize> getUnReceived();

}
