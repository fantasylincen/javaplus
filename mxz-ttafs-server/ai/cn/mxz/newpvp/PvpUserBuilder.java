package cn.mxz.newpvp;

import java.util.List;

import cn.javaplus.math.Fraction;
import cn.javaplus.random.Fetcher;
import cn.javaplus.util.Util;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.city.City;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.protocols.pvp.PvpP.PvpUser;
import cn.mxz.user.builder.PlayerBase;
import cn.mxz.user.builder.UserBaseBuilder;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.FractionBuilder;
import cn.mxz.util.debuger.Debuger;

class PvpUserBuilder {

	public PvpUser build(City me, PvpFightUser player) {
		return build(me, player, player.getRankInAll());
	}

	public PvpUser build(City me, PvpFightUser pfu, int rank) {
		PvpUser.Builder b = PvpUser.newBuilder();

		b.setRank(rank);

		PlayerBase py = pfu.getPlayer();
		int fighterTypeId = py.getMainFighterTypeId();
		b.setPlayerTypeId(fighterTypeId);
		b.setUser(new UserBaseBuilder().buildWithOutFightingCapCity(py));
//
//		Debuger.debug("PvpUserBuilder.build()" + fighterTypeId + pfu.getPlayer().getId());

		b.setDanId(pfu.getDanId());
		b.setPower(pfu.getPower());
		Fraction practice = pfu.getPractice();
//		Debuger.debug("PvpUserBuilder.build()" + practice.getNumerator());
		b.setPractice(new FractionBuilder().build(practice));

//		int sj = pfu.getCamp().getShenJia();
//		Debuger.debug("PvpUserBuilder.build()" + sj);
		
		b.setChiXuShiJianMiao(0);
		b.setVipLevel(py.getVipLevel());

//		int step = pfu.getStep();
//		Debuger.debug("PvpUserBuilder.build() step : " + step + " " + pfu.getPlayer().getNick());
		b.setStep(4);

		PlayerCamp c = pfu.getCamp();

		List<Hero> all = c.getFighters();

		Fetcher<Hero, Integer> f = new Fetcher<Hero, Integer>() {

			@Override
			public Integer get(Hero t) {
				return t.getTypeId();
			}
		};
		b.setFighters(Util.Collection.linkWith(",", all, f));

		b.setUpNumber(0);
		b.setPracticeWin(new PracticeCaculator().calc(me, pfu));

		b.setWarsituationId(pfu.getWarsituationId());
		return b.build();
	}

}
