package cn.mxz.findfighter;

import java.util.List;

import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;


class SFinder extends FinderBase {


	SFinder(City city, String string) {
		super(city, string);
	}

	@Override
	public Hero findOneTime() {

		List<FighterTemplet> findBySpot = FighterTempletConfig.findBySpot(type);

		FighterTemplet randomOne = cn.javaplus.util.Util.Collection.getRandomOne(findBySpot);

		return city.getTeam().createNewHero(randomOne.getType());
	}

}
