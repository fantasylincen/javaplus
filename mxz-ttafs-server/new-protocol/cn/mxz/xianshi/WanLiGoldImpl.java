package cn.mxz.xianshi;

public class WanLiGoldImpl implements WanLiGold {

	private int	need;

	public WanLiGoldImpl(int need) {
		this.need = need;
	}

	@Override
	public int getValue() {
		return need;
	}

}
