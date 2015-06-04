package cn.mxz.newpvp;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.protocols.pvp.PvpP.PvpUsersPro;

public class PvpUsersBuilder {

	/**
	 * @param city
	 * @param players
	 * @param page
	 * @param pageAll
	 * @param count		每页人数....
	 * @return
	 */
	public PvpUsersPro build(City city, List<PvpFightUser> players, int page, int pageAll, int count) {
		PvpUsersPro.Builder b = PvpUsersPro.newBuilder();
		int rank = 1 + count * (page - 1);
		for (PvpFightUser p : players) {
			b.addUsers(new PvpUserBuilder().build(city, p, rank++));
		}
		b.setPage(page);
		b.setPageAll(pageAll);
		return b.build();
	}

	public PvpUsersPro build(City city, List<PvpPlayer> players) {

		PvpUsersPro.Builder b = PvpUsersPro.newBuilder();
		int rank = 1;
		for (PvpPlayer p : players) {
			NormalPvpFightUser npf = new NormalPvpFightUser(p);
			b.addUsers(new PvpUserBuilder().build(city, npf, rank++));
		}
		b.setPage(1);
		b.setPageAll(1);
		return b.build();
	}

	public PvpUsersPro build(City city, List<PvpPlayer> players, int rankStart) {

		PvpUsersPro.Builder b = PvpUsersPro.newBuilder();
		for (PvpPlayer p : players) {
			NormalPvpFightUser npf = new NormalPvpFightUser(p);
			b.addUsers(new PvpUserBuilder().build(city, npf, rankStart++));

//			Debuger.debug("PvpServiceImpl.getUsersNearMe()" + rankStart);
		}
		b.setPage(1);
		b.setPageAll(1);
		return b.build();
	}

}
