package cn.mxz.battle;

import org.junit.Test;

import cn.mxz.handler.BattleService;
import cn.mxz.testbase.TestBaseAccessed;

public class BattleTest extends TestBaseAccessed {

	private static int	ID;

	private BattleService getBattleService() {
		return ((BattleService) factory.get("battleService"));
	}

	@Test
	public final void test() {
		for (int i = 0; i < 1000; i++) {
			BattleService s = getBattleService();
			s.fightingTest();
		}
	}

//	@Test
//	public final void test2() {
//
//		DAO<Integer, db.domain.WarSituation> DAO = DaoFactory.getWarSituationDAO();
//		ID = DAO.nextId();
//		db.domain.WarSituation o = new db.domain.WarSituationImpl();
//		o.setSituationId(ID);
//		Date t = new Date();
//		o.setCreateTime(t);
//		o.setData(new byte[0]);
//		DAO.add(o);
//
//
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String format = sf.format(t);
//		System.out.println(format);
//	}
//
//	@Test
//	public final void test3() {
//		DAO<Integer, db.domain.WarSituation> DAO = DaoFactory.getWarSituationDAO();
//		WarSituation o = DAO.get(ID);
//		Date t = o.getCreateTime();
//
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String format = sf.format(t);
//		System.out.println(format);
//	}

}
