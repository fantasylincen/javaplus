package cn.mxz.prompt;

import java.util.Collection;
import java.util.List;

import mongo.gen.MongoGen.AchieveTaskDto;
import mongo.gen.MongoGen.DailyTaskDto;
import mongo.gen.MongoGen.PvpWarSituationDto;
import cn.mxz.ActivityTemplet;
import cn.mxz.ActivityTempletConfig;
import cn.mxz.ConsumableTemplet;
import cn.mxz.ConsumableTempletConfig;
import cn.mxz.DogzTemplet;
import cn.mxz.DogzTempletConfig;
import cn.mxz.StuffTemplet;
import cn.mxz.StuffTempletConfig;
import cn.mxz.activity.ActivityIds;
import cn.mxz.bag.Bag;
import cn.mxz.bag.Grid;
import cn.mxz.base.task.Task;
import cn.mxz.city.City;
import cn.mxz.city.FunctionOpenManager;
import cn.mxz.city.FunctionOpenManager.ModuleType;
import cn.mxz.dogz.DogzUIImpl;
import cn.mxz.equipment.SnatchLog;
import cn.mxz.equipment.snatch.SnatchManager;
import cn.mxz.newpvp.DanReward;
import cn.mxz.newpvp.PvpManager;
import cn.mxz.newpvp.PvpPlayer;
import cn.mxz.openserver.OpenServerReward;
import cn.mxz.openserver.OpenServerRewardManager;
import cn.mxz.qiyu.QiYuButton;
import cn.mxz.qiyu.QiYuButtons;
import cn.mxz.shenmo.UserShenmo;
import cn.mxz.task.BoxReward;
import cn.mxz.task.TaskBox;
import cn.mxz.task.achieve.AchieveTaskManager;
import cn.mxz.task.dailytask.DailyTaskManager;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.util.needs.NeedsChecker;
import cn.mxz.util.needs.NeedsFactory;
import cn.mxz.xianshi.Recruitor;
import cn.mxz.xianshi.RecruitorFactory;

public class PromptManagerImpl implements PromptManager {

	private City city;

	private boolean[] messageMark = new boolean[100];

	public PromptManagerImpl(City city) {
		this.city = city;
	}

	// private boolean hasMessage(PromptType vv) {
	//
	// if (vv == PromptType.DUI_WU) {
	// return dogzCanLevelUp();
	// }
	//
	// int n = vv.toNum();
	// return prompt.getV(n);
	// }

	// 礼包可领取
	// List<Present> presents = city.getPresentManager().getAll();
	// for (Present present : presents) {
	// if (present.getRemainBuyTime() > 0) {
	// return true;
	// }
	// }
	// return false;

	@Override
	public boolean getHpksx() {
		// city.getS
		return false;
	}

	@Override
	public boolean getHpkzm() {

		Recruitor r1 = RecruitorFactory.create(1, city, true);
		Recruitor r2 = RecruitorFactory.create(2, city, true);
		Recruitor r3 = RecruitorFactory.create(3, city, true);

		int freeTimes1 = r1.getFreeTimes();
		int freeTimes2 = r2.getFreeTimes();
		int freeTimes3 = r3.getFreeTimes();

		return freeTimes1 + freeTimes2 + freeTimes3 > 0;
	}

	@Override
	public boolean getSsksj() {
		// DogzManager dm = city.getDogzManager();
		// Map<Integer, Dogz> all = dm.getDogzAll();
		// for (Dogz d : all.values()) {
		// if (dm.canLevelUp(d.getTypeId())) {
		// return true;
		// }
		// }
		
		FunctionOpenManager fm = city.getFunctionOpenManager();
		boolean open = fm.isOpen(ModuleType.ShenShouMoKuai);
		if(!open) {
			return false;
		}

		List<DogzTemplet> all = DogzTempletConfig.getAll();
		for (DogzTemplet dt : all) {
			DogzUIImpl dd = new DogzUIImpl(city, dt);
			if(dd.getStatus() == 2) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean getYsyktzcs() {

		// if (!city.getFunctionOpenManager().isOpen(ModuleType.DouFaMoKuai)) {
		// return false;
		// }
		//
		// PvpManager om = city.getNewPvpManager();
		// PvpPlayer p = om.getPlayer();
		// int remainTimes = p.getRemainTimes();
		// return remainTimes > 0;
		return false;
	}

	@Override
	public boolean getJjjlwlq() {

		if (!city.getFunctionOpenManager().isOpen(ModuleType.DouFaMoKuai)) {
			return false;
		}
		
		

		PvpManager om = city.getNewPvpManager();
		
		DanReward reward = om.getDanReward();
		if (reward.canReiceive()) {
			return true;
		}
		PvpPlayer p = om.getPlayer();
		
		return !p.canNotReceiveRankReward();
	}

	@Override
	public boolean getKdb() {
		List<StuffTemplet> all = StuffTempletConfig.findByType(7);
		Bag<Grid> pb = city.getPiecesBag();
		for (StuffTemplet s : all) {
			int count = pb.getCount(s.getType());
			if (count > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean getYmsktz() {
		if (!isOpen(ActivityIds.MoShenRuQin_1)) {
			return false;
		}
		UserShenmo sm = city.getUserShenmo();
		return sm.hasPrizeOrShenmo();
	}

	@Override
	public boolean getYmsjlklq() {
		if (!isOpen(ActivityIds.MoShenRuQin_1)) {
			return false;
		}
		UserShenmo sm = city.getUserShenmo();
		return sm.hasPrizeOrShenmo();
	}

	private boolean isOpen(int bid) {
		ActivityTemplet t = ActivityTempletConfig.get(bid);
		int lv = t.getLevel();
		return city.getLevel() >= lv;
	}

	@Override
	public boolean getYdbxxx() {

//		UserCounter his = city.getUserCounterHistory();
//		boolean has = his.isMark(CounterKey.HAS_NEW_SNATCH_MESSAGE);
//		if (!has) {
//			return false;
//		}

		SnatchManager sn = city.getSnatchManager();
		List<SnatchLog> all = sn.getLogs();
		for (SnatchLog s : all) {
			if(!s.isQuilt() && !s.isSaw()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean getYdzxxx() {

		UserCounter his = city.getUserCounterHistory();
		boolean has = his.isMark(CounterKey.HAS_NEW_PVP_MESSAGE);
		if (!has) {
			return false;
		}

		PvpManager pm = city.getNewPvpManager();

		List<PvpWarSituationDto> ws = pm.getWarSituationsBeHit();

		for (PvpWarSituationDto s : ws) {
			if(!s.getIsSaw()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean getYxtxxx() {

		int count = city.getFriendManager().getRequestCount();

//		Debuger.debug("PromptManagerImpl.getYxtxxx()   好友请求数:" + count);
		
		return count > 0;
	}

	@Override
	public boolean getYhyly() {
		return getSlyxxx();
	}

	@Override
	public boolean getSjyxxx() {
		boolean b = messageMark[SJYXXX];
//		Debuger.debug("PromptManagerImpl.getSjyxxx()" + b);
		return b;
	}

	@Override
	public boolean getLmyxxx() {
		return messageMark[LMYXXX];
	}

	@Override
	public boolean getSlyxxx() {
		return city.getUserChater().hasNewMessage();
//		UserCounter c = city.getUserCounterHistory();
//		boolean mark = c.isMark(CounterKey.RECEIVE_MESSAGE);
//		return messageMark[SLYXXX] || mark;
	}

	@Override
	public boolean getMbklq() {
		DailyTaskManager d = city.getDailyTaskManager();
		Collection<Task<DailyTaskDto>> ls = d.getAllUnGiveBack();
		for (Task<DailyTaskDto> task : ls) {
			if (task.isFinishAll()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean getCjklq() {
		AchieveTaskManager a = city.getAchieveTaskManager();
		Collection<Task<AchieveTaskDto>> ls = a.getAllUnGiveBack();
		for (Task<AchieveTaskDto> task : ls) {
			if (task.isFinishAll()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean getKzm() {
		Recruitor r1 = RecruitorFactory.create(1, city, true);
		Recruitor r2 = RecruitorFactory.create(2, city, true);
		Recruitor r3 = RecruitorFactory.create(3, city, true);

		int freeTimes1 = r1.getFreeTimes();
		int freeTimes2 = r2.getFreeTimes();
		int freeTimes3 = r3.getFreeTimes();

		int s1 = r1.getRemainingSec();
		int s2 = r2.getRemainingSec();
		int s3 = r3.getRemainingSec();

		if (s1 > 0 && s2 > 0 && s3 > 0) {
			return false;
		}

		int i = freeTimes1 + freeTimes2 + freeTimes3;
		return i > 0;
	}

	@Override
	public boolean getZmhd() {
		return false;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(" [city=");
		b.append(city);
		b.append(", getHpksx()=");
		b.append(getHpksx() + "\r");
		b.append(", getHpkzm()=");
		b.append(getHpkzm() + "\r");
		b.append(", getSsksj()=");
		b.append(getSsksj() + "\r");
		b.append(", getYsyktzcs()=");
		b.append(getYsyktzcs() + "\r");
		b.append(", getJjjlwlq()=");
		b.append(getJjjlwlq() + "\r");
		b.append(", getKdb()=");
		b.append(getKdb() + "\r");
		b.append(", getYmsktz()=");
		b.append(getYmsktz() + "\r");
		b.append(", getYmsjlklq()=");
		b.append(getYmsjlklq() + "\r");
		b.append(", getYdbxxx()=");
		b.append(getYdbxxx() + "\r");
		b.append(", getYdzxxx()=");
		b.append(getYdzxxx() + "\r");
		b.append(", getYxtxxx()=");
		b.append(getYxtxxx() + "\r");
		b.append(", getYhyly()=");
		b.append(getYhyly() + "\r");
		b.append(", getSjyxxx()=");
		b.append(getSjyxxx() + "\r");
		b.append(", getLmyxxx()=");
		b.append(getLmyxxx() + "\r");
		b.append(", getSlyxxx()=");
		b.append(getSlyxxx() + "\r");
		b.append(", getMbklq()=");
		b.append(getMbklq() + "\r");
		b.append(", getCjklq()=");
		b.append(getCjklq() + "\r");
		b.append(", getKzm()=");
		b.append(getKzm() + "\r");
		b.append(", getZmhd()=");
		b.append(getZmhd());
		b.append("]");
		return b.toString();
	}

	@Override
	public void removeMessageMark(int type) {
		messageMark[type] = false;
	}

	@Override
	public void markMessage(int type) {
		messageMark[type] = true;
	}

	@Override
	public boolean getBbybx() {
		Bag<Grid> bag = city.getBag();
		List<ConsumableTemplet> all = ConsumableTempletConfig.getAll();
		for (ConsumableTemplet ct : all) {
			int canOpen = ct.getCanOpen();
			if (canOpen != 1) {
				continue;
			}

			if (bag.getCount(ct.getId()) <= 0) {
				continue;
			}

			NeedsChecker c = NeedsFactory.getNeedsCheckerImpl2(ct.getNeeds());

			try {
				c.check(city.getPlayer());
			} catch (Exception e) {
				continue;
			}

			return true;
		}
		return false;
	}

	@Override
	public boolean getYxhb() {
		return messageMark[YXHB];
	}

	/**
	 * @param type
	 *            PromptManager 内部的常量
	 * @return
	 */
	public boolean getMessageMark(int type) {
		return messageMark[type];
	}

	@Override
	public boolean getQiYuButtons() {
		QiYuButtons bs = city.getQiyuManager().getButtons();
		List<QiYuButton> bss = bs.getButtons();
		for (QiYuButton b : bss) {
			if (b.isOpen() && b.getHasTips()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean getKflb() {

		OpenServerRewardManager o = city.getOpenServerRewardManager();
		List<OpenServerReward> all = o.getRewards();
		for (OpenServerReward oo : all) {
			boolean hasReceive = oo.getHasReceive();
			if (!hasReceive && oo.getCanReceive()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean getKflbOpen() {
		// 只要有任何一个礼包没有领取过 ,就显示
		OpenServerRewardManager o = city.getOpenServerRewardManager();
		List<OpenServerReward> all = o.getRewards();
		for (OpenServerReward oo : all) {
			boolean hasReceive = oo.getHasReceive();
			if (!hasReceive) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean getKfyxxx() {
		UserCounter c = city.getUserCounterHistory();
		boolean mark = c.isMark(CounterKey.RECEIVE_KE_FU_MESSAGE);
		return mark;
	}

	@Override
	public boolean getMmbxklq() {
		BoxReward bx = city.getDailyTaskManager().getDailyTaskList().getBoxReward();
		if(bx == null) {
			return false;
		}
		List<TaskBox> boxes = bx.getBoxes();
		for (TaskBox bxx : boxes) {
			if(!bxx.isOpen() && bxx.canReceive() ) {
				return true;
			}
		}
		return false;
	}
}
