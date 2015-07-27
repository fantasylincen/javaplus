package cn.javaplus.shhz.listeners.building;

import cn.javaplus.game.shhz.Events;
import cn.javaplus.shhz.components.buildings.Building;
import cn.javaplus.shhz.events.BuildingOnSelectEvent;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.stage.OnSelectEvent;
import cn.javaplus.shhz.stage.MapObject;

public class BuildingOnSelectDispatchEvent implements Listener<OnSelectEvent> {

	@Override
	public void onEvent(OnSelectEvent e) {
		MapObject selected = e.getSelected();
		if (selected instanceof Building) {
			Building building = (Building) selected;
			Events.dispatch(new BuildingOnSelectEvent(building));
		}
	}

}
