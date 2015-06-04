package cn.mxz.newpvp;

import cn.mxz.FighterExpPrize;
import cn.mxz.battle.AbstractBattle;
import cn.mxz.city.City;
import cn.mxz.enemy.EmptyExpPrize;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.pvp.SuperCamp;
import cn.mxz.user.team.Formation;

public class PvpBattle extends AbstractBattle {

	private int winPoints;
	private boolean isUp;
	private boolean isLastUpMatch;

	public PvpBattle(City city, PvpFightUser fightingUser) {
		super(copyCamp(city), fightingUser.getCamp());
	}

	private static SuperCamp copyCamp(City city) {
		Formation formation = city.getFormation();
		PlayerCamp selected = formation.getSelected();
		return new SuperCamp(selected, 1);
	}

	@Override
	protected FighterExpPrize getMapTemplet() {
		return new EmptyExpPrize();
	}

	public void setWinPointReceived(int winPoints) {
		this.winPoints = winPoints;
		
	}
	
	public int getWinPointsReceived() {
		return winPoints;
	}

	public boolean isUp() {
		return isUp;
	}

	public void setUp(boolean isUp) {
		this.isUp = isUp;
	}

	public boolean isLastUpMatch() {
		return isLastUpMatch;
	}

	public void setLastUpMatch(boolean isLastUpMatch) {
		this.isLastUpMatch = isLastUpMatch;
	}
	
	

}
