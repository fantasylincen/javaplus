package cn.javaplus.shhz.listeners.building;

import cn.javaplus.game.shhz.Events;
import cn.javaplus.shhz.components.buildings.Building;
import cn.javaplus.shhz.events.BuildingUnSelectEvent;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.stage.UnSelectEvent;
import cn.javaplus.shhz.stage.MapObject;

public class BuildingUnSelectDispatchEvent implements Listener<UnSelectEvent> {

	@Override
	public void onEvent(UnSelectEvent e) {
		MapObject selected = e.getSelected();
		if (selected instanceof Building) {
			Building building = (Building) selected;
			Events.dispatch(new BuildingUnSelectEvent(building));
		}
	}

}
