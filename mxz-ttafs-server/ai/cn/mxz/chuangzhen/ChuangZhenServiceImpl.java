package cn.mxz.chuangzhen;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.Responses;
import cn.mxz.base.service.AbstractService;
import cn.mxz.battle.BattleCamp;
import cn.mxz.city.City;
import cn.mxz.fighter.Fighter;
import cn.mxz.handler.ChuangZhenService;
import cn.mxz.protocols.user.chuangzhen.ChuangZhenP.ChuangZhenWarSituationPro;
import cn.mxz.util.counter.CounterKey;

@Component("chuangZhenService")
@Scope("prototype")
public class ChuangZhenServiceImpl extends AbstractService implements
		ChuangZhenService {

	@Override
	public ChuangZhenWarSituationPro fighting(int type) {
		City city = getCity();
		ChuangZhenPlayer p = city.getChuangZhenPlayer();

		ChuangZhenBattle battle = new ChuangZhenBattle(p, type);

		hpFull(battle.getUpper());

		battle.fighting();

		Responses r = new Responses(city);
		r.getChuangZhenTransform().responseGetData(p);

		city.getUserCounterAuto().add(CounterKey.CHUANG_ZHEN_TIMES, 1);
		
		ChuangZhenRankingList.getInstance().updateTodayRank(p);

		return new ChuangZhenWarSituationBuilder().build(p, battle);

	}

	private void hpFull(BattleCamp upper) {
		List<Fighter> ls = upper.getFighters();
		for (Fighter fighter : ls) {
			fighter.addHp(fighter.getHpMax() - fighter.getHpNow());
		}
	}
}
