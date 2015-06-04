//package cn.mxz.base.server;
//
//import java.util.List;
//
//import cn.mxz.PayTemplet;
//import cn.mxz.PayTempletConfig;
//import cn.mxz.base.server.linekong.LineKongException;
//import cn.mxz.city.CityFactory;
//import cn.mxz.city.PlayerProperty;
//import cn.mxz.user.City;
//import cn.mxz.user.Player;
//import cn.mxz.util.counter.CounterKey;
//import cn.mxz.util.counter.UserCounter;
//import cn.mxz.util.counter.UserCounterSetter;
//import cn.mxz.util.debuger.Debuger;
//import define.D;
//
///**
// * 订单解析器
// *
// * @author 林岑
// *
// */
//public class OrderResolver {
//
//	private String	order;
//
//	public OrderResolver(String order) {
//		this.order = order;
//	}
//
//	public int getCount() {
//		String[] split = order.split(":");
//		return new Integer(split[2]);
//	}
//
//	public City getCity() {
//		String[] split = order.split(":");
//		return CityFactory.getCity(split[1]);
//	}
//
//	public int getServerId() {
//		String[] split = order.split(":");
//		return new Integer(split[0]);
//	}
//
//	public String getPlatform() {
//		String[] split = order.split(":");
//		return split[3];
//	}
//
//	/**
//	 * 检查订单是否有效
//	 */
//	public void check() {
//		String regex = ".+";
//		if (!order.matches(regex)) {
//			throw new LineKongException("非法格式! 字符串必须匹配:" + regex);
//		}
//	}
//
//	void recharge() {
//
//		OrderResolver resolver = new OrderResolver(order);
//
//		int count = resolver.getCount();
//		if (count <= 0) {
//			return;
//		}
//
//		City city = resolver.getCity();
//		City user = city;
//		UserCounter his = user.getUserCounterHistory();
//		boolean isFirstRecharge = his.get(CounterKey.TOTAL_RECHARGE_GOLD_COUNT) == 0;
//		if (isFirstRecharge) {
//			his.set(CounterKey.FIRST_GOLD_COUNT, count);//
//		}
//
//		his.add(CounterKey.TOTAL_RECHARGE_GOLD_COUNT, count);//
//		UserCounterSetter uc = user.getUserCounter();
//		uc.add(CounterKey.TOTAL_RECHARGE_GOLD_COUNT, count);//
//
//		Player player = city.getPlayer();
//
//		player.add(PlayerProperty.GOLD, count);
//		
//		if (isFirstRecharge) {
////			player.add(PlayerProperty.GOLD, count * D.FIRST_RECHARGE_X);
//			city.getPlayer().add(PlayerProperty.GIFT_GOLD, count * D.FIRST_RECHARGE_X);
//		}
//
//		sendGift(city, count);
//
//		Debuger.debug("OrderResolver.recharge() 充值成功! " + getCity().getId() + " 充值数量:" + count);
//	}
//
//	private void sendGift(City city, int count) {
//
//		int count2 = find(count);
//
//		city.getPlayer().add(PlayerProperty.GIFT_GOLD, count2);
//	}
//
//	private int find(int count) {
//		String platform = getPlatform();
//
//		if (platform.equals("Android")) {
//
//			List<PayTemplet> findBySycee = PayTempletConfig.findByAndroidSycee(count);
//
//			if (findBySycee.isEmpty()) {
//				return 0;
//			}
//			return findBySycee.get(0).getAndroidSyceeGet();
//
//		} else if (platform.equals("IOS")) {
//
//			List<PayTemplet> findBySycee = PayTempletConfig.findByIosSycee(count);
//
//			if (findBySycee.isEmpty()) {
//				return 0;
//			}
//			return findBySycee.get(0).getIosSyceeGet();
//
//		}
//
//		return 0;
//	}
//
//}
