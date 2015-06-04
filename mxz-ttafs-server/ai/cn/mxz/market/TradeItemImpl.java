package cn.mxz.market;

import java.util.ArrayList;
import java.util.List;

import db.domain.UserMarket;

class TradeItemImpl implements TradeItem {

	private Integer fighterId;
	
	private List<UserMarket> propAll;

	private String uname;

	TradeItemImpl(String uname, Integer fighterId, List<UserMarket> propAll) {
		
		this.uname = uname;
		
		this.fighterId = fighterId;
		
		this.propAll = propAll;
	}

	@Override
	public TradeFighter getMes() {
		
		int time = getTradeTime();
		
		return new TradeFighterImpl(fighterId, uname, time);
	}

	/**
	 * 獲得該交易提出的時間
	 * @return
	 */
	private int getTradeTime() {

		for (UserMarket u : propAll) {
			
			return u.getTime();
		}
		
		return (int) (System.currentTimeMillis() / 1000);
	}

	@Override
	public List<TradePropItem> getAllProp() {
		
		List<TradePropItem> ls = new ArrayList<TradePropItem>();
		
		for (UserMarket m : this.propAll) {
			
			ls.add(new TradePropItemImpl(m));
		}
		
		return ls;
	}

}
