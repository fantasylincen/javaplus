package cn.mxz.shenmo;

import cn.mxz.city.City;

public class PropExchanger {

	private City	city;

	public PropExchanger(City city) {
		this.city = city;
	}

	public void exchange(final int stuffId) {
//		final String exchangeNeed = StuffTempletConfig.get(stuffId).getExchangeNeed();
//		if(exchangeNeed.isEmpty()) {
//			throw new OperationFaildException(S.S10179);
//		}
//		NeedsChecker nc = NeedsFactory.getNeedsCheckerImpl2(new INeedsInExcel() {
//
//			@Override
//			public String getNeeds() {
//				return exchangeNeed;
//			}
//		});
//
//		nc.check(city.getPlayer());
//		create(stuffId);
//		nc.deduct(city.getPlayer());
	}

	private void create(int stuffId) {
		city.getBagAuto().addProp(stuffId, 1);
	}
}
