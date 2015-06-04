package cn.mxz.tower;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.TowerService;

/**
 *
 * 爬塔服务
 *
 * @author 林岑
 * @since	2013年9月3日 16:13:50
 *
 */

public class TowerServiceImpl extends AbstractService implements TowerService {


//	@Override
//	protected MissionMap getMap() {
//
//		return getCity().getTowerMission().getCurrentMap();
//	}
//
//	@Override
//	protected DAO<String, TowerMissionMap> getMissionMapDAO() {
//
//		return DaoFactory.getTowerMissionMapDAO();
//	}
//
//	@Override
//	public TowerPro getData() {
//
//		return new TowerBuilder().build(getCity());
//	}
//
//	@Override
//	public FightingRewardPro getFightingReward() {
//
//		return new FightingRewardBuilder().build(getCity().getTowerMission().getTowerFightingReward());
//	}
//
//	@Override
//	protected Mission getCurrentMission() {
//
//		return getCity().getTowerMission();
//	}
//
//	@Override
//	protected Captures getCaptures() {
//
//		return MissionFactory.getCaptures(getId());
//	}
//
//	@Override
//	public GodsPro useGanLu() {
//
//		GodsPro g = super.useGanLu();
//
//		saveCatchScore(g);
//
//		return g;
//	}
//
//	private void saveCatchScore(GodsPro g) {
//		for (GodPro gg : g.getGodsList()) {
//
//			int type = gg.getType();
//
//			TowerFightingReward r = getCity().getTowerMission().getTowerFightingReward();
//
//			r.addCatchScore(calcCatchScore(type));
//		}
//	}
//
//	@Override
//	public final GodsPro useXianLu() {
//
//		GodsPro g = super.useXianLu();
//
//		saveCatchScore(g);
//
//		return g;
//	}
//
//	@Override
//	public void moveTo(int path, int index) {
//
//		super.move(path, index);
//
//		randomBug();
//	}
//
//	/**
//	 * 随机生成裂缝
//	 */
//	private void randomBug() {
//
//		if(getCity().getTowerMission().getBug() != null) {
//
//			return;
//		}
//
//		if(Util.isHappen(0.5f)) {
//
//			generateBug();
//		}
//
//	}
//
//	private void generateBug() {
//
//		getCity().getTowerMission().setBug(new TowerBugImpl(getCity()));
//	}
//
//	private int calcCatchScore(int type) {
//
//		//TODO LC:爬塔活动捕获神将积分
//
//		return 100;
//	}
//
//	@Override
//	protected BattlePrizeAble buildBattle(MapDemon d, PlayerCamp selected, int mapId) {
//
//		Camp<? extends Fighter> camp = d.getCamp();;
//
//		if(getCity().getTowerMission().getBug() != null) {
//
//			camp = new TowerDemonCamp(camp);
//		}
//
//		TowerBattleImpl b = new TowerBattleImpl(selected, mapId, camp);
//
//		b.addListener(new TowerWinListener());
//
//		b.addListener(new UpdateFighterHp());
//
//		return b;
//	}
}
