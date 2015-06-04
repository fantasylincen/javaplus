package cn.mxz;

import cn.mxz.events.Listener2;
import cn.mxz.util.debuger.Debuger;
import db.domain.OrderData;

public class RechargeFaildListener implements Listener2 {

	private OrderData data;

	public RechargeFaildListener(OrderData data) {
		this.data = data;
	}

	@Override
	public void onEvent(Object e) {
		Debuger.debug("OrderProcesser.run() 订单处理失败!" + data.getOrderId()
				+ "    ----    " + data.getOrderData());
	}

	@Override
	public Class<?> getEventListendClass() {
		return RechargeFaildEvent.class;
	}

}
