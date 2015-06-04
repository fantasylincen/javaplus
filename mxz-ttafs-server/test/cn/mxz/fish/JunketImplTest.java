//package cn.mxz.fish;
//
//import java.util.List;
//
//import org.junit.Test;
//
//import cn.javaplus.common.db.DAO2;
//import cn.mxz.testbase.TestBaseAccessed;
//import cn.mxz.user.City;
//import db.dao.factory.DaoFactory;
//import db.domain.UserFishes;
//import db.domain.UserFishesImpl;
//
//public class JunketImplTest extends TestBaseAccessed{
//
//	@Test
//	public final void testAddProp() {
//
//	}
//
//	@Test
//	public final void testGetAll() {
//
//		City city = getCity("lc56");
//
//		Junket junket = city.getJunket();
//
//		List<Fish> all = junket.getAll();
//
//		for (Fish fish : all) {
//
//			System.out.println(fish);
//		}
//	}
//
//	@Test
//	public void testA() throws Exception {
//
//		DAO2<Integer, String, UserFishes> DAO = DaoFactory.getUserFishesDAO();
//
//		int id = 1000;
//
//		for (int i = 0; i < 10; i++) {
//
//			UserFishes u = new UserFishesImpl();
//
//			u.setUname("lc56");
//
//			u.setGridId(id++);
//
//			u.setTypeid(160001);
//
//			u.setCount(5);
//
//			DAO.add(u);
//		}
//	}
//}
