//package db.dao.impl;
//
//import java.util.List;
//
//import cn.javaplus.common.db.DAO;
//import db.dao.factory.MongoDaoFactory;
//import db.domain.UserData;
//
//public class MongoDaoTest {
//	public static void main(String[] args) {
//		DAO<String, UserData> DAO = MongoDaoFactory.getUserDataDAO();
////		for (int i = 10; i < 10000; i++) {
////			
////			UserData user = DAO.createDTO();
////			user.setNick("哈" + i);
////			user.setUname("3" + i);
////			DAO.add(user);
////			System.out.println("" + i);
////		}
//		List<UserData> all = DAO.getAll();
//		for (UserData userData : all) {
//			System.out.println(userData.getUname() + ":" + userData.getNick());
//			DAO.delete(userData.getUname());
//		}
////		for (int i = 0; i < 100000; i++) {
////			
////			System.out.println("dfad" + i);
////			DAO.delete("3lc10");
////			UserData s = DAO.get("31c10");
////			System.out.println(s.getNick());
////		}
//	}
//}
