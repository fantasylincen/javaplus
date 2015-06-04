//package cn.mxz.activity.boss;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import message.S;
//import cn.javaplus.common.db.DAO;
//import cn.javaplus.common.util.time.Time;
//import cn.mxz.activity.ActivityManager;
//import cn.mxz.activity.tower.AbstractActivity;
//import cn.mxz.base.exception.OperationFaildException;
//import cn.mxz.city.PlayerProperty;
//import cn.mxz.event.ServerEvent;
//import cn.mxz.rankinglist.RankingListImpl;
//
//import com.google.common.collect.Lists;
//
//import db.dao.factory.CacheDaoFactory;
//import db.domain.BossData;
//import define.D;
//
///**
// * Boss活动
// *
// * @author 林岑
// *
// */
//public class BossActivityImpl extends AbstractActivity implements BossActivity {
//
//	public class StopTask extends TimerTask {
//
//		@Override
//		public void run() {
//
//			stop();
//		}
//
//	}
//
//	public class StartTask extends TimerTask {
//
//		@Override
//		public void run() {
//
//			start();
//		}
//
//	}
//
//	private static BossActivityImpl		instance;
//
//	private Map<String, BossChallenger>	all	= new HashMap<String, BossChallenger>();
//
//	private Timer						timer;
//
//	private BossActivityImpl() {
//
//		addListener(new ShowMessageOnStartListener());
//
//		addListener(new ShowMessageOnStopListener());
//
//		addListener(new ShowMessageOnWaitStartListener());
//
//		addListener(new ShowMessageOnWaitStopListener());
//	}
//
//	public static final BossActivity getInstance() {
//
//		if (instance == null) {
//
//			instance = new BossActivityImpl();
//
//			ActivityManager.getInstance().regist(instance);
//		}
//
//		return instance;
//	}
//
//	@Override
//	public Collection<BossChallenger> getAll() {
//
//		Collection<BossChallenger> all = Lists.newArrayList(this.all.values());
//
//		all.addAll(getUserAll());
//
//		return all;
//	}
//
//	private List<BossChallenger> getUserAll() {
//
//		List<BossChallenger> list = new ArrayList<BossChallenger>();
//
//		DAO<Integer, BossData> bossDataDAO = DaoFactory.getCacheBossDataDAO();
//
//		List<BossData> all = bossDataDAO.getAll();
//
//		Set<String> cs = new HashSet<String>();
//
//		for (BossData bossData : all) {
//
//			cs.add(bossData.getUname());
//
//			cs.addAll(getAllHelper(bossData));
//		}
//
//		cs.remove("");
//
//		for (String userId : cs) {
//
//			list.add(new BossChallengerImpl(userId));
//		}
//
//		return list;
//	}
//
//	private Collection<String> getAllHelper(BossData bossData) {
//
//		List<String> list = new ArrayList<String>();
//
//		for (int i = 0; i < BossData.HELPER_ID_LEN; i++) {
//
//			list.add(bossData.getHelperId(i));
//		}
//
//		return list;
//	}
//
//	@Override
//	public Collection<Boss> getBossAll() {
//
//		List<Boss> list = new ArrayList<Boss>();
//
//		DAO<Integer, BossData> DAO = DaoFactory.getCacheBossDataDAO();
//
//		List<BossData> all = DAO.getAll();
//
//		for (BossData data : all) {
//
//			if(timeOut(data)) {
//
//				DAO.delete(data.getBossId());
//
//				continue;
//			}
//
//			list.add(new BossImpl(data));
//		}
//
//		return list;
//	}
//
//	/**
//	 * 是否过期
//	 * @param data
//	 * @return
//	 */
//	private boolean timeOut(BossData data) {
//		long time = data.getFoundTime();
//		time *= 1000;
//		return System.currentTimeMillis() - time >= D.BOSS_TIME_OUT ;
//	}
//
//	@Override
//	public int getRank(String uname) {
//
//		RankingListImpl rankingList = RankingListImpl.getInstance();
//
//		return rankingList.getRank(uname, PlayerProperty.ACTIVITY_SCORE);
//	}
//
//	@Override
//	public void checkStart() {
//
//		if (!isStart()) {
//
//			throw new OperationFaildException(S.S10098);
//		}
//	}
//
//	@Override
//	public void add(BossChallenger user) {
//
//		this.all.put(user.getId(), user);
//	}
//
//	@Override
//	protected ServerEvent buildActivityStartEvent() {
//
//		return new BossActivityStartEvent();
//	}
//
//	@Override
//	public String getActivityName() {
//
//		return "BOSS_ACTIVITY";
//	}
//
//	@Override
//	protected ServerEvent buildActivityStopEvent() {
//
//		return new BossActivityStopEvent();
//	}
//
//	@Override
//	public void stop() {
//		super.stop();
//
//		ensureTimerDispose();
//	}
//
//	private void ensureTimerDispose() {
//
//		if (timer != null) {
//			timer.cancel();
//			timer = null;
//		}
//
//	}
//
//	@Override
//	public void startDelay() {
//
//		ensureTimerDispose();
//
//		timer = new Timer();
//
//		timer.schedule(new StartTask(), 15 * Time.MILES_ONE_MIN);
//
//		dispatchEvent(new StartDelayEvent());
//	}
//
//	@Override
//	public void stopDelay() {
//
//		ensureTimerDispose();
//
//		timer = new Timer();
//
//		timer.schedule(new StopTask(), Time.MILES_ONE_HOUR);
//
//		dispatchEvent(new StopDelayEvent());
//	}
//}
