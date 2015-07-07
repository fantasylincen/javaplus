package cn.javaplus.stock.t;

import cn.javaplus.stock.quotes.StockRecord;

public class PriceAdaptor implements IPrice {

	private StockRecord r;

	public PriceAdaptor(StockRecord r) {
		this.r = r;
		
	}

	@Override
	public double getPrice() {
		return new Double(r.getPriceNow());
	}

	@Override
	public double getUp() {
		return r.getUp();
	}

	@Override
	public long getV() {
		return new Long(r.getChengJiaoLiang());
	}

	@Override
	public long getSell1() {
		return new Long(r.getSellCount1());
	}

	@Override
	public long getSell2() {
		return new Long(r.getSellCount2());
	}

	@Override
	public long getSell3() {
		return new Long(r.getSellCount3());
	}

	@Override
	public long getSell4() {
		return new Long(r.getSellCount4());
	}

	@Override
	public long getSell5() {
		return new Long(r.getSellCount5());
	}

	@Override
	public long getBuy1() {
		return new Long(r.getBuyCount1());
	}

	@Override
	public long getBuy2() {
		return new Long(r.getBuyCount2());
	}

	@Override
	public long getBuy3() {
		return new Long(r.getBuyCount3());
	}

	@Override
	public long getBuy4() {
		return new Long(r.getBuyCount4());
	}

	@Override
	public long getBuy5() {
		return new Long(r.getBuyCount5());
	}

}
