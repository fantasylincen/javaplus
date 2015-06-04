package cn.mxz.listeners.equipment;

import cn.mxz.city.City;
import cn.mxz.events.AttributeChangeEvent;
import cn.mxz.events.EquipmentLevelUpEvent;
import cn.mxz.events.Events;
import cn.mxz.events.Listener;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class EquipmentLevelUpListener implements Listener<EquipmentLevelUpEvent> {

	@Override
	public void onEvent(EquipmentLevelUpEvent e) {

		
		City city = e.getCity();
		int oldShenJia = city.getFormation().getShenJia();
		UserCounter his = city.getUserCounterHistory();
		his.add(CounterKey.EQUIPMENT_LEVEL_UP_TIMES, 1);
		int levelAdd = e.getLevelAdd();
		UserCounter userCounter = city.getUserCounter();
		userCounter.add(CounterKey.EQUIPMENT_LEVEL_UP_TIMES, levelAdd);		
		

		Events.getInstance().dispatch(new AttributeChangeEvent(city, oldShenJia));
	}

}
