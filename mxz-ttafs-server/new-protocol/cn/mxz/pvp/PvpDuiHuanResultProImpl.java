package cn.mxz.pvp;

public class PvpDuiHuanResultProImpl implements PvpDuiHuanResultPro {

	private PvpDHItem p;
	private int rongYu;

	public PvpDuiHuanResultProImpl(PvpDHItem p, int rongYu) {
		this.p = p;
		this.rongYu = rongYu;
	}

	@Override
	public PvpDuiHuanItem getItem() {
		return new PvpDuiHuanItemImpl(p);
	}

	@Override
	public int getRongYu() {
		return rongYu;
	}

}
