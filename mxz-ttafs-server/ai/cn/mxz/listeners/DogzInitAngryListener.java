package cn.mxz.listeners;

import cn.mxz.DogzTemplet;
import cn.mxz.DogzTempletConfig;
import cn.mxz.dogz.Dogz;
import cn.mxz.events.FightingStartEvent;
import cn.mxz.events.Listener;
import cn.mxz.formation.PlayerCamp;

// 神兽怒气初始化
public class DogzInitAngryListener implements Listener<FightingStartEvent> {

	@Override
	public void onEvent(FightingStartEvent event) {
		initAngry(event.getUnder());
		initAngry(event.getUpper());
	}

	private void initAngry(PlayerCamp under) {
		if (under == null) {
			return;
		}
		Dogz fighting = under.getCity().getDogzManager().getFighting();

		if (fighting != null) {
			DogzTemplet temp = DogzTempletConfig.get(fighting.getTypeId());

			fighting.setAngry(temp.getInitAngry());
		}
	}

}
