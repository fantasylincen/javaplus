//package cn.mxz;
//
//import java.util.List;
//
//import cn.javaplus.common.db.DAO;
//import db.InitDB;
//import db.dao.impl.OrderDataDAOImpl;
//import db.domain.OrderData;
//
//public class OrderProcesser extends Thread {
//
//	@Override
//	public void run() {
//
//		while(true) {
//
//			DAO<Integer, OrderData> DAO = new OrderDataDAOImpl(InitDB.getInstance());
//
//			List<OrderData> all = DAO.getAll();
//
//			all = cn.javaplus.common.util.Util.Collection.sub(all, 30);
//
//			for (OrderData data : all) {
//
//				Recharger recharger = new Recharger(data);
//
//				recharger.addListener(new MoveOrderListener(data));
//
//				recharger.addListener(new RechargeFaildListener(data));
//
//				recharger.recharge();
//			}
//
//			cn.javaplus.common.util.Util.Thread.sleep(1000);
//		}
//	}
//
//
//}
