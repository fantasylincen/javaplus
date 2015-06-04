package cn.mxz.heishi;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;

/**
 * 黑市
 *
 * @author 林岑
 *
 */
public class HeiShiTransformImpl implements HeiShiTransform {

	private City	user;

	@Override
	public HeiShiUI getUI() {
		HeiShi heiShi = user.getHeiShi();
		return new HeiShiUIAdaptor(heiShi);
	}

	@Override
	public HeiShiUI refresh() {
		HeiShi heiShi = user.getHeiShi();
		heiShi.refresh();
		return getUI();
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}
	
	
	@Override
	public HeiShiUI exchange(int id) {
		HeiShi heiShi = user.getHeiShi();
		heiShi.exchange(id);
		user.getUserCounterAuto().add(CounterKey.HEI_SHI_EXCHANGE_TIMES, 1);
		return getUI();
	}

	@Override
	public HeiShiSingleUI exchange2(int id) {
		HeiShi heiShi = user.getHeiShi();
		heiShi.exchange(id);
		user.getUserCounterAuto().add(CounterKey.HEI_SHI_EXCHANGE_TIMES, 1);
		return new HeiShiSingleUIAdaptor(heiShi, id);
	}

}
