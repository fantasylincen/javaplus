//package cn.mxz.activity.boss;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import cn.javaplus.common.db.DAO;
//import cn.javaplus.common.db.DAOBase;
//import cn.mxz.BossEventMapTemplet;
//import cn.mxz.BossEventMapTempletConfig;
//import cn.mxz.MapTemplet;
//import cn.mxz.battle.BattleExpPrize;
//import cn.mxz.mission.AbstractMission;
//import cn.mxz.mission.MissionMap;
//import cn.mxz.mission.MissionPrize;
//import cn.mxz.mission.Person;
//import cn.mxz.mission.PropPrize;
//import cn.mxz.mission.RandomBossListener;
//import cn.mxz.mission.map.JsonMap;
//import cn.mxz.user.City;
//import cn.mxz.user.mission.MissionMark;
//import db.dao.factory.DaoFactory;
//import db.domain.BossMission;
//import db.domain.BossMissionMap;
//
//public class BossMissionImpl extends AbstractMission<BossMission, BossMissionMap> implements cn.mxz.activity.boss.BossMission {
//
//	private BossReward	bossReward;
//
//	public BossMissionImpl(City city) {
//		super(city);
//	}
//
//	@Override
//	public MissionMark getMark() {
//
//		MissionMark missionMark = new MissionMark() {
//
//			@Override
//			public int getMapId() {
//
//				int i = dto.getMissionId() + 1;
//
//				return i;
//			}
//
//			@Override
//			public int getMissionId() {
//
//				BossEventMapTemplet m = BossEventMapTempletConfig.get(getMapId());
//
//				int chapterId = m.getChapterId();
//
//				return chapterId;
//			}
//
//			@Override
//			public int getCopyId() {
//
//				BossEventMapTemplet m = BossEventMapTempletConfig.get(getMapId());
//
//				int sceneId = m.getSceneId();
//
//				return sceneId;
//			}
//
//			@Override
//			public String toString() {
//				return getMissionId() + "-" + getCopyId() + "-" + getMapId();
//			}
//
//		};
//
//		return missionMark;
//	}
//
//	@Override
//	public void onEnd(MissionMark markBeforePersonMove, MissionMap map) {
//	}
//
//	@Override
//	protected DAOBase<BossMission> getMissionDAO() {
//		return DaoFactory.getBossMissionDAO();
//	}
//
//	@Override
//	protected DAO<String, BossMissionMap> getMissionMapDAO() {
//		return DaoFactory.getBossMissionMapDAO();
//	}
//
//	@Override
//	public List<Boss> getBossList() {
//
//		BossActivity activity = BossActivityImpl.getInstance();
//
//		List<Boss> ls = new ArrayList<Boss>(activity.getBossAll());
//
//		filter(ls);
//
//		return ls;
//	}
//
//	/**
//	 * 过滤出所有我不是挑战者的Boss
//	 * 
//	 * @param ls
//	 */
//	private void filter(List<Boss> ls) {
//
//		Iterator<Boss> it = ls.iterator();
//
//		while (it.hasNext()) {
//
//			Boss next = it.next();
//
//			if (!isChallenter(next)) {
//
//				it.remove();
//			}
//		}
//	}
//
//	/**
//	 * 判断我是不是Boss的挑战者
//	 * 
//	 * @param boss
//	 * @param shower
//	 * @return
//	 */
//	private boolean isChallenter(Boss boss) {
//
//		List<BossChallenger> bossChallengers = boss.getBossChallengers();
//
//		for (BossChallenger bc : bossChallengers) {
//
//			if (city.getId().equals(bc.getId())) {
//
//				return true;
//			}
//		}
//
//		return false;
//	}
//
//	@Override
//	public Boss getBoss(int bossId) {
//
//		List<Boss> bossList = getBossList();
//
//		for (Boss boss : bossList) {
//
//			if (boss.getId() == bossId) {
//
//				return boss;
//			}
//		}
//
//		return null;
//	}
//
//	@Override
//	public BossReward getLastBossReward() {
//		return bossReward;
//	}
//
//	@Override
//	public FriendSharedPoints getFriendPoints() {
//
//		return new FriendSharedPointsImpl(city);
//	}
//
//	@Override
//	public List<Boss> getBossFoundByMe() {
//
//		List<Boss> bossList = getBossList();
//
//		List<Boss> list = new ArrayList<Boss>();
//
//		for (Boss boss : bossList) {
//
//			if (boss.getFinder().equals(city)) {
//
//				list.add(boss);
//			}
//		}
//
//		return list;
//	}
//	
//	@Override
//	public void saveBossReward(BossReward reward) {
//		bossReward = reward;
//	}
//
//	@Override
//	protected MissionMap newMap(int storyId) {
//
//		MapTemplet temp = BossEventMapTempletConfig.get(storyId);
//
//		return new JsonMap(storyId, city, temp);
//	}
//
//	@Override
//	public void buildMap(int storyId) {
//
//		super.buildMap(storyId);
//
//		Person person = getCurrentMap().getPerson();
//
//		person.addListener(new RandomBossListener());
//	}
//
//	private BossMissionPrize	prize;
//
//	@Override
//	public MissionPrize pickPrize() {
//
//		BossMissionPrize p = this.prize;
//		this.prize = null;
//		return p;
//
//	}
//
//	@Override
//	public void save(List<BattleExpPrize> fighterPrize, List<PropPrize> propPrize, int id) {
//		save(fighterPrize);
//	}
//
//	@Override
//	public void save(List<BattleExpPrize> fighterPrize) {
//		this.prize = new BossMissionPrize(fighterPrize);
//	}
//
//
//}
