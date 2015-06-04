package cn.mxz.daji;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.battle.Battle;
import cn.mxz.battle.WarSituationBuilder;
import cn.mxz.city.City;
import cn.mxz.corona.PropsBuilder;
import cn.mxz.handler.DajiService;
import cn.mxz.protocols.daji.DajiP.DajiPro;
import cn.mxz.protocols.daji.DajiP.DajiResultPro;
import cn.mxz.protocols.user.PropP.PropsPro;

@Component("dajiService")
@Scope("prototype")
public class DajiServiceImpl extends AbstractService implements DajiService {

	@Override
	public DajiPro getData() {

		DajiUserData data = ProtectDajiManager.INSTANCE.getUserData(getCity());
		DajiPro.Builder b = DajiPro.newBuilder();

		// b.setCd(value)
		b.setIsProtect(data.isProtectToday());
		b.setProtectCount(data.getAllProtectCount());
		int remainSec = data.getCd().getRemainSencond();
		b.setCd(remainSec);
		b.setCurrentMisiion(data.getTodayDriftCount());// 今日已经驱赶的次数

		cn.mxz.protocols.user.PropP.PropsPro.Builder pp = PropsPro.newBuilder();
		pp.addAllProps(new PropsBuilder().build(data.getPrizes()));
		b.setProps(pp);

		return b.build();
	}

	@Override
	public DajiResultPro drift() {

		City user = getCity();

		Battle b = ProtectDajiManager.INSTANCE.drift(user);

		DajiResultPro.Builder builder = DajiResultPro.newBuilder();
		builder.setSituation(new WarSituationBuilder().build(getCity(), b.getWarSituation()));

		DajiUserData data = ProtectDajiManager.INSTANCE.getUserData(getCity());

		cn.mxz.protocols.user.PropP.PropsPro.Builder pp = PropsPro.newBuilder();
		pp.addAllProps(new PropsBuilder().build(data.getPrizes()));
		builder.setProps(pp);

		return builder.build();

	}

	@Override
	public int protect() {
		return ProtectDajiManager.INSTANCE.protect(getCity());

	}

	@Override
	public int getProtectPrize() {
		return ProtectDajiManager.INSTANCE.getProtectPrize(getCity());

	}

	@Override
	public void getDriftPrize() {
		ProtectDajiManager.INSTANCE.getDriftPrize(getCity());

	}

}
