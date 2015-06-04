package cn.mxz.base.servertask;

import java.util.List;

import cn.javaplus.time.taskutil.ITaskCenter;
import cn.javaplus.time.taskutil.TaskCenterDispatcher;
import cn.mxz.bossbattle.BossBattleActivity;
import cn.mxz.czfk.FeedBackClearTask;
import cn.mxz.czfk.FeedBackRule;
import cn.mxz.events.EventDispatcher2Impl;
import cn.mxz.events.RateSystemEvent;
import cn.mxz.nvwa.AddBuy;
import cn.mxz.nvwa.NvwaAddBuyTask;
import cn.mxz.nvwa.NvwaRule;
import cn.mxz.task.ManXiangTaskCenter;
import cn.mxz.task.TaskCenterType;
import cn.mxz.user.PhysicalTask;
import cn.mxz.util.debuger.SystemLog;
import define.D;

public class ServerTaskImpl extends EventDispatcher2Impl implements ServerTask {

	private static ServerTaskImpl instance;

	public static final ServerTask getInstance() {
		if (instance == null) {
			instance = new ServerTaskImpl();
		}
		return instance;
	}

	private final TaskCenterDispatcher center;

	private static final String ZERO_CLOCK = "00:00";

	/**
	 * 每日定时定点任务
	 */
	private final ITaskCenter timed;

	/**
	 * 周期性任务
	 */
	private final ITaskCenter normal;

	/**
	 * 周期性任务
	 */
	private final ITaskCenter normal2;
	/**
	 * 周期性任务
	 */
	private final ITaskCenter normal3;
	/**
	 * 周期性任务4
	 */
	private final ITaskCenter normal4;

	private ITaskCenter bossTimer;

	private ITaskCenter heishiTimer;

	private MyTimer timer = new MyTimer();
	
	private ServerTaskImpl() {

		center = ManXiangTaskCenter.getInstance();

		timed = center.createInstanceById(TaskCenterType.TIMED_TASK_TASK, true);

		normal = center.createInstanceById(TaskCenterType.NORMAL_TASK_A, true);

		normal2 = center.createInstanceById(TaskCenterType.NORMAL_TASK_B, true);
		normal3 = center.createInstanceById(TaskCenterType.NORMAL_TASK_C, true);
		normal4 = center.createInstanceById(TaskCenterType.NORMAL_TASK_D, true);
		bossTimer = center.createInstanceById(TaskCenterType.BOSS_TIMER, true);
		// heishiTimer = center.createInstanceById(TaskCenterType.HEISHI_TIMER,
		// true);

	}

	@Override
	public void start() {

		timed.loopEveryDay(ZERO_CLOCK, new UserCountersZeroTask()); // 用户计数器

		timed.loopEveryDay(ZERO_CLOCK, new ZeroClockEventDispathcer(this));

		timed.loopEveryDay(ZERO_CLOCK, new DailyTaskClear()); // 日常任务任务清空

		normal.loop(PhysicalTask.RATE, PhysicalTask.RATE,
				PhysicalTask.getInstance()); // 体力增长任务

		normal.loop(PowerTask.RATE, PowerTask.RATE, PowerTask.getInstance()); // 精力增长任务

		normal.loop(ReduceJuHunTask.RATE, ReduceJuHunTask.RATE,
				ReduceJuHunTask.getInstance()); // 精力增长任务

		normal3.loop(30000, 0, new OnlineSizeUpdate());// 在线人数更新

		normal.loop(1 * 60000, 0, new EmptyPacketToAll());

		normal.loop(RateSystemEvent.RATE, 0, new DispatchSystemEvent());

		normal2.loopEveryDay(D.PVP_RANK_REWARD_SURE_TIME,
				new SavePvpRankReward());

		normal2.loopEveryDay(D.CHUANG_ZHEN_REFRESH_TIME,
				new ChuangZhenZeroTask());

		loopNvwa();

		loopZhaoXianBangRefresh();
		loopZbsrRefresh();

		SystemLog.debug("aaaaaaaaaaaaa" + BossBattleActivity.INSTANCE.start2);
		bossTimer.loopEveryDay(BossBattleActivity.INSTANCE.start1,
				new BossBattleTask(1));
		bossTimer.loopEveryDay(BossBattleActivity.INSTANCE.end1,
				new BossBattleTask(2));

		bossTimer.loopEveryDay(BossBattleActivity.INSTANCE.start2,
				new BossBattleTask(3));
		bossTimer.loopEveryDay(BossBattleActivity.INSTANCE.end2,
				new BossBattleTask(4));

		bossTimer.loopEveryDay(D.GEN_BOSS, new BossBattleTask(5));

		timer.schedule(new NvwaClearTask(), NvwaRule.getEndDate());

		timer.schedule(new FeedBackClearTask(), FeedBackRule.getEndDate());
		
		new PropertiesReloader().start();
	}

	private void loopNvwa() {
		List<AddBuy> addBuys = NvwaRule.getAddBuys();
		for (AddBuy addBuy : addBuys) {
			String time = addBuy.getTime();
			normal2.loopEveryDay(time, new NvwaAddBuyTask(addBuy));
		}
	}

	private void loopZhaoXianBangRefresh() {
		String[] split = D.ZHAO_XIAN_BANG_REFRESH_TIME.split(" ");
		for (String t : split) {
			if (!t.isEmpty()) {
				normal2.loopEveryDay(t, new RefreshHeiShi());
			}
		}
	}

	private void loopZbsrRefresh() {
		String[] split = D.ZBSR_REFRESH_TIME.split(" ");
		for (String t : split) {
			if (!t.isEmpty()) {
				normal2.loopEveryDay(t, new RefreshZbsr());
			}
		}
	}
}
