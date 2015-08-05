package cn.vgame.a.turntable;

public class CoinInOut {

	private long out;
	private long in;


	public long getOut() {
		return out;
	}

	public long getIn() {
		return in;
	}

	public void addIn(long add) {
		in += add;
	}

	public void addOut(long add) {
		out += add;
	}

}