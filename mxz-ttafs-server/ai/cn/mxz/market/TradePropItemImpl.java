package cn.mxz.market;

import db.domain.UserMarket;

class TradePropItemImpl implements TradePropItem {

	private UserMarket data;

	TradePropItemImpl(UserMarket data) {
		
		this.data = data;
	}

	@Override
	public int getTypeProperty() {

		return data.getTradType();
	}

	@Override
	public int getNub() {
		
		return data.getTradNub();
	}

	@Override
	public Integer getTradId() {
		
		return data.getTradId();
	}

}
