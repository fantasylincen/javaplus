package cn.javaplus.shhz.events.stage;

import cn.javaplus.shhz.stage.MapObject;

public class OnSelectEvent {

	private MapObject selected;

	public OnSelectEvent(MapObject selected) {
		this.selected = selected;
	}

	public MapObject getSelected() {
		return selected;
	}

}
