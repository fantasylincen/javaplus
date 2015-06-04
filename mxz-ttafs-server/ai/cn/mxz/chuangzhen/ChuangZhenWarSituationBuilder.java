package cn.mxz.chuangzhen;

import cn.mxz.battle.WarSituationBuilder;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.protocols.user.chuangzhen.ChuangZhenP.ChuangZhenWarSituationPro;

public class ChuangZhenWarSituationBuilder {

	public ChuangZhenWarSituationPro build(ChuangZhenPlayer player, ChuangZhenBattle battle) {
		ChuangZhenWarSituationPro.Builder b = ChuangZhenWarSituationPro.newBuilder();
		b.setFloor1(player.getNextAddition());
		b.setFloor2(player.getNextReward());
		b.setStar1(battle.getStarSingle());
		b.setX(battle.getStarX());
		b.setStar2(battle.getStarReceived());
		String id = player.getId();
		City city = CityFactory.getCity(id);
		b.setWarSituation(new WarSituationBuilder().build(city, battle.getWarSituation()));
		b.setTimesRemain(player.getMaxTimes() - player.getCurrentTimes());
		b.setRank(player.getRank());
		b.setFighterCount(player.getFormationCount());
		b.setCash(/*player.getCash()*/0);
		return b.build();
	}

}
