package cn.mxz.shenmo.battle;

import cn.mxz.battle.WarSituation;

public class ShemmoWarSituation {
	private WarSituation	situation;
	private int				gongde;
	private int				damage;
	public ShemmoWarSituation(WarSituation situation, int gongde, int damage ) {
		super();
		this.setSituation(situation);
		this.setGongde(gongde);
		this.setDamage(damage);
	}
	
	/**
	 * @return situation
	 */
	public WarSituation getSituation() {
		return situation;
	}
	/**
	 * @param situation 要设置的 situation
	 */
	public void setSituation(WarSituation situation) {
		this.situation = situation;
	}
	/**
	 * @return gongde
	 */
	public int getGongde() {
		return gongde;
	}
	/**
	 * @param gongde 要设置的 gongde
	 */
	public void setGongde(int gongde) {
		this.gongde = gongde;
	}

	/**
	 * @return damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param damage 要设置的 damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	
	

}
