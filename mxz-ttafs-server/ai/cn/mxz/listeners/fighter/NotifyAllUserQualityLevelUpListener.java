package cn.mxz.listeners.fighter;

import message.S;
import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.fighter.QualityLevelUpEvent;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.message.MessageSenderToAllUp;

//玩家进阶成功
public class NotifyAllUserQualityLevelUpListener implements Listener<QualityLevelUpEvent> {

	@Override
	public void onEvent(QualityLevelUpEvent e) {
		Hero hero = e.getHero();
		City city = e.getCity();
//		72006	<font color='#FF0000' size='24'>【恭喜】</font> 玩家<font color='#00CC66' size='24'>%s0</font>成功进阶<font color='#00FF00' size='24'>乙级%s1星</font>伙伴 <font color='#CC0000' size='24'>[ %s2]</font>
//		72007	<font color='#FF0000' size='24'>【恭喜】</font> 玩家<font color='#00CC66' size='24'>%s0</font>成功进阶<font color='#00FF00' size='24'>甲级%s1星</font>伙伴 <font color='#CC0000' size='24'>[ %s2]</font>

		int step = hero.getStep();
		
		if(step == 4 || step == 5) {
			new MessageSenderToAllUp().sendMessage(S.S72007, city.getPlayer().getNick(), hero.getStar());
		} else if(step == 3) {
			new MessageSenderToAllUp().sendMessage(S.S72006, city.getPlayer().getNick(), hero.getStar());
			
		}
		
	}

}
