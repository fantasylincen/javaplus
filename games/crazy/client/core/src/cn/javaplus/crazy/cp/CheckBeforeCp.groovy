package cn.javaplus.crazy.cp;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.crazy.collections.Lists;
import cn.javaplus.crazy.events.Listener;
import cn.javaplus.crazy.main.CardActor;
import cn.javaplus.crazy.main.CardActor;
import cn.javaplus.crazy.main.IPockerBox;
import cn.javaplus.crazy.main.MyPlayer;
import cn.javaplus.crazy.pocker.Card;
import cn.javaplus.crazy.pocker.DzRule;
import cn.javaplus.crazy.pocker.Pocker;

public class CheckBeforeCp implements Listener<BeforeCpEvent> {

	@Override
	public void onEvent(BeforeCpEvent e) {
		List<Integer> ids = e.getIds();
		MyPlayer mp = e.getMinePlayer();
		IPockerBox box = mp.getPockerBox();
		List<CardActor> cards = box.getCards();

		List<Card> willSend = buildWillSend(ids);
		List<Card> lastCards = buildLastCards(cards);
		DzRule.check(willSend, lastCards);
	}

	private List<Card> buildLastCards(List<CardActor> cards) {
		ArrayList<Card> ls = Lists.newArrayList();
		for (CardActor d : cards) {
			ls.add(Pocker.get(d.getId()));
		}
		return ls;
	}

	private List<Card> buildWillSend(List<Integer> ids) {
		ArrayList<Card> ls = Lists.newArrayList();
		for (Integer id : ids) {
			ls.add(Pocker.get(id));
		}
		return ls;
	}

}
