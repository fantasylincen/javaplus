package cn.mxz.listeners.tianming;

import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.equipment.TianMingChangeEvent;
import cn.mxz.messagesender.MessageFactory;

/**
 * 当天命变化了, 通知客户端
 * 
 * @author 林岑
 * 
 */
public class UpdateClientTianMingChangedListener implements
		Listener<TianMingChangeEvent> {

	@Override
	public void onEvent(TianMingChangeEvent e) {
		City user = e.getUser();

		List<Integer> as = e.getEquipmentTianMingAdd();
		as.addAll(e.getHeroTianMingAdd());
		as.addAll(e.getSkillTianMingAdd());

		List<Integer> rs = e.getEquipmentTianMingRemove();
		as.addAll(e.getHeroTianMingRemove());
		as.addAll(e.getSkillTianMingRemove());

		MessageFactory.getFighter().tianMingChanged(user.getSocket(), link(as),
				link(rs));
	}

	private String link(List<Integer> rs) {
		return Util.Collection.linkWith(",", rs);
	}

}
