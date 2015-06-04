package cn.mxz.listeners.city;

import message.S;
import cn.mxz.events.Listener;
import cn.mxz.events.PlayerHeroLevelUpEvent;
import cn.mxz.newpvp.PvpPlace;
import cn.mxz.newpvp.PvpPlaceImpl;
import cn.mxz.newpvp.PvpPlayer;
import cn.mxz.util.message.MessageSenderToAllUp;

public class NoticePlayerLevelUp implements Listener<PlayerHeroLevelUpEvent> {

	@Override
	public void onEvent(PlayerHeroLevelUpEvent e) {
		
		String nick = e.getCity().getPlayer().getNick();
		
		if(e.getLevel() == 50) {
			int rank = getRank(e);
			new MessageSenderToAllUp().sendMessage(S.S72001, nick, rank);
		} else if(e.getLevel() == 100) {
			int rank = getRank(e);
			new MessageSenderToAllUp().sendMessage(S.S72002, nick, rank);
		}
	}

	private int getRank(PlayerHeroLevelUpEvent e) {
		PvpPlace p = PvpPlaceImpl.getInstance();
		PvpPlayer player = e.getCity().getNewPvpManager().getPlayer();
		int rank = p.getRankInAll(player);
		return rank;
	}

}
