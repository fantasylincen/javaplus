package cn.javaplus.crazy.main;

import java.util.List;

import cn.javaplus.crazy.D;
import cn.javaplus.crazy.collections.Lists;
import cn.javaplus.crazy.pocker.JdzBean;
import cn.javaplus.crazy.pocker.JdzRole;
import cn.javaplus.crazy.pocker.Place;
import cn.javaplus.crazy.pocker.Table;
import cn.javaplus.crazy.protocol.Protocols.Channel;
import cn.javaplus.crazy.user.Game;
import cn.javaplus.log.Log;

import com.google.common.collect.Sets;

public class JdzManager {

	private List<JdzBean> jdzs;

	public JdzManager() {
		jdzs = Lists.newArrayList();
	}

	public void add(JdzBean bean) {
		Place place = bean.getPlace();
		boolean isSayDz = bean.isSayDz();
		Table table = place.getTable();

		boolean hasSomeOneJdz = hasSomeOneJdz();

		jdzs.add(bean);

		Integer index = getDzIndex();

		if (Sets.newHashSet(0, 1, 2).contains(index)) {
			JdzBean b = jdzs.get(index);
			Place dz = b.getPlace();
			dz.toBeDz();
			Log.d("JdzManager.add to be dz", index, dz.getPlayer().getNick());
			clearAllTips(table);
			table.sendTip(dz, D.WAIT_CP);

			return;
		}

		if (Sets.newHashSet(-1).contains(index)) {
			table.restartGame();
			return;
		}

		if (index == null) {
			if (hasSomeOneJdz) {
				if (isSayDz) {
					table.sendTip(place, D.QDZ);
				} else {
					table.sendTip(place, D.BQDZ);
				}
				sendRight(table, place, D.WAIT_QDZ);
			} else {
				if (isSayDz) {
					table.sendTip(place, D.JDZ);
					sendRight(table, place, D.WAIT_QDZ);
				} else {
					table.sendTip(place, D.BJDZ);
					sendRight(table, place, D.WAIT_JDZ);
				}
			}
		}

		table.next();

	}

	private void clearAllTips(Table table) {
		for (Place p : table.getIterator()) {
			Channel channel = p.getPlayer().getChannel();
			if (channel != null)
				Game.getProtocols().getPlayHandler()
						.clearAllTip(channel, new EmptyPImpl());
		}
	}

	private Integer getDzIndex() {
		String text = buildText();
		Log.d("JdzManager.getDzIndex ", text, buildNicks());
		return JdzRole.getDz(text);
	}

	private Object buildNicks() {
		StringBuilder sb = new StringBuilder();
		for (JdzBean j : jdzs) {
			sb.append(j.getPlace().getPlayer().getNick() + " - ");
		}
		return sb.toString();
	}

	private String buildText() {
		StringBuilder sb = new StringBuilder();
		for (JdzBean b : jdzs) {
			sb.append(b.isSayDz() ? "1" : "0");
		}
		return sb.toString();
	}

	private boolean hasSomeOneJdz() {
		for (JdzBean b : jdzs) {
			boolean sayDz = b.isSayDz();
			if (sayDz) {
				return true;
			}
		}
		return false;
	}

	private void sendRight(Table table, Place place, String message) {
		Place right = table.getRightPlace(place);
		table.sendTip(right, message);
	}

	public void clear() {
		jdzs.clear();
	}

}
