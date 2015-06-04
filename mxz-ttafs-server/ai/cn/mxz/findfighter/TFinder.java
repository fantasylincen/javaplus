package cn.mxz.findfighter;

import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;


class TFinder extends FinderBase {


	TFinder(City city, String string) {
		super(city, string);
	}

	@Override
	public Hero findOneTime() {
		return city.getTeam().createNewHero(type);
	}

}
