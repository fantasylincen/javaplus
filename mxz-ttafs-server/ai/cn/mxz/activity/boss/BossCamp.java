//package cn.mxz.activity.boss;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.mxz.battle.AbstractCamp;
//import cn.mxz.fighter.Fighter;
//
///**
// * Boss的阵容, 这个阵容专门用于放置一个Boss
// *
// * @author 林岑
// *
// */
//class BossCamp extends AbstractCamp<Boss> {
//
//	private List<Boss>	fighters;
//
//	BossCamp(Boss boss) {
//
//		fighters = new ArrayList<Boss>();
//
//		fighters.add(boss);
//	}
//
//	@Override
//	public List<Boss> getFighters() {
//		return fighters;
//	}
//
//	@Override
//	public int getPosition(Fighter f) {
//		return 7;
//	}
//
//	@Override
//	public int getShenJia() {
//		int r = 0;
//		for (Boss b : fighters) {
//			r += b.getShenJia();
//		}
//		return r;
//	}
//}
