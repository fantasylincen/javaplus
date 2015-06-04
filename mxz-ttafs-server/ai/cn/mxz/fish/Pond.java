//package cn.mxz.fish;
//
//import java.util.Iterator;
//import java.util.List;
//
//import message.S;
//import cn.javaplus.common.util.Util.Random;
//import cn.mxz.AuthorityTemplet;
//import cn.mxz.AuthorityTempletConfig;
//import cn.mxz.EquipmentTempletConfig;
//import cn.mxz.FishProbabilityTemplet;
//import cn.mxz.FishProbabilityTempletConfig;
//import cn.mxz.FishTempletConfig;
//import cn.mxz.FishTypesTempletConfig;
//import cn.mxz.activity.fish.FishActivityImpl;
//import cn.mxz.base.exception.OperationFaildException;
//import cn.mxz.base.exception.SureIllegalOperationException;
//import cn.mxz.base.world.World;
//import cn.mxz.base.world.WorldFactory;
//import cn.mxz.city.PlayerProperty;
//import cn.mxz.user.City;
//import cn.mxz.util.counter.CounterKey;
//import cn.mxz.util.counter.UserCounter;
//
//import com.lemon.commons.user.IUser;
//
//import define.D;
//
///**
// *
// * 池塘
// *
// * @author 	林岑
// * @time	2013-5-15
// */
//class Pond {
//
//	/**
//	 * 钓鱼房间
//	 */
//	private FishersRoom fishers;
//
//	private static Pond instance;
//
//	private Pond() {
//
//		fishers = new FishersRoom();
//	}
//
//	public static Pond getInstance() {
//
//		if (instance == null) {
//
//			instance = new Pond();
//		}
//
//		return instance;
//	}
//
//	public FishersRoom getRoom() {
//
//		return fishers;
//	}
//
//	/**
//	 *
//	 * 放鱼饵，开始钓鱼.
//	 *
//	 * @param user
//	 * @param isAutoBuy
//	 */
//	public void start(City user, boolean isAutoBuy) {
//
//		final Fisher fisher = new Fisher(user);
//
//		removeProp(fisher, isAutoBuy);
//
//		fisher.startWait();
//
//		fishers.add(fisher);
//	}
//
//	private void removeProp(Fisher user, boolean isAutoBuy) {
//
//		final World world = WorldFactory.getWorld();
//
//		final City u = world.get(user.getId());
//
//		final int free = Pond.getInstance().getFreeTimes(u);
//
//		if (free <= 0) { // 如果免费次数用完了
//
//			u.getPlayer().reduce(PlayerProperty.CASH, D.FISH_CASH_NEED);
//
//		} else {
//
//			u.getUserCounter().add(CounterKey.FISH_TIMES, 1); // 增加一次今日钓鱼次数
//			u.getUserCounterHistory().add(CounterKey.FISH_TIMES, 1); // 增加一次今日钓鱼次数
//		}
//	}
//
//	/**
//	 *
//	 * 收杆，收获掉到的鱼.
//	 *
//	 * @param user
//	 * @return
//	 */
//	public int fetch(IUser user, int probability) {
//
//		final Fisher fisher = fishers.get(user.getId());
//
//		return fetchForce(fisher, probability);
//	}
//
//	/**
//	 * 今日免费钓鱼次数
//	 * @param user
//	 * @return
//	 */
//	public int getFreeTimes(City user) {
//
//		final UserCounter c = user.getUserCounter();
//
//
//		AuthorityTemplet temp = AuthorityTempletConfig.get(user.getLevel());
//
//
//
//		final int remain = temp.getFreeTime() - c.get(CounterKey.FISH_TIMES);	//剩余次数
//
//		return remain < 0 ? 0 : remain;
//	}
//
//
//	/**
//	 * 强制收杆
//	 *
//	 * @param user
//	 * @param probability
//	 * @return
//	 * @return
//	 */
//	private int fetchForce(Fisher user, int probability) {
//
//		user.fetch();
//
//		final int fishId = random(probability);
//
//		sendFish(user, fishId); // 发送鱼苗奖励
//
//		return fishId;
//	}
//
//
//	private int random(int probability) {
//
//		int type = randomType(probability);		//随机一个鱼的类型
//
//		int propTypeId = randomPropTypeId(type);//根据鱼的类型,随机鱼的ID
//
//		return propTypeId;
//
//	}
//
//	private void sendFish(Fisher user, Integer fishId) {
//
//		final World w = WorldFactory.getWorld();
//
//		final City city = w.get(user.getId());
//
//		if(FishTempletConfig.get(fishId) != null) {
//
//			city.getJunket().addProp(fishId, 1);
//
//		} else if(EquipmentTempletConfig.get(fishId) != null) {
//
//			city.getEquipmentManager().createNewEquipment(fishId);
//
//		} else {
//
//			city.getBagAuto().addProp(fishId, 1);
//		}
//	}
//
//
//	private int randomType(int probability) {
//
//		int[] arrayByWeight;
//
//		switch (probability) {
//
//		case 0:
//
//			arrayByWeight = FishTypesTempletConfig.getArrayByWeight();
//
//			break;
//
//		case 1:
//
//			arrayByWeight = FishTypesTempletConfig.getArrayByWeight1();
//
//			break;
//
//		case 2:
//
//			arrayByWeight = FishTypesTempletConfig.getArrayByWeight2();
//
//			break;
//
//		default:
//
//			throw new SureIllegalOperationException("-probability = " + probability);
//		}
//
//		int index = Random.getRandomIndex(arrayByWeight);
//
//		return FishTypesTempletConfig.getKeys().get(index);
//	}
//
//	/**
//	 * 如果什么也没有发生, 抛出异常
//	 * @param type
//	 * @return
//	 */
//	private int randomPropTypeId(int type) {
//
//		List<FishProbabilityTemplet> all = FishProbabilityTempletConfig.findByFishType(type);
//
//		if(!FishActivityImpl.getInstance().isStart()) {
//
//			removeAllEquipment(all);
//		}
//
//		if(all.isEmpty()) {
//
//			throw new OperationFaildException(S.S10091);
//		}
//
//		int index = Random.getRandomIndex(buildProbabilities(all));
//
//		return all.get(index).getId();
//	}
//
//	/**
//	 * 移除所有钓鱼活动才出来的物品
//	 * @param all
//	 */
//	private void removeAllEquipment(List<FishProbabilityTemplet> all) {
//
//		Iterator<FishProbabilityTemplet> it = all.iterator();
//
//		while (it.hasNext()) {
//
//			FishProbabilityTemplet next = it.next();
//
//			if(next.getIsActivityOnly() == 1) {
//
//				it.remove();
//			}
//		}
//	}
//
//	/**
//	 *
//	 * 获得某一类型的所有鱼出现的几率
//	 *
//	 * @param all	某一类型的所有鱼
//	 * @return
//	 */
//	private int[] buildProbabilities(List<FishProbabilityTemplet> all) {
//
//		int [] aa = new int[all.size()];
//
//		for (int i = 0; i < aa.length; i++) {
//
//			aa[i] = all.get(i).getInteriorWeight();
//		}
//
//		return aa;
//	}
//}
//
