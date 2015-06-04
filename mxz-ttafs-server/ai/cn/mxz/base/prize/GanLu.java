//package cn.mxz.base.prize;
//
//import java.util.List;
//
//import cn.mxz.mission.MissionFactory;
//import cn.mxz.user.Player;
//import cn.mxz.user.mission.Captures;
//import cn.mxz.user.team.god.Hero;
//
///**
// *
// * 甘露:使用后有几率收服怪物。
// * @author 林岑
// *
// */
//public class GanLu implements AwardAble {
//
//	private List<Hero> heros;
//
//	@Override
//	public void award(Player player) {
//
//		Captures captures = MissionFactory.getCaptures(player.getId());
//
//		heros = captures.captureByGanLu();
//	}
//
//	public List<Hero> getHeros() {
//		return heros;
//	}
//}
