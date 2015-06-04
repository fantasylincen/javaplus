//package cn.mxz.base.prize;
//
//import java.util.Collection;
//
//import cn.mxz.mission.MissionFactory;
//import cn.mxz.user.Player;
//import cn.mxz.user.team.god.Hero;
//
///**
// * 仙露:使用后100%成功收服怪物。
// * @author 林岑
// *
// */
//public class XianLu implements AwardAble {
//
//	private Collection<Hero> heros;
//
//	@Override
//	public void award(Player player) {
//
//		heros = MissionFactory.getCaptures(player.getId()).captureByXianLu();
//	}
//
//	public Collection<Hero> getHeros() {
//		return heros;
//	}
//
//}
