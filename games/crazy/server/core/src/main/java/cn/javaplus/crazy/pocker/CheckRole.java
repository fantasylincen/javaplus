package cn.javaplus.crazy.pocker;

import java.util.List;

import cn.javaplus.crazy.events.Listener;

import com.google.common.collect.Lists;

public class CheckRole implements Listener<BeforeCpEvent> {

	@Override
	public void onEvent(BeforeCpEvent e) {
		List<Integer> ids = e.getIds();
		Place place = e.getPlace();
		Table table = place.getTable();
		UserSendCards cards = table.getUserSendCards();
		List<Card> willSend = Lists.newArrayList();
		for (Integer id : ids) {
			willSend.add(Pocker.get(id));
		}
		List<Card> lastCards;
		if (cards == null || isSendByMySelf(cards, place)) {
			lastCards = Lists.newArrayList();
		} else {
			lastCards = cards.getCards();
		}
		DzRule.check(willSend, lastCards);
	}

	private boolean isSendByMySelf(UserSendCards cards, Place place) {
		return cards.getPlaceNumber() == place.getPlaceNumber();
	}

}
