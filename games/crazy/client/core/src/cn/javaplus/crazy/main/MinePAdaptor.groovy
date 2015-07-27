package cn.javaplus.crazy.main;

import java.util.List;

import cn.javaplus.crazy.Protocols.CardP;
import cn.javaplus.crazy.Protocols.MineP;
import cn.javaplus.crazy.Protocols.PlayerP;

public class MinePAdaptor implements PlayerInfo {

	private PlayerP player;
	private List<CardP> cards;

	public MinePAdaptor(MineP data) {
		player = data.getPlayer();
		cards = data.getCards();
	}

	public int getRamainCards() {
		return cards.size();
	}

	public String getNick() {
		return player.getNick();
	}

}
