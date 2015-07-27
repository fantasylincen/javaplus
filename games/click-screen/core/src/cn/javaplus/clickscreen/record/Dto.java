package cn.javaplus.clickscreen.record;

import java.util.Map;

import cn.javaplus.clickscreen.tank.TankDto;

public class Dto {

	private double coin;
	private int secondaryMission;
	private Map<Integer, TankDto> tanks;

	public double getCoin() {
		return coin;
	}

	public void setCoin(double coin) {
		this.coin = coin;
	}

	public int getSecondaryMission() {
		return secondaryMission;
	}

	public void setSecondaryMission(int secondaryMission) {
		this.secondaryMission = secondaryMission;
	}

	public Map<Integer, TankDto> getTanks() {
		return tanks;
	}

	public void setTanks(Map<Integer, TankDto> tanks) {
		this.tanks = tanks;
	}

}
