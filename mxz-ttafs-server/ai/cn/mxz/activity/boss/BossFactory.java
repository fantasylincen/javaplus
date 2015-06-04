//package cn.mxz.activity.boss;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import message.S;
//import cn.javaplus.common.db.DAO;
//import cn.javaplus.common.util.Util;
//import cn.mxz.BossEventMapTemplet;
//import cn.mxz.BossEventMapTempletConfig;
//import cn.mxz.activity.boss.BossServiceImpl.BossTemp;
//import cn.mxz.user.City;
//import cn.mxz.util.message.MessageSenderToAllUp;
//import db.dao.factory.CacheDaoFactory;
//import db.domain.BossData;
//
//public class BossFactory {
//
//	/**
//	 * 创建一个新的Boss
//	 * 
//	 * @param id
//	 * @param level
//	 * @param finder
//	 * @return
//	 */
//	private static Boss createNewBoss(int id, int level, City finder) {
//
//		DAO<Integer, BossData> DAO = CacheDaoFactory.getBossDataDAO();
//
//		BossData bd = new BossData();
//
//		bd.setFoundTime((int) (System.currentTimeMillis() / 1000));
//
//		bd.setBossTempletId(id);
//
//		bd.setBossId(DAO.nextId());
//
//		bd.setKiller("");
//
//		bd.setUname(finder.getId());
//
//		bd.setLevel(level);
//
//		int id2 = finder.getBossMission().getCurrentMap().getId();
//
//		bd.setMapId(id2);
//
//		for (int i = 0; i < BossData.HELPER_ID_LEN; i++) {
//
//			bd.setHelperId(i, "");
//		}
//
//		BossImpl bossImpl = new BossImpl(bd);
//
//		bd.setHp(bossImpl.getAttribute().getHp());
//
//		DAO.add(bd);
//
//		if (bossImpl.isSuperBoss()) {
//
//			new MessageSenderToAllUp().sendMessage(S.S71011, finder.getPlayer().getNick());
//		}
//
//		return bossImpl;
//	}
//
//	/**
//	 * 随机产生Boss
//	 * 
//	 * @param mapId
//	 *            地图ID
//	 */
//	public static void generateBoss(City city, int mapId) {
//
//		List<BossTemp> ls = buildBoss(mapId);
//
//		BossTemp bs = Util.getRandomOne(ls);
//
//		createNewBoss(bs.getId(), bs.getLevel(), city);
//	}
//
//	private static List<BossTemp> buildBoss(int mapId) {
//
//		BossEventMapTemplet temp = BossEventMapTempletConfig.get(mapId);
//
//		List<BossTemp> ls = new ArrayList<BossTemp>();
//
//		generateNormalBoss(temp, ls);
//
//		generateSuperBoss(temp, ls);
//
//		return ls;
//	}
//
//	private static void generateSuperBoss(BossEventMapTemplet temp, List<BossTemp> ls) {
//
//		String coreBossId = temp.getCoreBossId();
//
//		if (coreBossId.trim().isEmpty()) {
//
//			return;
//		}
//
//		String[] split = coreBossId.split(",");
//
//		int id = new Integer(split[0]);
//
//		int level = new Integer(split[1]);
//
//		ls.add(buildBoss(id, level));
//	}
//
//	private static BossTemp buildBoss(int id, int level) {
//
//		BossTemp bt = new BossTemp();
//
//		bt.setId(id);
//
//		bt.setLevel(level);
//
//		return bt;
//	}
//
//	private static void generateNormalBoss(BossEventMapTemplet temp, List<BossTemp> ls) {
//
//		String bossId = temp.getCommonBossId();
//
//		String[] split = bossId.split(",");
//
//		for (String bid : split) {
//
//			if (!bid.isEmpty()) {
//
//				// 怪物等级下限 怪物等级上限
//
//				int level = generatorRandomLevel(temp.getMonsterGradeMin(), temp.getMonsterGradeMax());
//
//				ls.add(buildBoss(new Integer(bid.trim()), level));
//			}
//		}
//	}
//
//	/**
//	 * 随机一个5的倍数的等级
//	 * 
//	 * @param min
//	 * @param max
//	 * @return
//	 */
//	private static int generatorRandomLevel(int min, int max) {
//
//		int x1 = min / 5;
//
//		int x2 = max / 5;
//
//		int x = Util.getRandomInt(x1, x2);
//
//		return x * 5;
//	}
//}
