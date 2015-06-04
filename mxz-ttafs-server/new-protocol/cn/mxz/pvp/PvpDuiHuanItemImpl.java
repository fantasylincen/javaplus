package cn.mxz.pvp;

public class PvpDuiHuanItemImpl implements PvpDuiHuanItem {

	private PvpDHItem i;

	public PvpDuiHuanItemImpl(PvpDHItem i) {
		this.i = i;
	}

	public int getTypeId() {
		return i.getTypeId();
	}

	public int getRongYuNeed() {
		return i.getRongYuNeed();
	}

	public int getChangeTimes() {
		return i.getChangeTimes();
	}

	public boolean getCanDuiHuan() {
		return i.getCanDuiHuan();
	}

	@Override
	public int getRemainSec() {
		return i.getRemainSec();
	}

}
