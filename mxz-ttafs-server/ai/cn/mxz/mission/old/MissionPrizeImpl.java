package cn.mxz.mission.old;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.battle.BattleExpPrize;


public class MissionPrizeImpl implements MissionPrize 	{

	private List<BattleExpPrize>	fighterPrize;

	private List<PropPrize>			propPrize;

	private int star;

	public MissionPrizeImpl(List<BattleExpPrize> fighterPrize,
			List<PropPrize> propPrize, int star) {
				this.fighterPrize = fighterPrize;
				this.propPrize = propPrize;
				this.star = star;
	}

	@Override
	public List<BattleExpPrize> getFighterPrize() {

		final List<BattleExpPrize> ls = new ArrayList<BattleExpPrize>(fighterPrize);

		fighterPrize = new ArrayList<BattleExpPrize>();

		return ls;
	}

	@Override
	public List<PropPrize> getPropPrizes() {

		final List<PropPrize> ls = new ArrayList<PropPrize>(propPrize);

		propPrize = new ArrayList<PropPrize>();

		return ls;
	}

	@Override
	public int getStar() {

		return star;
	}

}
