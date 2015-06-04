package cn.mxz.mission.type;

import cn.mxz.mission.old.demon.Demon;

public class DemonInCamp {

	private Demon	demon;
	private int	position;

	public DemonInCamp(Demon demon, int position) {
		this.demon = demon;
		this.position = position;
	}

	public Demon getDemon() {
		return demon;
	}
	
	public int getPosition() {
		return position;
	}
}
