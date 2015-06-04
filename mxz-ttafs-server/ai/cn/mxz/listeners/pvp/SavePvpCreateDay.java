package cn.mxz.listeners.pvp;

import cn.mxz.city.City;
import cn.mxz.city.FunctionOpenManager;
import cn.mxz.city.FunctionOpenManager.ModuleType;
import cn.mxz.events.Listener;
import cn.mxz.events.PlayerHeroLevelUpEvent;
import cn.mxz.newpvp.PvpPlayer;

public class SavePvpCreateDay implements Listener<PlayerHeroLevelUpEvent> {

	@Override
	public void onEvent(PlayerHeroLevelUpEvent e) {
		savePvpCreateDay(e);
	}

	private void savePvpCreateDay(PlayerHeroLevelUpEvent e) {
		City city = e.getCity();
		FunctionOpenManager fm = city.getFunctionOpenManager();
		if(fm.isOpen(ModuleType.DouFaMoKuai)) {
			PvpPlayer player = city.getNewPvpManager().getPlayer();
			if(!player.hasInit()) {
				player.saveCreateDay();
			}
		}
	}

}
