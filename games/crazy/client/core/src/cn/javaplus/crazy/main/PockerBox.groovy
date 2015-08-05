package cn.javaplus.crazy.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.crazy.D;
import cn.javaplus.crazy.Protocols.CardP;
import cn.javaplus.crazy.collections.Lists;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

public class PockerBox implements IPockerBox {

	private Group box;
	private CardUI ui;

	public PockerBox(Group box, CardUI ui) {
		this.box = box;
		this.ui = ui;
	}
	@Override
	public void addCard(CardActor actor) {
		box.addActor(actor);
	}

	@Override
	public List<CardActor> getCards() {
		SnapshotArray<Actor> c = box.getChildren();
		ArrayList<CardActor> ls = Lists.newArrayList();
		for (Actor actor : c) {
			ls.add((CardActor) actor);
		}
		return ls;
	}

	@Override
	public void showCards(List<CardP> cards) {
		for (CardP cardP : cards) {
			CardActor e = new CardActor(cardP, ui);
			e.setX(box.getWidth() / 2);
			addCard(e);
		}
		sortCards();

//		for (Actor cc : box.getChildren()) {
//			ICardActor e = (ICardActor) cc;
//			Action action = buildAction(e);
//			e.addAction(action);
//		}
//		Log.d("MinePlayer.showCards ");
	}

	@Override
	public void sortCards() {

		SnapshotArray<Actor> cards = box.getChildren();
		cards.sort();
		int i = 0;
		for (Actor c : cards) {
			c.setZIndex(i++);
		}
	}

	@Override
	public void remove(List<CardP> cards) {
		for (CardP cardP : cards) {
			remove(cardP.getId());
		}
	}

	@Override
	public void adjustPockersPosition() {
		sortCards();
		for (Actor c : box.getChildren()) {
			c.setX(calcX(c));
		}
	}

	private float calcX(Actor c) {

		SnapshotArray<Actor> cards = box.getChildren();
		int width = (int) ((cards.size - 1) * D.POCKER_SPACE + c.getWidth());
		float center = box.getWidth() / 2;
		float startX = center - width / 2;
		int index = cards.indexOf(c, true);

		float x = index * D.POCKER_SPACE + startX;
		return x;
	}
//
//	private Action buildAction(CardActor a) {
//		float x = calcX(a);
//
//		MoveToAction m = new MoveToAction() {
//			@Override
//			protected void end() {
//				super.end();
//				getActor().clearActions();
//			}
//		};
//		m.setDuration(0.1f);
//		m.setPosition(x, 0);
//		return m;
//	}

	@Override
	public List<Integer> getSelectedIds() {
		List<CardActor> all = getSelectedCards();
		return getSelectedIds(all);
	}

	@Override
	public List<Integer> getSelectedIds(List<CardActor> cards) {
		ArrayList<Integer> ls = Lists.newArrayList();
		for (CardActor actor : cards) {
			ls.add(actor.getId());
		}
		return ls;
	}

	@Override
	public List<CardActor> getSelectedCards() {
		SnapshotArray<Actor> all = box.getChildren();
		ArrayList<CardActor> ls = Lists.newArrayList();
		for (Actor actor : all) {

			CardActor c = (CardActor) actor;

			boolean select = c.isSelect();
			if (select) {
				ls.add(c);
			}
		}
		return ls;
	}

	@Override
	public void remove(int id) {
		SnapshotArray<Actor> all = box.getChildren();
		Iterator<Actor> it = all.iterator();
		while (it.hasNext()) {
			CardActor a = (CardActor) it.next();
			if (a.getId() == id) {
				it.remove();
				break;
			}
		}
	}
	@Override
	public void clear() {
		box.clear();
	}

}
