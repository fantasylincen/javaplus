package cn.mxz.listeners.pvp;

import message.S;
import cn.mxz.DanRewardTemplet;
import cn.mxz.DanRewardTempletConfig;
import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.pvp.PvpUpSuccessEvent;
import cn.mxz.newpvp.PvpPlayer;
import cn.mxz.util.message.MessageSenderToAllUp;

//晋级公告
public class NotifyAllUserPvpLevelUp implements Listener<PvpUpSuccessEvent> {

	@Override
	public void onEvent(PvpUpSuccessEvent e) {

		City city = e.getPlayer().getCity();
		String nick = city.getPlayer().getNick();
		PvpPlayer player = e.getPlayer();
		int dan = player.getDan();
		DanRewardTemplet temp = DanRewardTempletConfig.get(dan);
		
		new MessageSenderToAllUp().sendMessage(S.S72004, nick, temp.getTitle() + temp.getLevel());		
	}

}
