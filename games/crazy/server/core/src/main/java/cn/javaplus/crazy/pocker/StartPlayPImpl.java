package cn.javaplus.crazy.pocker;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import cn.javaplus.crazy.main.CardP;
import cn.javaplus.crazy.main.StartPlayP;

public class StartPlayPImpl implements StartPlayP {

	private Table table;
	private Place place;

	public StartPlayPImpl(Table table, Place place) {
		this.table = table;
		this.place = place;
	}

	@Override
	public int getDzPlaceNumber() {
		return place.getPlaceNumber();
	}

	@Override
	public List<CardP> getDzCards() {
		ArrayList<CardP> ls = Lists.newArrayList();
		for (Card cardP : table.getDzCards()) {
			ls.add(new CardPImpl(cardP));
		}
		return ls;
	}

}
