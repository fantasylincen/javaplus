package cn.vgame.a.turntable;

public class CoinInOut {

	private long caiJinOut;
	private long out;
	private long in;

	public void addCaiJinOut(long add) {
		this.caiJinOut += add;
	}

	public long getOut() {
		return out;
	}

	public long getIn() {
		return in;
	}

	public long getCaiJinOut() {
		return caiJinOut;
	}

	public void addIn(long add) {
		in += add;
	}

	public void addOut(long add) {
		out += add;
	}

}