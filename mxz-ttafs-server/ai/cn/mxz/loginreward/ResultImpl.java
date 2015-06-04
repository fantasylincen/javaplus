package cn.mxz.loginreward;

public class ResultImpl implements Result {

	private int	gold;
	private int	next;

	public ResultImpl(int gold, int next) {
		this.gold = gold;
		this.next = next;
	}

	@Override
	public int getNextGold() {
		return next;
	}

	@Override
	public int getReceived() {
		return gold;
	}

}
