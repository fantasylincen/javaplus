//package cn.mxz.fighter;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import cn.javaplus.common.db.DAO2;
//import cn.mxz.protocols.user.god.FighterP.FightersHadPro;
//import cn.mxz.user.City;
//import cn.mxz.user.team.god.Hero;
//import db.dao.factory.DaoFactory;
//import db.domain.FighterHad;
//
//public class FightersHadBuilder {
//
//	public FightersHadPro build(City city) {
//
//		FightersHadPro.Builder b = FightersHadPro.newBuilder();
//
//		Set<Integer> findBy = findIds(city);
//
//		for (Integer fighterHad : findBy ) {
//
//			b.addFighterTypeId(fighterHad);
//		}
//
//		return b.build();
//	}
//
//	private Set<Integer> findIds(City city) {
//
//		DAO2<Integer, String, FighterHad> fighterHadDAO = DaoFactory.getFighterHadDAO();
//
//
//		Set<Integer> set = new HashSet<Integer>();
//
//		Collection<Hero> values = city.getTeam().getAll();
//
//		for (Hero hero : values) {
//
//			set.add(hero.getTypeId());
//		}
//
//		List<FighterHad> findBy = fighterHadDAO.findByUname(city.getId());
//
//		for (FighterHad f : findBy) {
//
//			set.add(f.getFighterTypeId());
//		}
//
//		return set;
//	}
//}
