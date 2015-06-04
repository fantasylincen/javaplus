package cn.javaplus.mxzrobot.listeners;

import java.util.Comparator;

import cn.javaplus.mxzrobot.events.Ask.AskWay;

public class SortBySimilar implements Comparator<AskWay> {

	private String heSay;

	public SortBySimilar(String heSay) {
		this.heSay = heSay;
	}

	public int compare(AskWay a1, AskWay a2) {
		return a2.getSimilar(heSay) - a1.getSimilar(heSay);
	}

}
