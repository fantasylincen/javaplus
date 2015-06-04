package cn.mxz.zbsr;

public class ZbsrGoodsUIAdaptor implements ZbsrItem {

	private ZbsrGoods g;

	public ZbsrGoodsUIAdaptor(ZbsrGoods g) {
		this.g = g;
	}

	public String getName() {
		return g.getName();
	}

	public int getId() {
		return g.getId();
	}

	public String getNeeds() {
		return g.getNeeds();
	}

	@Override
	public int getEquipmentTempletId() {
		return g.getEquipmentTempletId();
	}

	@Override
	public int getGoldOld() {
		return g.getGoldOld();
	}

	@Override
	public int getGoldNew() {
		return g.getGoldNew();
	}

	@Override
	public boolean getIsTeJia() {
		return g.isTeJia();
	}

	@Override
	public boolean getCanReceive() {
		return g.getCanReceive();
	}

}
