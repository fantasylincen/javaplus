package cn.mxz.market;

import cn.mxz.FighterTempletConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;

class TradeFighterImpl implements TradeFighter {


	private Integer fighterId;
	private String uname;
	private int tradeTime;

	TradeFighterImpl(Integer fighterId, String uname, int tradeTime) {
		this.fighterId = fighterId;
		this.uname = uname;
		this.tradeTime = tradeTime;
		
	}

	@Override
	public String getNick() {

		Hero h = getHero();
		
		int typeId = h.getTypeId();
		
		IFighterTemplet fighterTemplet = FighterTempletConfig.get(typeId);
		
		return fighterTemplet.getName();
	}

	private Hero getHero() {
		
		City city = WorldFactory.getWorld().get(uname);
		
		Hero god = city.getTeam().get(fighterId);
		
		return god;
	}

	@Override
	public int getTypeId() {

		return getHero().getTypeId();
	}

	@Override
	public int getFighterId() {

		return fighterId;
	}

	@Override
	public int getTradeTime() {

		return tradeTime;
	}

	@Override
	public String getBelongPlayer() {

		return uname;
	}
}
