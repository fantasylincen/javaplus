package cn.mxz.shenmo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.activity.PropBuilder;
import cn.mxz.bag.BagAuto;
import cn.mxz.base.service.AbstractService;
import cn.mxz.city.City;
import cn.mxz.handler.ShenMoService;
import cn.mxz.protocols.shenmo.ShenMoP.ShenMoListPro;
import cn.mxz.protocols.shenmo.ShenMoP.ShenmoPro;
import cn.mxz.protocols.shenmo.ShenMoP.ShenmoWarSituationPro;
import cn.mxz.protocols.user.PropP.PropPro;
import cn.mxz.shenmo.battle.ShemmoWarSituation;
import cn.mxz.shenmo.battle.ShenmoWarSituationBuilder;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounterSetter;

@Component("shenMoService")
@Scope("prototype")
public class ShenMoServiceImpl extends AbstractService implements ShenMoService {

	@Override
	public PropPro exchange(int stuffId) {
		new PropExchanger(getCity()).exchange(stuffId);
		BagAuto bag = getCity().getBagAuto();
		int count = bag.getCount(stuffId);
		return new PropBuilder().build(stuffId, count);
	}

	@Override
	public ShenmoWarSituationPro fight(int shenmoId, int type) {

		boolean isFullFighting = type == 1;//是否是全力战斗


		UserShenmo userShenmo = getCity().getUserShenmo();
		ShemmoWarSituation fight = userShenmo.fight(shenmoId, isFullFighting);

		if (isFullFighting) {

			UserCounterSetter uc = getCity().getUserCounter();
			uc.add(CounterKey.SHEN_MO_QLYJ_FIGNTING_TIMES, 1);
		}

		return new ShenmoWarSituationBuilder().build(fight, getCity());
	}

	@Override
	public ShenMoListPro getMyData() {
		City user =  getCity();
		return new ShenMoListBuilder().build(user);
	}

	@Override
	public String getPrize(int shenmoId) {
		UserShenmo userShenmo = getCity().getUserShenmo();
		String prize = userShenmo.getPrize(shenmoId);
		if( prize != null ){
			//PrizeBuilder b = new PrizeBuilder
		}
		return prize;
	}


	@Override
	public void getGongdePrize(int index) {
		UserShenmo userShenmo = getCity().getUserShenmo();
		userShenmo.getGongdePrize().getPrize(index);

	}

	@Override
	public ShenmoPro getNewShenmo() {
		UserShenmo userShenmo = getCity().getUserShenmo();
		ShenmoItem shenmo = userShenmo.getNewShenmo();
		return new ShenMoBuilder().build(shenmo, getCity() );
	}
}
