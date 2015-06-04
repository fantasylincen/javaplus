//package cn.mxz.activity.boss;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.javaplus.common.db.DAO2;
//import cn.mxz.base.world.WorldFactory;
//import cn.mxz.city.PlayerProperty;
//import cn.mxz.user.City;
//import db.dao.factory.DaoFactory;
//import db.domain.BossScoreShared;
//
//public class FriendSharedPointsImpl implements FriendSharedPoints {
//
//	public class SharePointImpl implements SharePoint {
//
//		private BossScoreShared	shared;
//
//		private SharePointImpl(BossScoreShared shared) {
//
//			this.shared = shared;
//		}
//
//		@Override
//		public int getAll() {
//
//			return getCity().getPlayer().get(PlayerProperty.ACTIVITY_SCORE);
//		}
//
//		@Override
//		public int getRank() {
//
//			BossActivity activity = BossActivityImpl.getInstance();
//
//			int rank = activity.getRank(shared.getUanme());
//
//			return rank;
//		}
//
//		@Override
//		public int getShareToMe() {
//
//			return shared.getScore();
//		}
//
//		@Override
//		public City getCity() {
//
//			City city2 = WorldFactory.getWorld().get(shared.getUanme());
//
//			return city2;
//		}
//
//	}
//
//	private City	city;
//
//	public FriendSharedPointsImpl(City city) {
//		this.city = city;
//	}
//
//	@Override
//	public List<SharePoint> getAll() {
//
//		DAO2<String, String, BossScoreShared> DAO = DaoFactory.getBossScoreSharedDAO();
//
//		List<BossScoreShared> findBy = DAO.findBy("share_id", city.getId());
//
//		List<SharePoint> list = new ArrayList<SharePoint>();
//
//		for (BossScoreShared s : findBy) {
//
//			list.add(new SharePointImpl(s));
//		}
//
//		return list;
//	}
//
//	@Override
//	public int getAllPoint() {
//
//		int count = 0;
//
//		List<SharePoint> all = getAll();
//
//		for (SharePoint sharePoint : all) {
//
//			count += sharePoint.getShareToMe();
//		}
//
//		return count;
//	}
//
//	@Override
//	public void remove(SharePoint s) {
//
//		DAO2<String, String, BossScoreShared> DAO = DaoFactory.getBossScoreSharedDAO();
//
//		String fenXiangZhe = s.getCity().getId(); // 分享者
//
//		DAO.delete(fenXiangZhe, city.getId());
//	}
//}
