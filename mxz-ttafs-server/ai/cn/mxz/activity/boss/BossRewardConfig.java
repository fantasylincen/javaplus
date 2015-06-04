//package cn.mxz.activity.boss;
//
//import java.util.List;
//
//class BossRewardConfig {
//
//	/**
//	 * 获得指定奖励类型的奖励
//	 *
//	 * @param awardType
//	 * @param boss
//	 * @return
//	 */
//	public static BossEventTemplet get(int awardType, Boss boss) {
//
//		List<BossEventTemplet> findByLevel = BossEventTempletConfig.findByLevel(boss.getLevel());
//
//		for (BossEventTemplet b : findByLevel) {
//
//			boolean superSame = boss.isSuperBoss() == (b.getIsFinal() == 1);
//
//			boolean awardTypeSame = b.getAwardType() == awardType;
//
//			if (superSame && awardTypeSame) {
//
//				return b;
//			}
//		}
//
//		return null;
//	}
//}
