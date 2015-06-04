//package cn.mxz;
//
//import java.util.List;
//
//import org.apache.mina.core.buffer.IoBuffer;
//import org.apache.mina.core.service.IoHandlerAdapter;
//import org.apache.mina.core.session.IoSession;
//
//import cn.javaplus.common.db.DAO;
//import cn.mxz.base.server.OrderResolver;
//import cn.mxz.util.debuger.Debuger;
//
//import com.alibaba.fastjson.util.UTF8Decoder;
//import com.google.common.collect.Lists;
//
//import db.InitDB;
//import db.dao.impl.OrderDataDAOImpl;
//import db.domain.OrderData;
//
//public class RechargeServerDataHandler extends IoHandlerAdapter {
//
//	@Override
//	public void messageReceived(IoSession session, Object message) throws Exception {
//
//		IoBuffer buffer = (IoBuffer) message;
//
//		String orders = buffer.getString(new UTF8Decoder());
//
//		String[] split = orders.split("\r\n");
//
//		try {
//
//			DAO<Integer, OrderData> DAO = new OrderDataDAOImpl(InitDB.getInstance());
//
//			List<OrderData> ls = build(DAO, split);
//
//			DAO.addAll(ls);
//
//			write(session, "success");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			write(session, e.getMessage());
//		}
//	}
//
//	private List<OrderData> build(DAO<Integer, OrderData> DAO, String[] split) {
//		List<OrderData> ls = Lists.newArrayList();
//		for (String string : split) {
//
//			string = string.trim();
//
//			new OrderResolver(string).check();
//
//			OrderData data = new OrderData();
//			data.setOrderData(string.trim());
//			data.setOrderId(DAO.nextId());
//			ls.add(data);
//
//			Debuger.debug("RechargeServerDataHandler.build() 提交新的订单:" + data.getOrderId() + "   ----  " + data.getOrderData());
//		}
//		return ls;
//	}
//
//	private void write(IoSession session, String string) {
//
//		IoBuffer b = IoBuffer.allocate(100);
//		b.put(string.getBytes());
//		b.flip();
//		session.write(b);
//	}
//}
