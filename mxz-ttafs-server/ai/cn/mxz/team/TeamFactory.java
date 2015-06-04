package cn.mxz.team;

import cn.mxz.city.City;
import cn.mxz.user.team.Team;

/**
 * 队伍工厂
 * @author 林岑
 *
 */
public class TeamFactory {

	public static Team createTeam(City city) {

		final TeamImpl team = new TeamImpl(city);

		team.setFighters(team.createFighters());

		return team;
	}

}
