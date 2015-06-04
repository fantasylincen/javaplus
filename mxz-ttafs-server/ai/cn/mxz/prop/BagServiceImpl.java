package cn.mxz.prop;

import java.util.List;

import message.S;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Collection;
import cn.mxz.ConsumableTemplet;
import cn.mxz.ConsumableTempletConfig;
import cn.mxz.PropTemplet;
import cn.mxz.StuffTemplet;
import cn.mxz.StuffTempletConfig;
import cn.mxz.activity.PropBuilder;
import cn.mxz.bag.Bag;
import cn.mxz.bag.BagAuto;
import cn.mxz.bag.Grid;
import cn.mxz.bag.builder.BagBuilder;
import cn.mxz.bag.builder.GridBuilder;
import cn.mxz.base.exception.ClientException;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.prize.AwardAble;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.base.service.AbstractService;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.handler.BagService;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.PropP.PropPro;
import cn.mxz.protocols.user.PropP.PropsPro;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.protocols.user.god.BagP.BagPro;
import cn.mxz.protocols.user.mission.BoxP.BoxPro;
import cn.mxz.shop.Price;
import cn.mxz.shop.Shopper;
import cn.mxz.user.builder.UserBuilder;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.counter.UserCounterSetter;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.util.needs.INeeds;
import cn.mxz.util.needs.NeedsChecker;
import cn.mxz.util.needs.NeedsFactory;

import com.google.common.collect.Lists;

import define.D;

@Component("bagService")
@Scope("prototype")
public class BagServiceImpl extends AbstractService implements BagService {

	@Override
	public BagPro getData() {

		Bag<Grid> bag = getCity().getBag();

		BagPro build = new BagBuilder().build(bag);

		return build;
	}

	@Override
	public BoxPro useProp(int position) {

		Grid g = getCity().getBag().get(position);

		Integer typeId = g.getTempletId();

		return usePropToFighter(typeId, 1, -1);
	}

	@Override
	public BagPro getTempBagData() {
		// return new BagBuilder().build(getCity().getTempBag());
		return null;
	}

	@Override
	public PropPro getCount(int id) {

		int count = getCity().getBagAuto().getCount(id);

		// Debuger.debug("BagServiceImpl.getCount() " + " id =" + id + " 数量:"
		// + count);

		return new PropBuilder().build(id, count);
	}

	private List<AwardAble> use(int propType, int count, int fighterId) {

		ConsumableTemplet temp = ConsumableTempletConfig.get(propType);

		NeedsChecker c = null;
		if (temp != null) {
			c = NeedsFactory.getNeedsCheckerImpl2(temp.getNeeds());

			// 当遇到开启某个物品, 道具不够的时候, 提示 详细的道具需求信息
			try {
				c.check(getPlayer());
			} catch (ClientException e) {
				List<INeeds> needs = c.getNeeds();

				if (needs.size() == 1) {
					INeeds p = needs.get(0);
					int id = p.getId();
					int cc = p.getCount();
					PropTemplet templet = PropTempletFactory.get(id);
					throw new OperationFaildException(S.S10197, cc,
							templet.getName());
				}

			}
		}

		List<AwardAble> prize = Lists.newArrayList();

		getChecker().checkProp(propType, count);

		City city = getCity();
		for (int i = 0; i < count; i++) {

			List<AwardAble> s = PrizeSenderFactory.createUserPropPrize(
					propType, fighterId, city);
			prize.addAll(s);

		}

		for (AwardAble p : prize) {

			p.award(getPlayer());
		}

		if (temp != null) {
			PrizeSenderFactory.getPrizeSender().send(getPlayer(),
					temp.getCertainly());
		}

		city.getBagAuto().remove(propType, count);

		UserCounterSetter uc = city.getUserCounter();
		if (propType == D.PVP_FUWEN_ID) {
			uc.add(CounterKey.USE_FU_WEN_TIMES, 1);
		}
		if (propType == D.ID_SHEN_XING_DAN) {
			uc.add(CounterKey.USE_SHEN_XING_DAN_TIMES, 1);
		}
		if (propType == D.ID_HUI_QI_DAN) {
			uc.add(CounterKey.USE_HUI_QI_DAN_TIMES, 1);
		}

		if (c != null) {
			c.deduct(getPlayer());
		}

		return prize;
	}

	@Override
	public PropPro getCountBySpotId(int id) {

		List<Integer> ls = PropTempletFactory.getTypeIdsBySpotId(id);

		int count = 0;

		for (Integer integer : ls) {

			count += getCity().getBag().getCount(integer);
		}

		Debuger.debug("BagServiceImpl.getCount() " + " id =" + id + " 数量:"
				+ count);

		return new PropBuilder().build(id, count);
	}

	@Override
	public BoxPro usePropBySpotId(int spotId, int need, int fighterId) {

		List<AwardAble> prize = Lists.newArrayList();

		getChecker().checkPropBySpotId(spotId, need);

		while (need > 0) {

			Grid g = findFirstTypeId(spotId);

			int had = g.getCount();

			int remove = had < need ? had : need;

			List<AwardAble> use = use(g.getTempletId(), remove, fighterId);

			prize.addAll(use);

			need -= remove;
		}

		return new BoxBuilder().build(prize, getPlayer(), spotId);
	}

	private Grid findFirstTypeId(int spotId) {

		Bag<Grid> bag = getCity().getBag();

		for (Grid grid : bag) {

			int templetId = grid.getTempletId();

			PropTemplet propTemplet = PropTempletFactory.get(templetId);

			if (propTemplet.getSpot() == spotId) {

				return grid;
			}
		}

		return null;
	}

	@Override
	public void moveToBagFromTempBag(int gridId) {

		// City city = getCity();
		//
		// Bag<Grid> bag = city.getBag();
		//
		// Bag<Grid> tempBag = city.getTempBag();
		//
		// List<Grid> all = tempBag.getAll();
		//
		// for (Grid grid : all) {
		//
		// if (grid.getGridId() == gridId) {
		//
		// // if (FishTempletConfig.get(grid.getTempletId()) != null) {
		// //
		// // city.getJunket().addProp(grid.getTempletId(), grid.getCount());
		// //
		// // } else {
		//
		// bag.addProp(grid.getTempletId(), grid.getCount());
		// // }
		//
		// tempBag.remove(grid.getTempletId(), grid.getCount());
		//
		// break;
		// }
		// }
	}

	@Override
	public PropsPro getCounts(String ids) {
		List<Integer> idss = Collection.getIntegers(ids);
		PropsPro.Builder b = PropsPro.newBuilder();
		for (Integer id : idss) {
			b.addProps(getCount(id));
		}
		return b.build();
	}

	@Override
	public BoxPro usePropToFighter(int propType, int count, int fighterId) {

		List<AwardAble> prize = use(propType, count, fighterId);

		return new BoxBuilder().build(prize, getPlayer(), propType);
	}

	@Override
	public BoxPro useProp2(int propType, int count) {

		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();
		
		List<AwardAble> prize = use(propType, count, -1);

		UserPro b = new UserBuilder().build(getCity());
		MessageFactory.getUser().onUpdateUserList(getSocket(), b);

		s.snapshoot();
		
		return new BoxBuilder().build(prize, getPlayer(), propType);
	}

	@Override
	public BagPro getPiecesBag() {

		Bag<Grid> bag = getCity().getPiecesBag();

		BagPro build = new BagBuilder().build(bag);

		return build;
	}

	@Override
	public BoxPro buyAndUseProp2(int propType, int count) {
		new Shopper(getCity()).buy(propType, count);
		return useProp2(propType, count);
	}

	// 斗法符文使用次数 可购买VIP礼包等级 体力上限 精力上限 每日可使用神行丹次数 每日可使用回气丹次数
	// 0 0 0 0 0 0
	// int int int int int int
	// athleticsTimes bagGrade powerLimit vigorLimit addPower addVigor

	@Override
	public String getRemainUseTimes(int propType) {

		Shopper shopper = new Shopper(getCity());
		int count = shopper.getCanBuyCountToday(propType);
		// VipPrivilegeTemplet temp = VipPrivilegeTempletConfig
		// .get((byte) getCity().getVipPlayer().getLevel());
		//
		// if (propType == D.PVP_FUWEN_ID) {
		// return get(propType, CounterKey.USE_FU_WEN_TIMES,
		// temp.getAthleticsTimes());
		// }
		// if (propType == D.ID_SHEN_XING_DAN) {
		// return get(propType, CounterKey.USE_SHEN_XING_DAN_TIMES,
		// temp.getAddPower());
		// }
		// if (propType == D.ID_HUI_QI_DAN) {
		// return get(propType, CounterKey.USE_HUI_QI_DAN_TIMES,
		// temp.getAddVigor());
		// }
		//
		City c = getCity();
		BagAuto ba = c.getBagAuto();

		Price price = shopper.getPrice(propType, 1);

		return count + "/" + count + ":" + ba.getCount(propType) + ":"
				+ price.getGoldNew();
	}

	private String get(int propType, CounterKey key, int max) {

		City city = getCity();
		UserCounter uc = city.getUserCounter();
		int count = uc.get(key);

		int remain = max - count;
		remain = Math.max(0, remain);

		BagAuto ba = city.getBagAuto();

		return remain + "/" + max + ":" + ba.getCount(propType);
	}

	@Override
	public BagPro getBagAll() {
		BagPro.Builder b = BagPro.newBuilder();
		b.setCapacity(0);

		Bag<Grid> b1 = getCity().getBag();

		Bag<Grid> b2 = getCity().getPiecesBag();

		List<Grid> all = b1.getAll();
		List<Grid> all2 = b2.getAll();

		for (Grid grid : all) {

			b.addGrid(new GridBuilder().build(grid));
		}

		for (Grid grid : all2) {

			b.addGrid(new GridBuilder().build(grid));
		}

		return b.build();
	}

	@Override
	public void sell(String propTypesAndcounts) {
		List<Integer> ss = Util.Collection.getIntegers(propTypesAndcounts);
		for (int i = 0; i < ss.size(); i += 2) {
			Integer id = ss.get(i);
			Integer count = ss.get(i + 1);

			StuffTemplet temp = StuffTempletConfig.get(id);
			int sellPrice = temp.getSellPrice();
			if (sellPrice <= 0)
				continue;

			getCity().getBagAuto().remove(id, count);

			getCity().getPlayer().add(PlayerProperty.CASH, sellPrice * count);
		}

		getCity().getMessageSender().send(S.S10262);
	}
}
