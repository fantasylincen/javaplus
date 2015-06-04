package cn.mxz.hhdlb;

public class HhdlbUIAdaptor implements HhdlbUI {

	private HhdlbManager manager;

	public HhdlbUIAdaptor(HhdlbManager manager) {
		this.manager = manager;
	}

	@Override
	public int getAll() {
		return manager.getAll();
	}

	@Override
	public boolean getCanReceive() {
		return manager.canReceive();
	}

	@Override
	public boolean getHasReceive() {
		return manager.hasReceive();
	}

}
