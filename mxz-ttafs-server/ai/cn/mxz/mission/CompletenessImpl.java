package cn.mxz.mission;

public class CompletenessImpl implements Completeness {

	private int	n;
	private int	d;

	public CompletenessImpl(int n, int d) {
		this.n = n;
		this.d = d;
	}

	public int getN() {
		return n;
	}

	public int getD() {
		return d;
	}

}
