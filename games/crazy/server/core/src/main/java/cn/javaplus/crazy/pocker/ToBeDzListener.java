package cn.javaplus.crazy.pocker;

import java.util.List;

import cn.javaplus.crazy.events.Listener;
import cn.javaplus.crazy.protocol.Protocols.Channel;
import cn.javaplus.crazy.protocol.Protocols.PlayHandler;
import cn.javaplus.crazy.user.Game;

public class ToBeDzListener implements Listener<ToBeDzEvent> {

	@Override
	public void onEvent(ToBeDzEvent e) {
		Place place = e.getPlace();
		Table table = place.getTable();

		List<Place> all = table.getPlaces();
		PlayHandler h = Game.getProtocols().getPlayHandler();
		for (Place p : all) {
			Channel channel = p.getPlayer().getChannel();
			if (channel != null) {
				h.startPlayPocker(channel, new StartPlayPImpl(table, place));
			}
		}

		List<Card> dzCards = table.getDzCards();
		place.addCards(dzCards);
		table.clearDzCards();
	}

}
