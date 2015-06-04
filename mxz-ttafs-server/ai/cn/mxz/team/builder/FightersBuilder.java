package cn.mxz.team.builder;

import java.util.Collection;

import cn.mxz.protocols.user.god.FighterP.FightersPro;
import cn.mxz.user.team.god.Hero;


/**
 * 神将列表
 */
public class FightersBuilder{

	public FightersPro build(Collection<Hero> hs) {

		FightersPro.Builder b = FightersPro.newBuilder();

		for (Hero g :  hs) {

			b.addFighters(new FighterBuilder().build(g));
		}

		return b.build();
	}
}
