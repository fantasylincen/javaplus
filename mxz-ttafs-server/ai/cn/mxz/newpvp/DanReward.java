package cn.mxz.newpvp;

import message.S;
import cn.mxz.DanRewardTemplet;
import cn.mxz.DanRewardTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.prize.PrizeSender;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

/**
 * 段位奖励
 * @author 林岑
 *
 */
public class DanReward /*implements*/ /*IDanReward*/ {

//	private PvpManager	manager;
//
//	public DanReward(PvpManager manager) {
//		this.manager = manager;
//	}
//
//	public boolean canReiceive() {
//		int id = manager.getPlayer().getDan();
//		UserCounter counter = getCounter();
//		boolean mark = counter.isMark(CounterKey.PVP_HAS_RECEIVE_DAN_REWARD, id);
//		
//		return !mark;
//	}
//
//	private UserCounter getCounter() {
//		return manager.getCity().getUserCounterHistory();
//	}
//
//	public void receive() {
//		String reward = getReward();
//		if (!canReiceive()) {
//			throw new OperationFaildException(S.S10252);
//		}
//		PrizeSender sender = PrizeSenderFactory.getPrizeSender();
//		sender.send(manager.getCity().getPlayer(), reward);
//		markReceive();
//	}
//
//	private void markReceive() {
//		DanRewardTemplet templet = getTemplet();
//		int dan = templet.getDan();
//		getCounter().mark(CounterKey.PVP_HAS_RECEIVE_DAN_REWARD, dan);
//	}
//
//	public String getReward() {
//		DanRewardTemplet temp = getTemplet();
//		return temp.getAwards();
//	}
//	
//	public int getId() {
//		DanRewardTemplet temp = getTemplet();
//		return temp.getDan();
//	}
//
//	private DanRewardTemplet getTemplet() {
//		int id = manager.getPlayer().getDan();
//		DanRewardTemplet temp = DanRewardTempletConfig.get(id);
//		return temp;
//	}
	
	
	
	
	
	
	
	
	
	
	public class NextBean {

		private DanRewardTemplet temp;

		public NextBean(DanRewardTemplet temp) {
			this.temp = temp;
		}

		public DanRewardTemplet getTemp() {
			return temp;
		}

		public int getId() {
			return temp.getDan();
		}

		public String getReward() {
			return temp.getAwards();
		}
	}

	private PvpManager manager;

	public DanReward(PvpManager manager) {
		this.manager = manager;
	}

	private UserCounter getCounter() {
		return manager.getCity().getUserCounterHistory();
	}

	public void receive() {
		String reward = getReward();
		if (!canReiceive()) {
			throw new OperationFaildException(S.S10252);
		}
		PrizeSender sender = PrizeSenderFactory.getPrizeSender();
		sender.send(manager.getCity().getPlayer(), reward);
		
		getCounter().mark(CounterKey.PVP_HAS_RECEIVE_DAN_REWARD, getId());
	}

	public boolean hasReceive(int id) {
		return getCounter().isMark(CounterKey.PVP_HAS_RECEIVE_DAN_REWARD, id);
	}

	public String getReward() {
		NextBean nextBean = getNextBean();
		if(nextBean == null  ) {
			return "";
		}
		return nextBean.getReward();
	}

	public boolean canReiceive() {
		NextBean nextBean = getNextBean();
		if(nextBean == null  ) {
			return false;
		}
		int dan = manager.getPlayer().getDan();
		int id = nextBean.getId();
		boolean reachDan = dan >= id;
		boolean noReceive = !hasReceive(id);
		return noReceive && reachDan;
	}

	public int getId() {
		NextBean nextBean = getNextBean();
		if(nextBean == null  ) {
			return -1;
		}
		int id = nextBean.getId();
		return id;
	}

	private NextBean getNextBean() {

		int id = 1;
		while (true) {
			DanRewardTemplet temp = DanRewardTempletConfig.get(id);
			if (temp == null) {
				return null;
			}
			String a = temp.getAwards();
			if (!a.trim().isEmpty() && !hasReceive(id)) {
				return new NextBean(temp);
			}
			id++;
		}
	}

}
