//package cn.mxz.init;
//
//import java.util.Collection;
//import java.util.Set;
//
//import cn.javaplus.common.db.DAO;
//import cn.javaplus.common.util.Util;
//import cn.mxz.base.world.WorldFactory;
//import cn.mxz.city.City;
//import cn.mxz.user.Player;
//
//import com.google.common.collect.Sets;
//
//import db.dao.factory.DaoFactory;
//import db.domain.ResetUser;
//import db.domain.ResetUserImpl;
//
//public class UserResetor {
//
//	private static final String SP = "_r";
//
//	public String getNewestId(String id) {
//		id = getId(id);
//		ResetUser u = lazyGet(id);
//		return id + getTail(u);
//	}
//
//	private String getId(String id) {
//		if (id.contains(SP)) {
//			id = id.split(SP)[0];
//		}
//		return id;
//	}
//
//	/**
//	 * 后缀
//	 *
//	 * @param u
//	 * @return
//	 */
//	private String getTail(ResetUser u) {
//		return SP + u.getNewestUserIdIndex();
//	}
//
//	private ResetUser lazyGet(String id) {
//		DAO<String, ResetUser> DAO = DaoFactory.getResetUserDAO();
//		ResetUser u = DAO.get(id);
//		if (u == null) {
//			u = new ResetUserImpl();
//			u.setUname(id);
//			DAO.add(u);
//		}
//		return u;
//	}
//
//	public void reset(City city) {
//		String id = getId(city.getId());
//		ResetUser u = lazyGet(id);
//		u.addNewestUserIdIndex(1);
//		DaoFactory.getResetUserDAO().update(u);
//
//		rename(city);
//	}
//
//	/**
//	 * 老账号昵称重命名
//	 *
//	 * @param city
//	 */
//	private void rename(City city) {
//		String newNick = getRandomNick();
//
//		Player player = city.getPlayer();
//		player.setNick(newNick);
//	}
//
//	public String getRandomNick() {
//		Set<String> xing = getAllXing();
//		Set<String> ming = getAllMing();
//
//		String x = Util.Random.getRandomOne(xing);
//		String m = Util.Random.getRandomOne(ming);
//
//		String newNick = x + m;
//		return newNick;
//	}
//
//	/**
//	 * 服务器内 所有玩家的名字
//	 *
//	 * @return
//	 */
//	private Set<String> getAllMing() {
//
//		Set<String> set = Sets.newHashSet();
//		Collection<City> all = WorldFactory.getWorld().getAll();
//
//		for (City city : all) {
//			String nick = city.getPlayer().getNick();
//			set.add(nick.substring(1, nick.length()));
//		}
//		return set;
//	}
//
//	/**
//	 * 服务器内 所有玩家的姓
//	 *
//	 * @return
//	 */
//	private Set<String> getAllXing() {
//
//		Set<String> set = Sets.newHashSet();
//		Collection<City> all = WorldFactory.getWorld().getAll();
//
//		for (City city : all) {
//			String nick = city.getPlayer().getNick();
//			set.add(nick.substring(0, 1));
//		}
//		return set;
//	}
//
//}
