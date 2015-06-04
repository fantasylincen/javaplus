package cn.mxz.listeners;

import message.S;
import cn.mxz.city.City;
import cn.mxz.city.IMessageSender;
import cn.mxz.events.Listener;
import cn.mxz.events.PlayerHeroQualityUpEvent;

//主角进阶成功 冒子提示
public class ShowSuccessMessage implements Listener<PlayerHeroQualityUpEvent> {

	@Override
	public void onEvent(PlayerHeroQualityUpEvent e) {
		City city = e.getCity();
		IMessageSender s = city.getMessageSender();
		s.send(S.S10257);
	}

}
