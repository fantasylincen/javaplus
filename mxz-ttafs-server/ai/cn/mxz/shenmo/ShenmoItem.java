package cn.mxz.shenmo;

import java.util.List;

import cn.mxz.city.City;
import db.dao.impl.DaoFactory;
import db.domain.BossDamageData;
import db.domain.BossData;

/**
 * 一个神魔的完善的信息，包括攻击列表
 * @author Administrator
 *
 */
public class ShenmoItem {
	private final ShenmoFighter				fighter;
	private final BossData					bd;
	//private List<BossDamageData>			damageList = Lists.newArrayList();
	
	
	public int getBossId(){
		return bd.getBossId();
	}
	public ShenmoItem(BossData bd ) {
		this.bd = bd;		
		fighter = new ShenmoFighter( bd );
		
		//this.damageList = damageList;
	}
	
	public int getHp(){
		return bd.getHp();
	}
//	public void addDamageData( BossDamageData data ){
//		getDamageList().add(data);
//	}

	/**
	 * 神魔死了或者逃走
	 * @return
	 */
	boolean isEnd(){
		return bd.getHp() <= 0 || getRunSecond() <= 0;
	}
	
	BossDamageData getUserData( String uname ){
		for( BossDamageData bdd : getDamageList() ){
			if( bdd.getChallengerId().equals( uname ) ){
				return bdd;
			}
		}
		return null;
	}
	
	public int getRank( String uname ){
		int rank = 1;
		for( BossDamageData bdd : getDamageList() ){
			
			if( bdd.equals( uname ) ){
				break;
			}
			rank++;
		}
		return rank;
	}
	
	

	/**
	 * 判断神魔逃跑的剩余时间
	 * @param bossData
	 * @return
	 */
	public int getRunSecond() {
		int end = bd.getFoundTime() + 60 * 60;
		int remain = (int) (end - System.currentTimeMillis() / 1000);
		return remain < 0 ? 0 : remain;
	}
	
	public String getFounder(){
		return bd.getUname();
	}

	void saveToDB(BossDamageData bdd){
		DaoFactory.getBossDataDao().update( bd );
	}

	public BossData getBossData() {
		return bd;
	}
	public boolean isDeath() {
		return bd.getHp() <= 0;
	}
	
	/**
	 * 计算mvp是谁，有可能返回null，如果没有人攻击过此神魔
	 * @param fighter
	 * @return
	 */
	public boolean isMvp(City user) {
		
		int maxDamage = 0;
		BossDamageData mvp = null;
		for( BossDamageData bdd : getDamageList() ){
			if( bdd.getDamage() > maxDamage ){
				mvp = bdd;
				maxDamage = bdd.getDamage();
			}
		}
		
		if( mvp == null || !mvp.getChallengerId().equals( user.getId() ) ){
			return false;
		}
		return true;
	}
	public boolean isKiller( City user ) {
		if ( bd.getKiller()  == null ){
			return false;
		}
		
		if( bd.getKiller().equals( user.getId() ) ){
			return true;
		}
		return false;
	}
	
	public boolean isFounder( City user ) {
		assert ( bd.getUname()  != null );
		if( bd.getUname().equals( user.getId() ) ){
			return true;
		}
		return false;
	}
	/**
	 * @return fighter
	 */
	public ShenmoFighter getFighter() {
		return fighter;
	}
	/**
	 * @return damageList
	 */
	public List<BossDamageData> getDamageList() {
		return ShenmoManager.INSTANCE.getBossDamageDataByShenmo( bd.getBossId() );
	}
	
}
