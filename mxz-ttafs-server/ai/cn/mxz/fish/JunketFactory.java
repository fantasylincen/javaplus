//package cn.mxz.fish;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.google.common.collect.Lists;
//
//import cn.javaplus.common.db.DAO;
//import cn.mxz.FishWellTemplet;
//import cn.mxz.FishWellTempletConfig;
//import cn.mxz.user.City;
//import db.dao.factory.DaoFactory;
//import db.domain.UserFishes;
//import db.domain.UserJunket;
//import db.domain.UserJunketImpl;
//
//public class JunketFactory {
//
//	public static Junket createJunketFactory(City c) {
//
//		return new Junket() {
//
//			@Override
//			public Fish remove(int gridId) {
//				// TODO 自动生成的方法存根
//				return null;
//			}
//
//			@Override
//			public Fish getFish(int gridId) {
//				// TODO 自动生成的方法存根
//				return null;
//			}
//
//			@Override
//			public int getCount(int fishType) {
//				// TODO 自动生成的方法存根
//				return 0;
//			}
//
//			@Override
//			public int getCapacity() {
//				// TODO 自动生成的方法存根
//				return 0;
//			}
//
//			@Override
//			public List<Fish> getAll() {
//				// TODO 自动生成的方法存根
//				return Lists.newArrayList();
//			}
//
//			@Override
//			public void addProp(int type, int count) {
//				// TODO 自动生成的方法存根
//
//			}
//		};
//
////		JunketImpl j = new JunketImpl();
////
////		j.setCity(c);
////
////		j.setGrids(createFishes(c));
////
////		j.setJunketBaseInfo(createJunketBaseInfo(c));
////
////		return j;
//	}
//
//	private static UserJunket createJunketBaseInfo(City c) {
//
//		DAO<String, UserJunket> DAO = DaoFactory.getUserJunketDAO();
//
//		UserJunket j = DAO.get(c.getId());
//
//		if(j == null) {
//
//			j = new UserJunketImpl();
//
//			j.setUname(c.getId());
//
//			j.setCapacity(findInitSize(c.getPlayer().getLevel()));
//
//		}
//
//		return j;
//	}
//
//	private static int findInitSize(int level) {
//
//		List<FishWellTemplet> fs = FishWellTempletConfig.findByLevel(level);
//
//		int max = 0;
//
//		for (FishWellTemplet t : fs) {
//
//			if(t.getWellNumber() > max) {
//
//				max = t.getWellNumber();
//			}
//		}
//
//		return max;
//	}
//
//	private static List<Fish> createFishes(City c) {
//
//		List<UserFishes> all = DaoFactory.getUserFishesDAO().findByUname(c.getId());
//
//		List<Fish> fishes = new ArrayList<Fish>();
//
//		for (UserFishes u : all) {
//
//			fishes.add(new FishImpl(c, u));
//		}
//
//		return fishes;
//	}
//
//}
