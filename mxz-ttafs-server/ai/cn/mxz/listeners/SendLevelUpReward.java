package cn.mxz.listeners;

import cn.mxz.AuthorityTemplet;
import cn.mxz.AuthorityTempletConfig;
import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.PlayerHeroLevelUpEvent;

//玩家升级时, 发放升级奖励
public class SendLevelUpReward implements Listener<PlayerHeroLevelUpEvent> {

	@Override
	public void onEvent(PlayerHeroLevelUpEvent e) {

		City c = e.getCity();

		AuthorityTemplet temp = AuthorityTempletConfig.get(c.getLevel());

		String xxx = temp.getLevelUpgoods();
		String levelUpAward = temp.getLevelUpAward();
		c.getPrizeSender1().send(xxx);
		c.getPrizeSender1().send(levelUpAward);
	}

}
