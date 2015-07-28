package cn.javaplus.crazy.cp;

import java.util.List;

import cn.javaplus.crazy.main.MyPlayer;

public class BeforeCpEvent {

	private List<Integer> ids;
	private MyPlayer minePlayer;

	public BeforeCpEvent(List<Integer> ids, MyPlayer minePlayer) {
		this.ids = ids;
		this.minePlayer = minePlayer;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public MyPlayer getMinePlayer() {
		return minePlayer;
	}
}
