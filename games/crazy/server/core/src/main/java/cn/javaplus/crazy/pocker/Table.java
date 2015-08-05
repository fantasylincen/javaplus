package cn.javaplus.crazy.pocker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.crazy.events.Events;
import cn.javaplus.crazy.exception.ServerException;
import cn.javaplus.crazy.main.CpP;
import cn.javaplus.crazy.main.JdzManager;
import cn.javaplus.crazy.main.Player;
import cn.javaplus.crazy.protocol.Protocols;
import cn.javaplus.crazy.protocol.Protocols.Channel;
import cn.javaplus.crazy.protocol.Protocols.PlayHandler;
import cn.javaplus.crazy.user.Game;
import cn.javaplus.log.Log;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

/**
 * @author 林岑
 */
public class Table {

	private List<Card> dzCards;

	public class PlaceIterator implements Iterable<Place> {

		@Override
		public Iterator<Place> iterator() {

			Place current = getCurrent();
			Place left = getLeftPlace(current);
			Place right = getRightPlace(current);

			List<Place> ls = new ArrayList<Place>();
			add(ls, current);
			add(ls, left);
			add(ls, right);
			return ls.iterator();
		}

		private void add(List<Place> ls, Place t) {
			Player player = t.getPlayer();
			Channel channel = player.getChannel();
			if (channel != null) {
				ls.add(t);
			}
		}

	}

	public void dispatchPocker() {
		Pocker pocker = new Pocker();
		pocker.randomCards();
		dzCards = pocker.peek(3);
		for (Card card : pocker.getCards()) {
			Place p = getCurrent();
			p.addCard(card);
			next();
		}
	}

	public List<Card> getDzCards() {
		return dzCards;
	}

	public void onTimeUpdate() {
	}

	private Place dz;

	private Place a; // left is c
	private Place b; // left is a
	private Place c; // left is b
	private Place current;

	private JdzManager jdzManager;

	private UserSendCards userSendCards ;

	public Table() {
		initPlace();
		jdzManager = new JdzManager();
	}

	private void initPlace() {
		a = new Place(this, 1);
		b = new Place(this, 2);
		c = new Place(this, 3);
		current = a;
	}

	public Place getA() {
		return a;
	}

	public Place getB() {
		return b;
	}

	public Place getC() {
		return c;
	}

	public Place next() {
		current = getRightPlace(current);
		return current;
	}

	public Place getCurrent() {
		return current;
	}

	public void start() {
		dispatchPocker();
		Events.dispatch(new GameStartEvent(this));
	}

	public void sitDown(List<Player> players) {
		if (players.size() != 3) {
			throw new cn.javaplus.crazy.exception.ServerException(
					"player must be 3");
		}
		Util.Collection.upset(players);
		a.sitDown(players.get(0));
		b.sitDown(players.get(1));
		c.sitDown(players.get(2));
	}

	public Place getDz() {
		return dz;
	}

	public void setDz(Place dz) {
		this.dz = dz;
	}

	public Place getLeftPlace(Place place) {
		return getLeftPlace(place.getPlayer());
	}

	public Place getRightPlace(Place place) {
		return getRightPlace(place.getPlayer());
	}

	public Place getLeftPlace(Player player) {
		String id = player.getId();
		return getLeftPlace(id);
	}

	public Place getRightPlace(Player player) {
		String id = player.getId();
		return getRightPlace(id);
	}

	public Place getLeftPlace(String id) {
		if (a.isSitDown(id)) {
			return c;
		}
		if (b.isSitDown(id)) {
			return a;
		}
		if (c.isSitDown(id)) {
			return b;
		}
		throw new RuntimeException("can not found left:" + id);
	}

	public Place getRightPlace(String id) {
		if (a.isSitDown(id)) {
			return b;
		}
		if (b.isSitDown(id)) {
			return c;
		}
		if (c.isSitDown(id)) {
			return a;
		}
		throw new RuntimeException("can not found right:" + id);
	}

	public Player getLeftPlayer(Player player) {
		return getLeftPlace(player).getPlayer();
	}

	public Player getRightPlayer(Player player) {
		return getRightPlace(player).getPlayer();
	}

	public void reEnter(Player player) {
		Place p = getPlace(player);
		p.sitDown(player);
		sendEnterMessage(player);
	}

	private void sendEnterMessage(Player player) {
		Channel channel = player.getChannel();
		Protocols pt = Game.getProtocols();
		PlayHandler h = pt.getPlayHandler();
		h.enterOldTable(channel, new TablePImpl(player, this));
	}

	public Place getPlace(Player player) {
		return getPlace(player.getId());
	}

	public Place getPlace(String id) {
		if (a.isSitDown(id)) {
			return a;
		}
		if (b.isSitDown(id)) {
			return b;
		}
		if (c.isSitDown(id)) {
			return c;
		}
		throw new RuntimeException("can not found place:" + id);
	}

	public PlaceIterator getIterator() {
		return new PlaceIterator();
	}

	public void restartGame() {
		clearTable();
		start();
	}

	private void clearTable() {
		getA().reset();
		getB().reset();
		getC().reset();
		jdzManager.clear();
		dzCards.clear();
		dz = null;
	}

	public void checkCurrent(Place place) {
		if (current != place) {
			throw new ServerException("is not your turn now");
		}
	}

	public JdzManager getJdzManager() {
		return jdzManager;
	}

	public void clearDzCards() {
		dzCards.clear();
	}

	public List<Place> getPlaces() {
		ArrayList<Place> ls = Lists.newArrayList();
		ls.add(getA());
		ls.add(getB());
		ls.add(getC());
		return ls;
	}

	public void setUserSendCards(UserSendCards userSendCards) {
		this.userSendCards = userSendCards;
	}

	public UserSendCards getUserSendCards() {
		return userSendCards;
	}

	public void sendCpMessage(Place place, List<Card> cards) {
		for (Place p : getIterator()) {
			Channel channel = p.getPlayer().getChannel();
			PlayHandler h = Game.getProtocols().getPlayHandler();
			CpP data = new CpImpl(place, cards);
			h.chuPai(channel, data);
		}
	}

	/**
	 * @param tipFrom
	 *            消息发送者
	 * @param message
	 */
	public void sendTip(Place tipFrom, String message) {
		int placeNumber = tipFrom.getPlaceNumber();
		Log.d("Table.sendTip ", placeNumber, message);
		for (Place p : getIterator()) {
			Channel channel = p.getPlayer().getChannel();
			TipPImpl tip = new TipPImpl(placeNumber, message);
			PlayHandler h = Game.getProtocols().getPlayHandler();
			h.tip(channel, tip);
		}
	}

	public void setCurrent(Place place) {
		current = place;
	}
}
