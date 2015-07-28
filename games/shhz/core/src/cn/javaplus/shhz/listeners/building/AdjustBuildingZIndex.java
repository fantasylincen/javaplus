package cn.javaplus.shhz.listeners.building;

import java.util.Comparator;

import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.building.BuildingMovedEvent;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

public class AdjustBuildingZIndex implements Listener<BuildingMovedEvent> {

	public class SortByY implements Comparator<Actor> {

		@Override
		public int compare(Actor a, Actor b) {
			return (int) (b.getY() - a.getY());
		}

	}

	private Comparator<Actor> comparator;

	public AdjustBuildingZIndex() {
		comparator = new SortByY();
	}

	@Override
	public void onEvent(BuildingMovedEvent e) {
		Group group = e.getBuilding().getParent();
		SnapshotArray<Actor> cs = group.getChildren();
		cs.sort(comparator);
	}

}
