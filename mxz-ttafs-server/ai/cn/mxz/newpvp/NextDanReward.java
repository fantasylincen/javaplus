//package cn.mxz.newpvp;
//
//import message.S;
//import cn.mxz.DanRewardTemplet;
//import cn.mxz.DanRewardTempletConfig;
//import cn.mxz.base.exception.OperationFaildException;
//import cn.mxz.base.prize.PrizeSender;
//import cn.mxz.base.prize.PrizeSenderFactory;
//import cn.mxz.util.counter.CounterKey;
//import cn.mxz.util.counter.UserCounter;
//
//public class NextDanReward /*implements*/ /*IDanReward*/ {
//
//	public class NextBean {
//
//		private DanRewardTemplet temp;
//
//		public NextBean(DanRewardTemplet temp) {
//			this.temp = temp;
//		}
//
//		public DanRewardTemplet getTemp() {
//			return temp;
//		}
//
//		public int getId() {
//			return temp.getDanId();
//		}
//
//		public String getReward() {
//			return temp.getAwards();
//		}
//	}
//
//	private PvpManager manager;
//
//	public NextDanReward(PvpManager manager) {
//		this.manager = manager;
//	}
//
//	/**
//	 * 当前段位的奖励
//	 * 
//	 * @return
//	 */
//	private String getCurrent() {
//		int id = manager.getPlayer().getDan();
//		DanRewardTemplet temp = DanRewardTempletConfig.get(id);
//		return temp.getAwards();
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
//	}
//
//	public boolean hasReiceiveCurrent() {
//		int id = manager.getPlayer().getDan();
//		return getCounter().isMark(CounterKey.PVP_HAS_RECEIVE_DAN_REWARD, id);
//	}
//
//	public boolean hasReceive(int id) {
//		return getCounter().isMark(CounterKey.PVP_HAS_RECEIVE_DAN_REWARD, id);
//	}
//
//	public String getReward() {
////		String current = getCurrent();
////		if (current.isEmpty() || hasReiceiveCurrent()) {
////			return getNext();
////		}
////		return current;
//		NextBean nextBean = getNextBean();
//		if(nextBean == null  ) {
//			return "";
//		}
//		return nextBean.getReward();
//	}
//
////	/*
////	 * 下一奖励
////	 */
////	private String getNext() {
////		NextBean nextBean = getNextBean();
////		if(nextBean == null  ) {
////			return "";
////		}
////		return nextBean.getReward();
////	}
//
//	public boolean canReiceive() {
//		NextBean nextBean = getNextBean();
//		if(nextBean == null  ) {
//			return false;
//		}
//		return !hasReceive(nextBean.getId());
////		String current = getCurrent();
////		if (current.isEmpty()) {
////			return false;
////		}
////		return !hasReiceiveCurrent();
//	}
//
//	public int getId() {
//		NextBean nextBean = getNextBean();
//		if(nextBean == null  ) {
//			return -1;
//		}
//		return nextBean.getId();
//	}
//
//	private NextBean getNextBean() {
//
//		int id = 1;
//		while (true) {
//			DanRewardTemplet temp = DanRewardTempletConfig.get(id);
//			if (temp == null) {
//				return null;
//			}
//			String a = temp.getAwards();
//			if (!a.trim().isEmpty() && !hasReceive(id)) {
//				return new NextBean(temp);
//			}
//			id++;
//		}
//	}
//
//}
