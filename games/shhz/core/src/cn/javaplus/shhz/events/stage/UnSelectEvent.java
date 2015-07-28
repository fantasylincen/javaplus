package cn.javaplus.shhz.events.stage;

import cn.javaplus.shhz.stage.MapObject;

public class UnSelectEvent {

	private MapObject selected;

	public UnSelectEvent(MapObject selected) {
		this.selected = selected;
	}

	public MapObject getSelected() {
		return selected;
	}

}
