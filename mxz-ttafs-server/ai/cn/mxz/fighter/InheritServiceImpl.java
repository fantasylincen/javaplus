package cn.mxz.fighter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.city.City;
import cn.mxz.handler.InheritService;
import cn.mxz.protocols.user.god.FighterP.FightersPro;
import cn.mxz.team.builder.FightersBuilder;
import cn.mxz.user.team.god.Hero;

@Component("inheritService")
@Scope("prototype")

public class InheritServiceImpl  extends AbstractService implements InheritService {


	@Override
	public FightersPro inherit(int from, int to, int propId) {

		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();

		City city = getCity();
		Hero godFrom = city.getTeam().get(from);
		Hero godTo = city.getTeam().get(to);

		if(godTo == null || godFrom == null) {
			throw new NullPointerException("战士不存在!");
		}

		Inherit i = new Inherit();
		i.run( godFrom, godTo, propId, getCity() );

		List<Hero> l = new ArrayList<Hero>();
		l.add(godTo);
		l.add(godFrom);
		s.snapshoot();

		return new FightersBuilder().build( l );
	}

	@Override
	public int getInheritLevel(int from, int to, int propId) {
		Inherit i = new Inherit();
		return i.getToLevel( from, to, propId, getCity() );
	}

}
