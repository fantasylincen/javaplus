//package cn.mxz.fighter;
//
//import message.S;
//import cn.mxz.city.City;
//import cn.mxz.rankinglist.RankingList;
//import cn.mxz.rankinglist.RankingListImpl;
//import cn.mxz.user.team.god.Hero;
//import cn.mxz.util.message.MessageSenderToAllUp;
//
///**
// * 全服通知玩家升级了
// * @author 林岑
// */
//public class NoticeOnLevelUp implements LevelUpListener {
//
//	@Override
//	public void onLevelUp(LevelUpEvent e) {
//
////		Hero h = e.getSource();
////
////		int level = h.getLevel();
////
////
////		int code = -1;
////
////
////		if(level == 50) {
////
////			code = S.S71002;
////
////		} else if(level == 100) {
////
////			code = S.S71003;
////		}
////
////
////		if (h instanceof PlayerHero) {
////
////			PlayerHero hero = (PlayerHero) h;
////
////			City c = hero.getCity();
////
////			if(code != -1) {
////
////				RankingList rl = RankingListImpl.getInstance();
////
////				int levelRank = rl.getLevelRank(c.getId());
////
////				String nick = c.getPlayer().getNick();
////
////				new MessageSenderToAllUp().sendMessage(code, nick, levelRank + "");
////			}
////		}
//	}
//
//	@Override
//	public void beforeAddExp(BeforeAddExpEvent e) {
//	}
//}
