package cn.mxz.bossbattle;

import cn.mxz.battle.WarSituation;

public class BossBattleResult {
	
	private WarSituation 		situation;
	private int					rank;
	private int					damage;
	private int					allDamage;
	
	public WarSituation getSituation() {
		return situation;
	}
	public int getRank() {
		return rank;
	}
	public int getDamage() {
		return damage;
	}
	public int getAllDamage() {
		return allDamage;
	}
	public void setSituation(WarSituation situation) {
		this.situation = situation;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public void setAllDamage(int allDamage) {
		this.allDamage = allDamage;
	}
	
	
	

}
