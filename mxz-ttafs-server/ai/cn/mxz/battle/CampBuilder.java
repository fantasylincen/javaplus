package cn.mxz.battle;

import java.util.List;

import cn.mxz.dogz.Dogz;
import cn.mxz.dogz.DogzBuilder;
import cn.mxz.fighter.Fighter;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.protocols.user.battle.WarSituationP.CampPro;
import cn.mxz.util.debuger.Debuger;

public class CampBuilder {

	public CampPro build(Camp<?> camp) {

		final CampPro.Builder b = CampPro.newBuilder();

		List<? extends Fighter> fightersFront = camp.getFighters();

		for (Fighter f : fightersFront) {
			b.addFighters(new BattleFighterBuilder().build(f, camp));
		}
		
		Dogz fighting = camp.getDogz();

		if (fighting != null) {
			b.setFighting(new DogzBuilder().build(fighting));
		}
		other(camp, b);

		return b.build();
	}

	private void other(Camp<?> camp, final CampPro.Builder b) {
		Fighter mf = camp.getMainFighter();
		if (mf != null) {
			String name = mf.getName();
			b.setNick(name);
			b.setLevel(mf.getLevel());
		}
		
		String userId = camp.getUserId();
		if(userId != null) {
			b.setUserId(userId);
		}
		
		int shenJia = camp.getShenJia();
		
		if(camp instanceof PlayerCamp) {
			PlayerCamp c = (PlayerCamp) camp;
			shenJia = c.getCity().getFormation().getShenJia();
		}
		
//		Debuger.debug("CampBuilder.other()" + camp.getClass());
		
//		Debuger.debug("CampBuilder.other() 222222    " + shenJia);
		b.setCapacity(shenJia);
	}
}
