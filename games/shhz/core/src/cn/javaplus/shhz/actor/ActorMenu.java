package cn.javaplus.shhz.actor;

import java.util.List;

import cn.javaplus.shhz.collections.Lists;
import cn.javaplus.shhz.components.itemlist.Item;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

public class ActorMenu extends Group {

	private ActorControlable actor;

	public ActorMenu(ActorControlable actor) {
		this.actor = actor;
		add(new MoveToItem());
		add(new CancelItem());
		add(new OtherItem());

		for (Actor a : getChildren()) {
			a.setSize(0, 0);
		}

	}

	private void add(Item item) {
		addActor(item);
	}

	public ActorControlable getActor() {
		return actor;
	}

	public List<Item> getItems() {
		SnapshotArray<Actor> all = getChildren();
		List<Item> ls = Lists.newArrayList();
		for (Actor actor : all) {
			ls.add((Item) actor);
		}
		return ls;
	}

	public void show() {

		SnapshotArray<Actor> cs = getChildren();

		int r = 300; // 半径

		double angle = 2 * Math.PI / cs.size;

		float w = actor.getWidth();
		float h = actor.getHeight();

		for (int i = 0; i < cs.size; i++) {
			Actor a = cs.get(i);
			a.clearActions();
			double ag = i * angle;
			a.setPosition(w / 2, h / 2);
			a.addAction(new SanKaiAction(this, ag, r));
		}
	}

	public void hide() {
		for (Actor a : getChildren()) {
			a.clearActions();
			a.addAction(new HeLongAction(actor));
		}
	}

}
