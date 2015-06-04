package cn.mxz.newpvp;

import cn.javaplus.math.Fraction;
import cn.mxz.city.City;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.user.Player;
import cn.mxz.user.builder.PlayerBase;

public class NormalPvpFightUser implements PvpFightUser {

	private PvpPlayer	player;

	public NormalPvpFightUser(PvpPlayer player) {
		this.player = player;
	}

	@Override
	public int getDanId() {
		return player.getDan();
	}

	@Override
	public int getPower() {
		City city = player.getCity();
		Player player2 = city.getPlayer();
		Fraction power = player2.getPower();
		return power.getNumerator();
	}

	@Override
	public Fraction getPractice() {
		return player.getPractice();
	}

	@Override
	public int getWarsituationId() {
		return player.getWarsituationId();
	}

	@Override
	public int getRankInAll() {
		return player.getRankInAll();
	}

	@Override
	public PlayerCamp getCamp() {
		return player.getCity().getFormation().getSelected();
	}

	@Override
	public PlayerBase getPlayer() {
		City city = player.getCity();
		Player player2 = city.getPlayer();
		return player2;
	}

	@Override
	public int getStep() {
		City city = player.getCity();
		PlayerHero p = city.getTeam().getPlayer();
		return p.getStep();
	}

	@Override
	public int getWinTimes() {
		return player.getWinTimes();
	}

	@Override
	public int getLoseTimes() {
		return player.getLoseTimes();
	}

	@Override
	public boolean isRobot() {
		return false;
	}

}
