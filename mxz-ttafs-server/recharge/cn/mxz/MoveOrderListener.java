//package cn.mxz;
//
//import cn.mxz.event.Listener;
//import cn.mxz.event.ServerEvent;
//import cn.mxz.util.debuger.Debuger;
//import db.InitDB;
//import db.dao.impl.OrderDataDAOImpl;
//import db.dao.impl.OrderDataProcessdDAOImpl;
//import db.domain.OrderData;
//import db.domain.OrderDataProcessd;
//
//public class MoveOrderListener implements Listener {
//
//	private OrderData	data;
//
//	public MoveOrderListener(OrderData data) {
//		this.data = data;
//	}
//
//	@Override
//	public void onEvent(ServerEvent e) {
//
//		moveToProcessed();
//
//		Debuger.debug("OrderProcesser.run() 订单处理完毕!" + data.getOrderId() + "    ----    " + data.getOrderData());
//	}
//
//	@Override
//	public Class<? extends ServerEvent> getEventListendClass() {
//		return RechargeSuccessEvent.class;
//	}
//
//	/**
//	 * 将该订单移动到已经处理的订单表中去
//	 *
//	 * @param data
//	 */
//	private void moveToProcessed() {
//
//		OrderDataDAOImpl DAO = new OrderDataDAOImpl(InitDB.getInstance());
//		DAO.delete(data.getOrderId());
//
//		OrderDataProcessdDAOImpl DAOP = new OrderDataProcessdDAOImpl(InitDB.getInstance());
//		data.setOrderId(DAOP.nextId());
//
//		DAOP.add(parse(DAOP, data));
//	}
//
//	private OrderDataProcessd parse(OrderDataProcessdDAOImpl dAOP, OrderData data) {
//		OrderDataProcessd d = new OrderDataProcessd();
//		d.setOrderData(data.getOrderData());
//		d.setOrderId(dAOP.nextId());
//		return d;
//	}
//}
