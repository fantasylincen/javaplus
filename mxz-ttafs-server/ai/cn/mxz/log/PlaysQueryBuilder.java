package cn.mxz.log;

import cn.mxz.city.City;
import cn.mxz.protocols.user.friend.QueryFriendAppayP.PlaysQueryPro;
import cn.mxz.user.Player;
import cn.mxz.user.builder.UserBaseBuilder;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;

public class PlaysQueryBuilder {

	public PlaysQueryPro build(City city) {

		final PlaysQueryPro.Builder b = PlaysQueryPro.newBuilder();

		Team team2 = city.getTeam();

		Hero player2 = team2.getPlayer();

		int typeId = player2.getTypeId();

		Player player = city.getPlayer();

		// 赋值
		boolean man = player.isMan();

		b.setIsMan(man);

		b.setUserId(player.getId());

		b.setType(typeId);

		b.setLevel(player.getLevel());
		
		b.setUserBase(new UserBaseBuilder().build(player));

		b.setNick(player.getNick());

		b.setLastLogin(city.getPlayer().getLastLoginSec());

		return b.build();
	}

}
