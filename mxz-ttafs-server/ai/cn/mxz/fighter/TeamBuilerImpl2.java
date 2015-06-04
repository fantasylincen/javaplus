//package cn.mxz.fighter;
//
//import java.util.List;
//
//import cn.mxz.protocols.user.god.TeamP.TeamPro;
//import cn.mxz.user.City;
//
//public class TeamBuilerImpl2 {
//
//	public TeamPro build(City city) {
//
//		TeamPro.Builder b = TeamPro.newBuilder();
//
//		List<TGrid> ts = city.getTeam().getTeamGrids();
//
//		for (TGrid t : ts) {
//
////			Debuger.debug("TeamBuilerImpl2.build() " + t.getFighterId());
//
//			b.addGrids(new TeamGridBuilder().build(t));
//
//		}
//
//		return b.build();
//	}
//
//}
