package cn.mxz.loginreward;

import java.util.Collection;

import cn.mxz.bossbattle.Prize;

public class OpenResultImpl implements OpenResult {

	private Collection<Prize>	received;
	private int			day;
	private Collection<Prize> unReceived;

	public OpenResultImpl(Collection<Prize> received,Collection<Prize> unReceived, int day) {
		this.received = received;
		this.unReceived = unReceived;
		this.day = day;
	}

	@Override
	public int getContinuousDay() {
		return day;
	}

	@Override
	public Collection<Prize> getReceived() {
		return received;
	}

	@Override
	public Collection<Prize> getUnReceived() {
		return unReceived;
	}

}
