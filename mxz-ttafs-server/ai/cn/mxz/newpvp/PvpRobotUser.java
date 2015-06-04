package cn.mxz.newpvp;

import cn.javaplus.math.Fraction;
import cn.javaplus.util.Util;
import cn.mxz.DanRewardTemplet;
import cn.mxz.DanRewardTempletConfig;
import cn.mxz.city.City;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.user.builder.PlayerBase;
import cn.mxz.user.team.Formation;

/**
 * PVP 机器人
 * 
 * @author 林岑
 * 
 */
public class PvpRobotUser implements PvpFightUser {

	private double min;
	private double max;
	private int danId;
	private Fraction power;
	private Fraction practice;
	private PvpRobotCamp camp;
	private int warsituationId;
	private int rankInAll;
	private int step;
	private int winTimes;
	private int loseTimes;

	public PvpRobotUser(City city, double min, double max) {
		this.min = min;
		this.max = max;

		PvpManager pm = city.getNewPvpManager();
		PvpPlayer player = pm.getPlayer();
		danId = player.getDan();
		
		DanRewardTemplet temp = DanRewardTempletConfig.get(danId);
		
		if(temp.getDanLv() == 5) {
			danId -= 2;
		}
		
		power = city.getPlayer().getPower();
		practice = player.getPractice();
		warsituationId = player.getWarsituationId();
		rankInAll = player.getRankInAll();

		buildCamp(city);

		step = city.getTeam().getPlayer().getStep();

		winTimes = player.getWinTimes() + Util.Random.get(0, 30);
		loseTimes = player.getLoseTimes() + Util.Random.get(0, 20);

	}

	private void buildCamp(City city) {
		Formation formation = city.getFormation();
		PlayerCamp selected = formation.getSelected();
		this.camp = new PvpRobotCamp(selected, min, max);
	}

	@Override
	public PlayerCamp getCamp() {
		return camp;
	}

	@Override
	public int getDanId() {
		return danId;
	}

	@Override
	public int getPower() {
		return power.getNumerator();
	}

	@Override
	public Fraction getPractice() {
		return practice;
	}

	@Override
	public int getWarsituationId() {
		return warsituationId;
	}

	@Override
	public int getRankInAll() {
		return rankInAll;
	}

	@Override
	public PlayerBase getPlayer() {
		return camp.getJiaPlayer();
	}

	@Override
	public int getStep() {
		return step;
	}

	@Override
	public int getWinTimes() {
		return winTimes;
	}

	@Override
	public int getLoseTimes() {
		return loseTimes;
	}

	@Override
	public boolean isRobot() {
		return true;
	}
}
