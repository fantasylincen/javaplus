package cn.mxz.fish;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.FishService;
import cn.mxz.protocols.user.fish.FishUserP.FishUserPro;

@Component("fishService")
@Scope("prototype")

public class FishServiceImpl extends AbstractService implements FishService {

	@Override
	public void start(Boolean isAutoBuy) {
		// TODO 自动生成的方法存根

	}

	@Override
	public int stop(int probability) {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public FishUserPro getData() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public int sell(int gridId, int count) {
		// TODO 自动生成的方法存根
		return 0;
	}

//	private final Pond pond = Pond.getInstance();
//
//
//	@Override
//	public void start(Boolean isAutoBuy) {
//
//		pond.start(getCity(), isAutoBuy);
//	}
//
//	@Override
//	public int stop(int probability) {
//
//		int fishId = pond.fetch(getCity(), probability);
//
//		if(FishTempletConfig.get(fishId) != null) {
//			saveCount(fishId);
//		}
//
//		return fishId;
//	}
//
//	/**
//	 * 记录吊起来的鱼的数量
//	 * @param fishId
//	 */
//	private void saveCount(int fishId) {
//		UserCounterSetter his = getCity().getUserCounterHistory();
//		his.add(CounterKey.FISH_SUCCESS_COUNT, 1, fishId);
//	}
//
//	@Override
//	public FishUserPro getData() {
//		return new FishUserBuilder().build(getCity());
//	}
//
//	@Override
//	public int sell(int gridId, int count) {
//
//		Junket junket = getCity().getJunket();
//
//		Fish fish = junket.remove(gridId);
//
//		int typeId = fish.getTempletId();
//
//		FishTemplet temp = FishTempletConfig.get(typeId);
//
//		int price = temp.getSellPrice() * fish.getCount();
//
//		getPlayer().add(PlayerProperty.CASH, price);
//
//
//
//
//		MessageSender.sendMessage(getCity().getSocket(), S.S10158, price);
//
////		10149	金币 -%s0
////		10150	元宝 -%s0
////		10151	缘分 +%s0
////		10152	强化成功
////		10153	请找异性双休
////		10154
////		10155
////		10156	%s0 x %s1
////		10157	临时背包 %s0 x %s1
////		10158	金币 +%s0
////		10159	元宝 +%s0
//
//
//		return price;
//	}
}
