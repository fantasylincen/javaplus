package cn.mxz.base.handler;

import cn.javaplus.comunication.OnDataEvent;
import cn.javaplus.comunication.OnDataListener;
import cn.javaplus.db.KeyValueCollection;
import cn.mxz.base.config.KeyValueDefine;
import cn.mxz.base.config.KeyValueManagerImpl;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.temp.TempKey;
import cn.mxz.util.debuger.Debuger;

public class CashLogListener implements OnDataListener<City> {

	@Override
	public void beforeOnData(OnDataEvent<City> e) {
		City user = e.getUser();
		if (user != null) {
			KeyValueCollection<TempKey, Object> c = user.getTempCollection();

			c.put(TempKey.CASH, user.getPlayer().get(PlayerProperty.CASH));
			c.put(TempKey.GOLD, user.getPlayer().getGold());
		}
	}

	@Override
	public void afterOnData(OnDataEvent<City> e) {

		City user = e.getUser();

		if (user != null) {
			KeyValueCollection<TempKey, Object> c = user.getTempCollection();

			Object tcash = c.get(TempKey.CASH);
			Object tgold = c.get(TempKey.GOLD);
			
			if(tcash == null || tgold == null) {
				return;
			}
			
			final int cashOld = (Integer) tcash;
			final int goldOld = (Integer) tgold;

			final int cashNow = user.getPlayer().get(PlayerProperty.CASH);
			final int goldNow = user.getPlayer().getGold();

			if (cashNow != cashOld) {

				log(

				"Cash",

				e.getRequest().getClassName() + "." + e.getRequest().getMethodName(),

				cashNow - cashOld,

				cashNow,

				user.getPlayer().getNick(),

				user.getId());
			}

			if (goldNow != goldOld) {

				log(

				"Gold",

				e.getRequest().getClassName() + "." + e.getRequest().getMethodName(),

				goldNow - goldOld,

				goldNow,

				user.getPlayer().getNick(),

				user.getId());

				if (goldNow < goldOld) { // 如果是消费

					String method = e.getRequest().getClassName() + "." + e.getRequest().getMethodName();

					saveToDB(method, goldOld - goldNow);

				}
			}
		}
	}

	/**
	 * 记录消费点
	 *
	 * @param method
	 * @param gold
	 */
	private void saveToDB(String method, int gold) {

		KeyValueManagerImpl k = new KeyValueManagerImpl();

		String c = k.get(KeyValueDefine.GOLD_REDUCE, "|" + method);

		Integer count = new Integer(c);

		count += gold;

		k.put(KeyValueDefine.GOLD_REDUCE, count, "|" + method);
	}

	private void log(final String head, String explain, long d, long now, String userNick, String userId) {

		if (explain == null || explain.isEmpty()) {

			return;
		}

		final StringBuilder info = new StringBuilder();

		info.append(explain + ";"); // 前置说明

		info.append(userId + ";"); // 用户帐号

		info.append(userNick + ";"); // 用户昵称

		info.append(now + ";"); // 用户金币

		info.append(d); // 用户金币增量

		Debuger.info("[" + head + "]", info);
	}
}
